package com.micro.common.util.other;

import com.alibaba.fastjson.JSON;
import com.micro.common.exception.BusinessException;
import com.micro.common.exception.DataParseException;
import com.micro.common.interfaces.Function;
import com.micro.common.interfaces.FunctionEmpty;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: www
 * @description: 通用工具
 * @author: XiongJiaMin
 * @create: 2021-10-29 16:16
 **/
@SuppressWarnings("unused")
public class CommonUtils implements Serializable {

    private static final long serialVersionUID = 2062570375066533650L;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat();

    /**
     * <p>
     *     字符串 null 比较 且 长度不为 0
     * </p>
     * @description 是否为 null
     * @create: 2021/10/29
     * @param o 对象
     * @return boolean
     */
    public static boolean isNull(Object o) {
        return null == o || o.toString().length() == 0;
    }

    /**
     * @description 是否不为 null
     * @create: 2021/10/29
     * @param o 对象
     * @return boolean
     */
    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

    /**
     * <p>
     *     针对字符串, 集合类, 迭代器等有特殊处理
     * </p>
     * @description 是否为空
     * @create: 2021/10/29
     * @param o 对象
     * @return boolean
     */
    public static boolean isEmpty (Object o) {
        if (null != o) {
            if (o instanceof String) {
                return ((String) o).length() == 0;
            } else if (o instanceof Iterable) {
                return isEmpty((Iterable<?>)o);
            } else if (o instanceof Map<?, ?>) {
                return isEmpty((Map<?, ?>) o);
            } else if (o instanceof Iterator<?>) {
                return isEmpty((Iterator<?>)o);
            } else if (o.getClass().isArray()) {
                return Array.getLength(o) == 0;
            }
            return false;
        }
        return true;
    }

    /**
     * @description 是否不为空 Object
     * @create: 2021/10/29
     * @param o 对象
     * @return boolean
     */
    public static boolean isNotEmpty (Object o) {
        return !isEmpty(o);
    }

    /**
     * @description 是否不为空 Map
     * @create: 2021/10/29
     * @param map 键值对
     * @return boolean
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return null == map || map.isEmpty();
    }

    /**
     * @description 是否不为空 迭代
     * @create: 2021/10/29
     * @param iterable 迭代
     * @return boolean
     */
    public static boolean isEmpty(Iterable<?> iterable) {
        return null == iterable || isEmpty(iterable.iterator());
    }

    /**
     * @description 是否不为空 迭代器
     * @create: 2021/10/29
     * @param iterator 迭代器
     * @return boolean
     */
    public static boolean isEmpty(Iterator<?> iterator) {
        return null == iterator || !iterator.hasNext();
    }

    /**
     * @description 为空执行方法并返回
     * @create: 2021/10/29
     * @param handler 执行器
     * @return T 自定义泛型实例
     */
    public static <T>T isEmptyExecute(T o, Function<T> handler) {
        return isEmpty(o) ? handler.handle() : o;
    }

    /**
     * @description 为空执行方法
     * @create: 2021/10/29
     * @param handler 执行器
     */
    public static void isEmptyExecute(Object o, FunctionEmpty handler) {
        if (isEmpty(o)) {
            handler.handle();
        }
    }

    /**
     * @description 格式化字符串
     * @create: 2021/11/5
     * <p>
     *      @param params "this is a %d", "test"
     *      @return "this is a test"
     * </p>
     */
    public static String formatStr(String... params) {
        String formatStr = "";
        if (!isEmpty(params) && params.length > 1) {
            String finalFormatStr = params[0];
            for (int i = 1; i < params.length; i++) {
                finalFormatStr = finalFormatStr.replaceFirst("%d",params[i]);
            }
            formatStr = finalFormatStr;
        }
        return formatStr;
    }

    /**
     * <p>
     *     日期字符串转换长整型
     *     @throws DataParseException 数据转换异常
     * </p>
     * @param dateStr 日期字符串
     * @param pattern 日期模板
     * @return long
     */
    public static long parseTimeStamp (String dateStr, String pattern) {
        try {
            if (isParamsEmpty(dateStr, pattern)) {
                throw new DataParseException("The parameters of the method 'parseTimeStamp' must not be empty !");
            }
            dateFormat.applyPattern(pattern);
            return dateFormat.parse(dateStr).getTime();
        } catch (ParseException e) {
            throw new DataParseException(e, ("The dateStr " + dateStr + " does not match pattern " + pattern));
        }
    }

