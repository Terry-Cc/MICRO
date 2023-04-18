package com.micro.common.util.other;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  时间和日期处理工具类
 * </p>
 * @since 2023/4/18 13:51
 */
@SuppressWarnings("unused")
public class DateTimeUtils {

    /**
     * @since 2023/4/18 14:12
     * @description <p>
     *  根据日期时间模板获取默认时区的格式器
     * </p>
     * @param pattern 模板 {@link com.micro.common.constant.DateTimePattern}
     * @return {@link java.time.format.DateTimeFormatter}
     */
    public static DateTimeFormatter getFormatter(String pattern) {
        return getFormatter(pattern, ZoneId.systemDefault());
    }

    /**
     * @since 2023/4/18 14:12
     * @description <p>
     *  根据日期时间模板获取默认时区的格式器,自己指定时区
     * </p>
     * @param pattern 模板 {@link com.micro.common.constant.DateTimePattern}
     * @param zoneId 时区 {@link java.time.ZoneId}
     * @return {@link java.time.format.DateTimeFormatter}
     */
    public static DateTimeFormatter getFormatter(String pattern, ZoneId zoneId) {
        return DateTimeFormatter.ofPattern(pattern).withZone(zoneId);
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  date转LocalDateTime
     * </p>
     * @param date 日期 {@link java.util.Date}
     * @return LocalDateTime {@link java.time.LocalDateTime}
     */
    public static LocalDateTime parseLocalDateTime(Date date) {
        return parseLocalDateTime(date, ZoneId.systemDefault());
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  date转LocalDateTime,自己指定时区
     * </p>
     * @param date 日期 {@link java.util.Date}
     * @param zoneId 时区 {@link java.time.ZoneId}
     * @return LocalDateTime {@link java.time.LocalDateTime}
     */
    public static LocalDateTime parseLocalDateTime(Date date, ZoneId zoneId) {
        return LocalDateTime.ofInstant(date.toInstant(), zoneId);
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  date转LocalDate
     * </p>
     * @param date 日期 {@link java.util.Date}
     * @return LocalDate {@link java.time.LocalDate}
     */
    public static LocalDate parseLocalDate(Date date) {
        return parseLocalDateTime(date).toLocalDate();
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  date转LocalTime
     * </p>
     * @param date 日期 {@link java.util.Date}
     * @return LocalTime {@link java.time.LocalTime}
     */
    public static LocalTime parseLocalTime(Date date) {
        return parseLocalDateTime(date).toLocalTime();
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  localDateTime转Date
     * </p>
     * @param localDateTime 日期时间 {@link java.time.LocalDateTime}
     * @return Date {@link java.util.Date}
     */
    public static Date parseDate(LocalDateTime localDateTime) {
        return parseDate(localDateTime, ZoneId.systemDefault());
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  localDateTime转Date,自己指定时区
     * </p>
     * @param localDateTime 日期时间 {@link java.time.LocalDateTime}
     * @param zoneId 时区 {@link java.time.ZoneId}
     * @return Date {@link java.util.Date}
     */
    public static Date parseDate(LocalDateTime localDateTime, ZoneId zoneId) {
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  根据模板把字符串转换为LocalDate
     * </p>
     * @param charSequence 日期字符串 {@link java.lang.CharSequence}
     * @param pattern 模板 {@link com.micro.common.constant.DateTimePattern}
     * @return LocalDate {@link java.time.LocalDate}
     */
    public static LocalDate parseLocalDate(CharSequence charSequence, String pattern) {
        return LocalDate.parse(charSequence, getFormatter(pattern));
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  根据模板把字符串转换为LocalTime
     * </p>
     * @param charSequence 日期字符串 {@link java.lang.CharSequence}
     * @param pattern 模板 {@link com.micro.common.constant.DateTimePattern}
     * @return LocalTime {@link java.time.LocalTime}
     */
    public static LocalTime parseLocalTime(CharSequence charSequence, String pattern) {
        return LocalTime.parse(charSequence, getFormatter(pattern));
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  根据模板把字符串转换为LocalDateTime
     * </p>
     * @param charSequence 日期字符串 {@link java.lang.CharSequence}
     * @param pattern 模板 {@link com.micro.common.constant.DateTimePattern}
     * @return LocalDateTime {@link java.time.LocalDateTime}
     */
    public static LocalDateTime parseLocalDateTime(CharSequence charSequence, String pattern) {
        return LocalDateTime.parse(charSequence, getFormatter(pattern));
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  根据模板把字符串转换为Instant
     * </p>
     * @param charSequence 日期字符串 {@link java.lang.CharSequence}
     * @param pattern 模板 {@link com.micro.common.constant.DateTimePattern}
     * @return Instant {@link java.time.Instant}
     */
    public static Instant parseInstant(CharSequence charSequence, String pattern) {
        return parseInstant(charSequence, pattern, ZoneId.systemDefault());
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  根据模板把字符串转换为Instant,自己指定时区
     * </p>
     * @param charSequence 日期字符串 {@link java.lang.CharSequence}
     * @param pattern 模板 {@link com.micro.common.constant.DateTimePattern}
     * @param zoneId 时区 {@link java.time.ZoneId}
     * @return Instant {@link java.time.Instant}
     */
    public static Instant parseInstant(CharSequence charSequence, String pattern, ZoneId zoneId) {
        return parseLocalDateTime(charSequence, pattern).atZone(zoneId).toInstant();
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  根据模板把字符串转换为Date
     * </p>
     * @param charSequence 日期字符串 {@link java.lang.CharSequence}
     * @param pattern 模板 {@link com.micro.common.constant.DateTimePattern}
     * @return Date {@link java.util.Date}
     */
    public static Date parseDate(CharSequence charSequence, String pattern) {
        return Date.from(parseInstant(charSequence, pattern));
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  根据临时存储器和模板转换成字符串
     * </p>
     * @param temporal 临时存储器 {@link java.time.temporal.TemporalAccessor}
     * @param pattern 模板 {@link com.micro.common.constant.DateTimePattern}
     * @return String {@link java.lang.String}
     */
    public static String format(TemporalAccessor temporal, String pattern) {
        return getFormatter(pattern).format(temporal);
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  根据日期和模板转换成字符串
     * </p>
     * @param date 日期 {@link java.util.Date}
     * @param pattern 模板 {@link com.micro.common.constant.DateTimePattern}
     * @return String {@link java.lang.String}
     */
    public static String format(Date date, String pattern) {
        return format(date.toInstant(), pattern);
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  获取指定日期的当天起始日期
     * </p>
     * @param date 日期 {@link java.util.Date}
     * @return date 起始日期 {@link java.util.Date}
     */
    public static Date getStartOfDay(Date date) {
        return parseDate(parseLocalDate(date).atStartOfDay());
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  获取指定日期的当天终止日期
     * </p>
     * @param date 日期 {@link java.util.Date}
     * @return date 终止日期 {@link java.util.Date}
     */
    public static Date getEndOfDay(Date date) {
        return parseDate(LocalDateTime.of(parseLocalDate(date), LocalTime.MAX));
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  获取当天起始日期
     * </p>
     * @return date 起始日期 {@link java.util.Date}
     */
    public static Date getStartOfDay() {
        return parseDate(LocalDate.now().atStartOfDay());
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  获取当天终止日期
     * </p>
     * @return date 终止日期 {@link java.util.Date}
     */
    public static Date getEndOfDay() {
        return parseDate(LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  获取昨天起始日期
     * </p>
     * @return date 昨天起始日期 {@link java.util.Date}
     */
    public static Date getStartOfYesterday() {
        return parseDate(LocalDate.now().minusDays(1L).atStartOfDay());
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  获取昨天终止日期
     * </p>
     * @return date 昨天终止日期 {@link java.util.Date}
     */
    public static Date getEndOfYesterday() {
        return parseDate(LocalDateTime.of(LocalDate.now().minusDays(1L), LocalTime.MAX));
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  获取本周起始日期
     * </p>
     * @return date 本周起始日期 {@link java.util.Date}
     */
    public static Date getStartOfWeek() {
        LocalDate localDate = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return parseDate(localDate.atStartOfDay());
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  获取本周终止日期
     * </p>
     * @return date 本周终止日期 {@link java.util.Date}
     */
    public static Date getEndOfWeek() {
        LocalDate localDate = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return parseDate(LocalDateTime.of(localDate, LocalTime.MAX));
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  获取本月起始日期
     * </p>
     * @return date 本月起始日期 {@link java.util.Date}
     */
    public static Date getStartOfMonth() {
        LocalDate localDate = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        return parseDate(localDate.atStartOfDay());
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  获取本月终止日期
     * </p>
     * @return date 本月终止日期 {@link java.util.Date}
     */
    public static Date getEndOfMonth() {
        LocalDate localDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        return parseDate(LocalDateTime.of(localDate, LocalTime.MAX));
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  获取本年起始日期
     * </p>
     * @return date 本年起始日期 {@link java.util.Date}
     */
    public static Date getStartOfYear() {
        LocalDate localDate = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
        return parseDate(localDate.atStartOfDay());
    }

    /**
     * @since 2023/4/18 14:19
     * @description <p>
     *  获取本年终止日期
     * </p>
     * @return date 本年终止日期 {@link java.util.Date}
     */
    public static Date getEndOfYear() {
        LocalDate localDate = LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
        return parseDate(LocalDateTime.of(localDate, LocalTime.MAX));
    }
}
