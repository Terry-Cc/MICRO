package com.micro.common.util.other;

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.operator.jcajce.*;
import org.bouncycastle.util.io.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Iterator;
import java.util.Objects;

/**
 * @program: micro
 * @description: PGP 工具
 * @author: XiongJiaMin
 * @create: 2022-06-29 09:30
 **/
@SuppressWarnings("unused")
public class PgpUtils {

    protected static Logger logger = LoggerFactory.getLogger(PgpUtils.class);

    static{
        try{
            Security.addProvider(new BouncyCastleProvider());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 开始加密
     * @param encryptFileOutPath 加密输出文件路径
     * @param encryptFileInPath 加密文件路径
     * @param publicKeyPath 公钥文件路径
     * @param armor PGP修饰
     * @param withIntegrityCheck 完整校验
     */
    public static void encrypt(
            String encryptFileOutPath,
            String encryptFileInPath,
            String publicKeyPath,
            boolean armor,
            boolean withIntegrityCheck) throws IOException, PGPException {
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(encryptFileOutPath))) {
            PGPPublicKey encKey = readPublicKey(publicKeyPath);
            encryptFile(out, encryptFileInPath, encKey, armor, withIntegrityCheck);
        }
    }

    /**
     * 开始解密
     * @param decryptFileInPath 要解密得文件路径
     * @param privateKeyPath 私钥路径
     * @param privateKeyPwdPath 私钥密码文件路径
     * @param outPutFilePath 解密后的文件输出路径
     * @throws IOException  #IOException
     * @throws PGPException  #PGPException
     * @return 解密文件全路径
     */
    public static String decrypt(
            String decryptFileInPath,
            String privateKeyPath,
            String privateKeyPwdPath,
            String outPutFilePath) throws IOException, PGPException {
        try (InputStream in = new BufferedInputStream(new FileInputStream(decryptFileInPath));
             InputStream keyIn = new BufferedInputStream(new FileInputStream(privateKeyPath))) {
            char[] privateKeyPwd = Objects.requireNonNull(FileUtils.readFile(privateKeyPwdPath)).toCharArray();
            return decryptFile(in, keyIn, privateKeyPwd, outPutFilePath);
        }
    }

    /**
     * 开始解密
     * @param in  要解密得文件
     * @param keyIn  私钥
     * @param passwd  私钥密码
     * @param outPutFilePath  解密后的文件输出路径
     * @throws IOException  #IOException
     * @throws PGPException  #PGPException
     * @return 解密文件全路径
     */
    public static String decryptFile(
            InputStream in,
            InputStream keyIn,
            char[] passwd,
            String outPutFilePath) throws IOException, PGPException {
        in = PGPUtil.getDecoderStream(in);
        JcaPGPObjectFactory pgpF = new JcaPGPObjectFactory(in);
        PGPEncryptedDataList enc;
        Object o = pgpF.nextObject();
        // 第一个PGP对象可能是加密数据集, 不是则获取下一个
        if (o instanceof PGPEncryptedDataList) {
            enc = (PGPEncryptedDataList) o;
        } else {
            enc = (PGPEncryptedDataList) pgpF.nextObject();
        }
        // 获取加密数据
        Iterator it = null;
        if (enc != null) {
            it = enc.getEncryptedDataObjects();
        }
        PGPPrivateKey sKey = null;
        PGPPublicKeyEncryptedData pbe = null;
        String outFileName;
        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(
                PGPUtil.getDecoderStream(keyIn), new JcaKeyFingerprintCalculator());
        if (it != null) {
            while (sKey == null && it.hasNext()) {
                pbe = (PGPPublicKeyEncryptedData) it.next();
                sKey = findSecretKey(pgpSec, pbe.getKeyID(), passwd);
            }
        }
        if (sKey == null) {
            throw new IllegalArgumentException("没有找到对应的私钥.");
        }
        InputStream clear = pbe.getDataStream(new JcePublicKeyDataDecryptorFactoryBuilder().setProvider("BC").build(sKey));
        JcaPGPObjectFactory plainFact = new JcaPGPObjectFactory(clear);
        Object message = plainFact.nextObject();
        if (message instanceof PGPCompressedData) {
            PGPCompressedData cData = (PGPCompressedData) message;
            JcaPGPObjectFactory pgpFact = new JcaPGPObjectFactory(cData.getDataStream());
            message = pgpFact.nextObject();
        }
        if (message instanceof PGPLiteralData) {
            PGPLiteralData ld = (PGPLiteralData) message;
            outFileName = ld.getFileName();
            if (outFileName.length() != 0) {
                outFileName = outPutFilePath.concat(outFileName);
                InputStream unc = ld.getInputStream();
                OutputStream fOut = new BufferedOutputStream(new FileOutputStream(outFileName));
                Streams.pipeAll(unc, fOut);
                fOut.close();
            } else {
                throw new PGPException("解密输出文件名获取失败.");
            }
        } else if (message instanceof PGPOnePassSignatureList) {
            throw new PGPException("加密消息包含签名消息, 解密失败.");
        } else {
            throw new PGPException("加密消息类型未知, 解密失败.");
        }
        if (pbe.isIntegrityProtected()) {
            if (!pbe.verify()) {
                logger.error("加密消息完整性校验不通过.");
            } else {
                logger.error("加密消息完整性校验通过.");
            }
        } else {
            logger.error("加密消息不校验.");
        }
        return outFileName;
    }