    /**
     * <p>
     *     @see Date 日期字符串转换日期
     *     @throws DataParseException 数据转换异常
     * </p>
     * @param dateStr 日期字符串
     * @param pattern 日期模板
     * @return date
     */
    public static Date parseTimeDate (String dateStr, String pattern) {
        try {
            if (isParamsEmpty(dateStr, pattern)) {
                throw new DataParseException("The parameters of the method 'parseTimeDate' must not be empty !");
            }
            dateFormat.applyPattern(pattern);
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new DataParseException(e, ("The dateStr " + dateStr + " does not match pattern " + pattern));
        }
    }

    /**
     * <p>
     *     时间戳转换日期字符串
     * </p>
     * @param timeStamp 时间戳
     * @param pattern 日期模板
     * @return 日期字符串
     */
    public static String parseTimeStr (long timeStamp, String pattern) {
        if (isParamsEmpty(timeStamp, pattern)) {
            throw new DataParseException("The parameters of the method 'parseTimeStr' must not be empty !");
        }
        dateFormat.applyPattern(pattern);
        return dateFormat.format(new Date(timeStamp));
    }

    /**
     * <p>
     *     @see Date 日期戳转换日期字符串
     * </p>
     * @param date 日期
     * @param pattern 日期模板
     * @return 日期字符串
     */
    public static String parseTimeStr (Date date, String pattern) {
        if (isParamsEmpty(date, pattern)) {
            throw new DataParseException("The parameters of the method 'parseTimeStr' must not be empty !");
        }
        dateFormat.applyPattern(pattern);
        return dateFormat.format(date);
    }

    /**
     * <p>
     *     多参检查空值
     *     一个参数为空即 false
     * </p>
     * @param params 多参
     * @return boolean
     */
    public static boolean isParamsEmpty (Object... params) {
        boolean flag = false;
        if (isEmpty(params)) {
            flag = true;
        } else {
            for (Object param : params) {
                if (isEmpty(param)) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * 根据字节长度截取字符串
     * @param msg 字符串
     * @param byteLength 长度
     * @param charset 编码
     * @return 截取后的字符串
     */
    public static String subStringByBytes(String msg, int byteLength, String charset) {
        char[] chars = msg.toCharArray();
        StringBuilder sb = new StringBuilder();
        try {
            for (char str : chars) {
                int strLen = String.valueOf(str).getBytes(charset).length;
                if (byteLength - strLen >= 0) {
                    sb.append(str);
                    byteLength = byteLength - strLen;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return sb.toString();
    }

    /**
     * 流关闭使用的通用类
     * @param closeable 流
     */
    public static void closeStream(AutoCloseable... closeable) {
        if (closeable != null && closeable.length > 0) {
            for (AutoCloseable autoCloseable : closeable) {
                try {
                    autoCloseable.close();
                } catch (Exception e) {
                    throw new BusinessException(e, "流关闭异常:");
                }
            }
        }
    }

    public static void insertSort(int[] data) {
        int temp, j;
        for (int i = 0; i < data.length; i++) {
            temp = data[i];
            //从右向左在有序区data[0 ... i-1]中找 data[i] 的插入位置
            j = i - 1;
            while (j >= 0 && temp < data[j]) {
                //将小于data[j]的元素后移
                data[j + 1] = data[j];
                j--;
            }
            //在 j+1 处插入 data[i]
            data[j + 1] = temp;
        }
    }

    public static void sort(int[] data) {
        int temp, j;
        for (int i = 0; i < data.length; i++) {
            temp = data[i];
            //从右向左在有序区data[0 ... i-1]中找 data[i] 的插入位置
            j = i - 1;
            while (j >= 0 && temp > data[j]) {
                //将大于data[j]的元素后移
                data[j + 1] = data[j];
                j--;
            }
            //在 j+1 处插入 data[i]
            data[j + 1] = temp;
        }
    }
}
