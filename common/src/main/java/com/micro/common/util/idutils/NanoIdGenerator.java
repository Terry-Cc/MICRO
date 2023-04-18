package com.micro.common.util.idutils;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author XiongJiaMin
 * @apiNote NanoId 生成器 (UUID 替代方案)
 * @since 2023-02-08 16:34
 **/
@SuppressWarnings("unused")
public final class NanoIdGenerator {

    public static final SecureRandom DEFAULT_NUMBER_GENERATOR = new SecureRandom();

    public static final String EMPTY = "";

    public static final String DEFAULT_ALPHABET = "_-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String SPECIAL_ALPHABET = "~!@#$%^&*()_+-=;:,.<>{}[]\\/'\"";

    public static final String NUMBER_ALPHABET = "0123456789";

    public static final String UPPERCASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String LOWERCASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static final int DEFAULT_SIZE = 21;

    public static String randomId() {
        // 原生方法
        return NanoIdUtils.randomNanoId();
    }

    /**
     * @since 2023/4/17 22:57
     * @description <p>
     *  生成默认21位的随机ID，模板为：大小写字母，数字，特殊字符
     * </p>
     */
    public static String randomNanoId() {
        return randomNanoId(DEFAULT_NUMBER_GENERATOR, DEFAULT_ALPHABET.toCharArray(), DEFAULT_SIZE);
    }

    /**
     * @since 2023/4/17 22:57
     * @description <p>
     *  生成指定位数的随机ID，模板为：大小写字母，数字，特殊字符
     * </p>
     */
    public static String randomNanoId(int length) {
        return randomNanoId(DEFAULT_NUMBER_GENERATOR, DEFAULT_ALPHABET.toCharArray(), length);
    }

    /**
     * @since 2023/4/17 22:57
     * @description <p>
     *  生成指默认21位的随机ID，模板自定义
     * </p>
     */
    public static String randomNanoId(String... alphabets) {
        return randomNanoId(DEFAULT_NUMBER_GENERATOR, String.join(EMPTY, alphabets).toCharArray(), DEFAULT_SIZE);
    }

    /**
     * @since 2023/4/17 22:57
     * @description <p>
     *  生成指指定位数的随机ID，模板自定义
     * </p>
     */
    public static String randomNanoId(int length, String... alphabets) {
        return randomNanoId(DEFAULT_NUMBER_GENERATOR, String.join(EMPTY, alphabets).toCharArray(), length);
    }

    /**
     * @since 2023/4/17 22:56
     * @description <p>
     *  nanoId 生成模板方法,默认提供了四种实现：
     *  {@link #randomNanoId()}
     *  {@link #randomNanoId(int)}
     *  {@link #randomNanoId(String...)}
     *  {@link #randomNanoId(int, String...)}
     * </p>
     * @param alphabet 模板，默认为：{@link #DEFAULT_ALPHABET}
     * @param random 随机策略，默认为: {@link #DEFAULT_NUMBER_GENERATOR}
     * @param size ID生成时的长度，默认为： {@link #DEFAULT_SIZE}
     */
    public static String randomNanoId(final Random random, final char[] alphabet, final int size) {
        if (random == null) {
            throw new IllegalArgumentException("random cannot be null.");
        }
        if (alphabet == null) {
            throw new IllegalArgumentException("alphabet cannot be null.");
        }
        if (alphabet.length == 0 || alphabet.length >= 256) {
            throw new IllegalArgumentException("alphabet must contain between 1 and 255 symbols.");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("size must be greater than zero.");
        }
        final int mask = (2 << (int) Math.floor(Math.log(alphabet.length - 1) / Math.log(2))) - 1;
        final int step = (int) Math.ceil(1.6 * mask * size / alphabet.length);
        final StringBuilder idBuilder = new StringBuilder();
        while (true) {
            final byte[] bytes = new byte[step];
            random.nextBytes(bytes);
            for (int i = 0; i < step; i++) {
                final int alphabetIndex = bytes[i] & mask;
                if (alphabetIndex < alphabet.length) {
                    idBuilder.append(alphabet[alphabetIndex]);
                    if (idBuilder.length() == size) {
                        return idBuilder.toString();
                    }
                }
            }
        }
    }
}