    /**
     * 开始加密
     * @param out 加密输出文件
     * @param encryptFileInPath 加密文件路径
     * @param encKey 公钥
     * @param armor  PGP修饰
     * @param withIntegrityCheck 完整性校验
     * @throws IOException #IOException
     * @throws PGPException #PGPException
     */
    private static void encryptFile(
            OutputStream out,
            String encryptFileInPath,
            PGPPublicKey encKey,
            boolean armor,
            boolean withIntegrityCheck) throws IOException, PGPException {
        if (armor) {
            out = new ArmoredOutputStream(out);
        }
        // 压缩加密文件, 获取字节数组
        byte[] bytes = compressFile(encryptFileInPath, CompressionAlgorithmTags.ZIP);
        PGPEncryptedDataGenerator encGen = new PGPEncryptedDataGenerator(
                new JcePGPDataEncryptorBuilder(PGPEncryptedData.CAST5)
                        .setWithIntegrityPacket(withIntegrityCheck)
                        .setSecureRandom(new SecureRandom())
                        .setProvider("BC"));
        encGen.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(encKey).setProvider("BC"));
        OutputStream cOut = encGen.open(out, bytes.length);
        if (cOut != null) {
            cOut.write(bytes);
            cOut.close();
        }
        if (armor) {
            out.close();
        }
    }

    /**
     * 压缩文件
     * @param filePath 文件路径
     * @param algorithm  压缩模式
     * @return #byte[]
     * @throws IOException #IOException
     */
    public static byte[] compressFile(String filePath, int algorithm) throws IOException {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(algorithm);
        PGPUtil.writeFileToLiteralData(comData.open(bOut), PGPLiteralData.BINARY,
                new File(filePath));
        comData.close();
        return bOut.toByteArray();
    }

    /**
     * 通过秘钥 ID 获取秘钥集里的 私钥
     * @param pgpSec 秘钥集合
     * @param keyID 需要的秘钥ID
     * @param pass 私钥对应的密码
     * @return #see PGPPrivateKey
     * @throws PGPException  #PGPException
     */
    public static PGPPrivateKey findSecretKey(PGPSecretKeyRingCollection pgpSec, long keyID, char[] pass) throws PGPException {
        PGPSecretKey pgpSecKey = pgpSec.getSecretKey(keyID);
        if (pgpSecKey == null) {
            return null;
        }
        return pgpSecKey.extractPrivateKey(new JcePBESecretKeyDecryptorBuilder().setProvider("BC").build(pass));
    }

    /**
     * 读取公钥
     * @param publicKeyPath 公钥路径
     * @return #see PGPPublicKey
     * @throws IOException  #IOException
     * @throws PGPException  #PGPException
     */
    private static PGPPublicKey readPublicKey(String publicKeyPath) throws IOException, PGPException {
        InputStream keyIn = new BufferedInputStream(new FileInputStream(publicKeyPath));
        PGPPublicKey pubKey = readPublicKey(keyIn);
        keyIn.close();
        return pubKey;
    }

    /**
     * 通过秘钥环获取第一个可用的秘钥
     * @param input 公钥
     * @return #see PGPPublicKey
     * @throws IOException  #IOException
     * @throws PGPException  #PGPException
     */
    public static PGPPublicKey readPublicKey(InputStream input) throws IOException, PGPException {
        PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(
                    PGPUtil.getDecoderStream(input), new JcaKeyFingerprintCalculator());
        Iterator<PGPPublicKeyRing> keyRingIter = pgpPub.getKeyRings();
        if (keyRingIter != null) {
            while (keyRingIter.hasNext()) {
                PGPPublicKeyRing keyRing = keyRingIter.next();
                Iterator<PGPPublicKey> keyIter = keyRing.getPublicKeys();
                while (keyIter.hasNext()) {
                    PGPPublicKey key = keyIter.next();
                    if (key.isEncryptionKey()) {
                        return key;
                    }
                }
            }
        }
        throw new IllegalArgumentException("查找公钥失败.");
    }
}
