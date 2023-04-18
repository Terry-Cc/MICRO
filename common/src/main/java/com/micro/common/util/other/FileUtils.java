package com.micro.common.util.other;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: micro
 * @description: 文件 工具
 * @author: XiongJiaMin
 * @create: 2022-07-01 17:22
 **/
@SuppressWarnings("unused")
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static final char CSV_DEFAULT_SEPARATOR = ',';
    
    public static final char CSV_VERTICAL_SEPARATOR = '|';

    public static final String CSV_DEFAULT_CHARSET = "UTF-8";

    public static final String CSV_DEFAULT_SUFFIX = ".csv";

    public static final String NEWLINE_CHARACTER = "\r\n";

    public static final String EMPTY = "";

    /**
     * 读文件
     * @param filePath 文件全路径
     * @return 文件内容 {@link String}
     */
    public static String readFile(String filePath) {
        String readStr = null;
        try {
            readStr = readFile(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件读取失败:", e);
        }
        return readStr;
    }

    public static String readFile(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, CSV_DEFAULT_CHARSET))) {
            int bt;
            while ((bt = reader.read()) != -1) {
                sb.append((char)bt);
            }
        } catch (IOException e) {
            throw new RuntimeException("文件读取失败:", e);
        }
        return sb.toString();
    }

    /**
     * 写文件
     * @param filePath 文件路径
     * @param fileData 文件数据
     * @throws RuntimeException 异常
     * @throws IOException 异常
     */
    public static void writeFile(String filePath, String fileData) throws RuntimeException, IOException {
        File file = new File(filePath);
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file))) {
            writer.write(fileData);
        } catch (IOException e) {
            throw new RuntimeException("文件写入失败:", e);
        }
    }

    /**
     * 去除文件空格
     * @param filePath 文件路径
     * @throws IOException 异常
     */
    public static void trimAll(String filePath) throws IOException {
        String strFromFile = readFile(filePath);
        writeFile(filePath, strFromFile.replaceAll(" +", ""));
    }

    /**
     * 删除单个文件
     * @param filePath 文件全路径
     * @return 成功/失败
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 删除目标文件夹以及文件夹下的文件, 递归
     * @param  path 被删除文件夹路径
     * @return  成功/失败
     */
    public static boolean clearDirectory(String path) {
        if (!path.endsWith(File.separator)) {
            path = path.concat(File.separator);
        }
        File dirFile = new File(path);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    flag = deleteFile(file.getAbsolutePath());
                    if (!flag) {
                        break;
                    }
                }
                else {
                    flag = clearDirectory(file.getAbsolutePath());
                    if (!flag) {
                        break;
                    }
                }
            }
        }
        if (!flag) {
            return false;
        }
        return dirFile.delete();
    }

    /**
     * 获取文件唯一标识
     * @param file 文件
     * @param algorithm 加密方式 MD5，SHA-256
     * @return value #String
     * @throws Exception 异常
     */
    public static String getHashByFile(File file, String algorithm) throws Exception {
        String value;
        try (FileInputStream in = new FileInputStream(file)) {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(byteBuffer);
            value = new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return value;
    }

    /**
     * 获取文件唯一标识
     * @param filePath 文件路径
     * @param algorithm 加密方式 MD5，SHA-256
     * @return value #String
     * @throws Exception 异常
     */
    public static String getHashByFile(String filePath, String algorithm) throws Exception {
        return getHashByFile(new File(filePath), algorithm);
    }

    /**
     * 修改 CSV
     * @param filePath 文件路径
     * @param row CSV行数
     * @param column CSV列数
     * @param modifyValue 修改的数据
     * @param charset 编码格式
     * @param separator CSV分隔符
     * @return 旧的值
     */
    public static String modifyCsv(String filePath, int row, int column, String modifyValue, String charset, char separator) throws RuntimeException {
        String oldValue = null;
        try {
            List<String[]> strList = readCsv(filePath, separator, charset);
            checkCsv(row, column, strList);
            if (modifyValue == null || modifyValue.length() == 0) {
                String[] rowStrS = strList.get(row - 1);
                List<String> colList = new ArrayList<>(Arrays.asList(rowStrS));
                oldValue = colList.remove(column - 1);
                if (colList.size() == 0) {
                    strList.remove(row - 1);
                } else {
                    strList.set(row - 1, colList.toArray(new String[0]));
                }
            } else {
                strList.get(row - 1)[column - 1] = modifyValue;
            }
            writeCsv(filePath, charset, separator, strList);
        } catch (RuntimeException e) {
            throw new RuntimeException("修改CSV失败:", e);
        }
        return oldValue;
    }

    /**
     * 写 CSV
     * @param filePath 文件路径
     * @param charset 编码
     * @param separator 分隔符
     * @param data 数据
     */
    public static void writeCsv(String filePath, String charset, char separator, List<String[]> data) throws RuntimeException {
        if (data != null && data.size() > 0) {
            CsvWriter csvWriter = null;
            try {
                csvWriter = new CsvWriter(filePath, separator, Charset.forName(charset));
                for (String[] csvRow : data) {
                    csvWriter.writeRecord(csvRow, true);
                }
            } catch (IOException e) {
                throw new RuntimeException("写CSV失败:", e);
            } finally {
                if (csvWriter != null) {
                    csvWriter.close();
                }
            }
        }
    }

    /**
     * 读 CSV
     * @param filePath 文件路径
     * @param separator 分隔符
     * @param charset 编码
     * @return 数据 {@link List}
     */
    public static List<String[]> readCsv(String filePath, char separator, String charset) throws RuntimeException {
        if (!filePath.endsWith(CSV_DEFAULT_SUFFIX)) {
            throw new RuntimeException("读取文件格式需为.CSV结尾!");
        }
        List<String[]> strList = new ArrayList<>();
        CsvReader csvReader = null;
        try {
            csvReader = new CsvReader(filePath, separator, Charset.forName(charset));
            while (csvReader.readRecord()) {
                strList.add(csvReader.getValues());
            }
        } catch (IOException e) {
            throw new RuntimeException("读CSV失败:", e);
        } finally {
            if (csvReader != null) {
                csvReader.close();
            }
        }
        return strList;
    }

    /**
     * 读 CSV
     * @param filePath 文件路径
     * @param separator 分隔符
     * @param charset 编码
     * @return 数据 List<List<String>>
     */
    public static List<List<String>> readCsvByList(String filePath, char separator, String charset) throws RuntimeException {
        List<String[]> data = readCsv(filePath, separator, charset);
        List<List<String>> dataList = new ArrayList<>();
        for (String[] row : data) {
            dataList.add(new ArrayList<>(Arrays.asList(row)));
        }
        return dataList;
    }

    /**
     * 获取签名 CSV最后一行第一个
     * @param filePath 文件路径
     * @param separator 分隔符
     * @param charset 编码
     * @return 签名
     */
    public static String getSignByCsvLast(String filePath, char separator, String charset) {
        List<String[]> csvStrS= readCsv(filePath, separator, charset);
        String lastStr = null;
        if (csvStrS.size() > 0) {
            lastStr = csvStrS.get(csvStrS.size() - 1)[0];
        }
        return lastStr;
    }

    /**
     * 读指定行数列数的CSV
     * @param filePath 文件路径
     * @param separator 分隔符
     * @param charset 编码
     * @param row 行数
     * @param column 列数
     * @return #String
     * @throws RuntimeException 异常
     */
    public static String readCsvByRowCol(String filePath, char separator, String charset, int row, int column) throws RuntimeException {
        List<String[]> strS = readCsv(filePath, separator, charset);
        checkCsv(row, column, strS);
        return strS.get(row)[column];
    }

    /**
     * 读指定行数列数的CSV
     * @param filePath 文件路径
     * @param row 行数
     * @param column 列数
     * @return #String
     * @throws RuntimeException 异常
     */
    public static String readCsvByRowCol(String filePath, int row, int column) throws RuntimeException {
         return readCsvByRowCol(filePath, CSV_DEFAULT_SEPARATOR, CSV_DEFAULT_CHARSET, row, column);
    }

    /**
     * 检查CSV文件
     * @param row 行数
     * @param column 列数
     * @param data 数据 #List<String[]>
     * @throws RuntimeException 异常
     */
    public static void checkCsv(int row, int column, List<String[]> data) throws RuntimeException {
        if (data != null && data.size() != 0) {
            if (data.size() < row) {
                throw new RuntimeException("行数超限:" + row);
            }
            if (data.get(row - 1).length < column) {
                throw new RuntimeException("列数超限:" + column);
            }
        } else {
            throw new RuntimeException("CSV数据为空！");
        }
    }

    /**
     * 计算字符串HASH
     * @param str 字符串数据
     * @return hash
     */
    public static String getHashByStr(String str, String algorithm) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            byte[] digest = messageDigest.digest(str.getBytes(CSV_DEFAULT_CHARSET));
            // hash数组为32位，否则就hash失败
            // 将计算得到的字节数组转为十六进制--并转换成字符串
            return digest.length != 32 ? null : Hex.toHexString(digest);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 文件是否存在
     * @param path 文件/文件夹 路径
     * @return boolean
     */
    public static boolean isExist(String path) {
        if (CommonUtils.isEmpty(path)) {
            return false;
        }
        File file = new File(path);
        return isExist(file);
    }

    /**
     * 是否是文件
     * @param path 文件/文件夹 路径
     * @return boolean
     */
    public static boolean isFile(String path) {
        if (isExist(path)) {
            return false;
        }
        File file = new File(path);
        return file.isFile();
    }

    /**
     * 是否是文件
     * @param file 文件 路径
     * @return boolean
     */
    public static boolean isFile(File file) {
        if (file == null) {
            return false;
        }
        return file.isFile();
    }

    /**
     * 文件是否存在
     * @param file 文件
     * @return boolean
     */
    public static boolean isExist(File file) {
        if (file == null) {
            return false;
        }
        return file.exists();
    }

    /**
     * 创建文件/文件夹
     * @param path 文件/文件夹 路径
     */
    public static boolean creatFile(String path) {
        File file = new File(path);
        return file.exists() || file.mkdirs();
    }

    /**
     * 获取文件绝对路径
     * @param path 文件路径
     * @return <code>CanonicalPath</code>
     */
    public static String getCanonicalPath(String path) {
        if (isExist(path)) {
            File file = new File(path);
            return getCanonicalPath(file);
        }
        File file = new File(".");
        return getCanonicalPath(file);
    }

    /**
     * 获取文件绝对路径
     * @param file 文件
     * @return <code>CanonicalPath</code>
     */
    public static String getCanonicalPath(File file) {
        try {
            if (file != null) {
                return file.getCanonicalPath();
            }
            file = new File(".");
            return file.getCanonicalPath();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获取文件夹下所有文件
     * @param filePath 文件夹路径
     * @return List<File>
     */
    public static List<File> listFile(String filePath) {
        return listFile(new File(filePath));
    }

    /**
     * 获取文件夹下所有文件
     * @param file 文件夹
     * @return List<File>
     */
    public static List<File> listFile(File file) {
        if (isExist(file) && file.isDirectory()) {
            File[] files = file.listFiles();
            if (!CommonUtils.isEmpty(files)) {
                return new ArrayList<>(Arrays.asList(files));
            }
        }
        return new ArrayList<>();
    }

    /**
     * 读取文件
     * @param clz 类
     * @param file 文件
     * @return <code>NoteCol</code>
     */
    public static <T>T readFile(Class<T> clz, File file) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            Object o = inputStream.readObject();
            if (clz.isInstance(o)) {
                return clz.cast(o);
            }
            return null;
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException("readFile exception", e);
        }
    }

    /**
     * 写文件
     * @param o 数据
     * @param file 文件
     */
    public static void writeFile(Object o, File file) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(o);
        } catch (IOException e) {
            throw new IllegalArgumentException("writeFile exception", e);
        }
    }
}
