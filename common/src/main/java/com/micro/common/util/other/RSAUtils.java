package com.micro.common.util.other;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * @program: micro
 * @description: RSA 工具
 * @author: XiongJiaMin
 * @create: 2022-06-29 15:00
 **/
public class RSAUtils {

    public static final String RSA_CHARSET = "UTF-8";
    
    public static final String RSA_ALGORITHM = "RSA";

    public static final String RSA_SIGNATURE = "SHA256WithRSA";

    /**
     * 通过生成器创建公钥私钥
     * @param keySize 密钥长度
     * @return #Map
     */
    public static Map<String, String> createKeys(int keySize) {
        KeyPairGenerator kpg;
        try {
            // 创建RSA秘钥生成器
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("创建构造器失败,没有该秘钥类型:[" + RSA_ALGORITHM + "]");
        }
        // 初始化密钥长度
        kpg.initialize(keySize);
        // 生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        // 得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.getMimeEncoder().encodeToString(publicKey.getEncoded());
        // 得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.getMimeEncoder().encodeToString(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);
        return keyPairMap;
    }

    /**
     * 获取公钥
     * @param publicKey 公钥base64字符串
     * @throws NoSuchAlgorithmException #NoSuchAlgorithmException
     * @throws InvalidKeySpecException #InvalidKeySpecException
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        byte[] bytes = Base64.getMimeDecoder().decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(bytes);
        return (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
    }

    /**
     * 获取私钥
     * @param privateKey  私钥base64字符串
     * @throws NoSuchAlgorithmException #NoSuchAlgorithmException
     * @throws InvalidKeySpecException #InvalidKeySpecException
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        byte[] bytes = Base64.getMimeDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(bytes);
        return (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
    }

    /**
     * 公钥加密
     * @param data 数据
     * @param publicKey 公钥
     * @return 加密后base64字符串
     */
    public static String publicEncrypt(String data, RSAPublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.getMimeEncoder().encodeToString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(RSA_CHARSET), publicKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("公钥加密数据[" + data + "]失败:", e);
        }
    }

    /**
     * 私钥解密
     * @param data 数据
     * @param privateKey 私钥
     * @return 解密明文
     */
    public static String privateDecrypt(String data, RSAPrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.getMimeDecoder().decode(data), privateKey.getModulus().bitLength()), RSA_CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("私钥解密数据[" + data + "]失败:", e);
        }
    }

    /**
     * 私钥加密
     * @param data 数据
     * @param privateKey 私钥
     * @return 加密后base64字符串
     */
    public static String privateEncrypt(String data, RSAPrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.getMimeEncoder().encodeToString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(RSA_CHARSET), privateKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("私钥加密数据[" + data + "]失败:", e);
        }
    }

    /**
     * 公钥解密
     * @param data 数据
     * @param publicKey 公钥
     * @return 明文
     */
    public static String publicDecrypt(String data, RSAPublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.getMimeDecoder().decode(data), publicKey.getModulus().bitLength()), RSA_CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("公钥解密数据[" + data + "]失败:", e);
        }
    }

    /**
     * RSA 分割加解密
     * @param cipher 加解密类
     * @param edMode 加解密模式  1 加密 2 解密
     * @param data 数据 byte[]
     * @param keySize 秘钥长度
     * @return #byte[]
     */
    private static byte[] rsaSplitCodec(Cipher cipher, int edMode, byte[] data, int keySize) {
        // 最大解密长度
        int maxBlock;
        if (edMode == Cipher.DECRYPT_MODE) {
            maxBlock = (keySize + 1) / 8;
        } else {
            maxBlock = (keySize + 1) / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (data.length > offSet) {
                if (data.length - offSet > maxBlock) {
                    //加密或解密数据
                    buff = cipher.doFinal(data, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(data, offSet, data.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常:", e);
        }
        byte[] resultData = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultData;
    }

    /**
     * 私钥对数据签名
     * @param content 被签名的数据
     * @param privateKey 私钥
     * @return base64 签名
     */
    public static String sign(String content, String privateKey) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.getMimeDecoder().decode(privateKey));
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);
            Signature signature = Signature.getInstance(RSA_SIGNATURE);
            signature.initSign(priKey);
            signature.update(content.getBytes(RSA_CHARSET));
            byte[] signed = signature.sign();
            return Base64.getMimeEncoder().encodeToString(signed);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 公钥验签
     * @param plainText 待验签明文
     * @param signStr 签名
     * @param publicKey base64加密公钥
     * @return true/false
     */
    public static boolean verifySign(String publicKey, String plainText, String signStr) {
        try {
            PublicKey key = getPublicKey(publicKey);
            Signature verifySign = Signature.getInstance("SHA256withRSA");
            verifySign.initVerify(key);
            verifySign.update(plainText.getBytes());
            return verifySign.verify(Base64.getMimeDecoder().decode(signStr));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
