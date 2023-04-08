package com.micro.common.util.idutils;

import com.micro.common.exception.InspectedParameterException;
import com.micro.common.util.other.CommonUtils;

import java.io.Serializable;

/**
 * @program: www
 * @description: ID工厂
 * @author: XiongJiaMin
 * @create: 2021-11-01 11:09
 * <p>
 *     SnowFlake 结构:
 *     一位标识: 表示正负 正数 0 负数 1
 *     41位时间戳: 时间 long 当前时间减开始时间
 *     10位机器标识: 5位数据ID 加 5位机器ID
 *     12位序列: 毫秒内的计数, 每个节点每毫秒产生 4096 个序号
 *     加起来刚好64位, 整体以时间自增, 分布式系统可使用机器ID区分, 效率高
 * </p>
 **/
@SuppressWarnings("all")
public class SnowFlakeIdGenerator implements Serializable {

    private static final long serialVersionUID = -1946084153633050532L;

    /**
     * @description 机器ID
     * @create: 2021/11/5
     */
    private long workId;

    /**
     * @description 机器ID占用位数
     * @create: 2021/11/26
     */
    private final static long workIdBit = 5L;

    /**
     * @description 机器ID最大值
     * @create: 2021/11/25
     */
    private final static long workIdMax = -1L ^ (-1L << workIdBit);

    /**
     * @description 数据标识ID
     * @create: 2021/11/5
     */
    private long dataCenterId;

    /**
     * @description 数据标识ID占用位数
     * @create: 2021/11/26
     */
    private final static long dataCenterIdBit = 5L;

    /**
     * @description 数据标识ID最大值
     * @create: 2021/11/25
     */
    private final static long dataCenterIdMax = -1L ^ (-1L << dataCenterIdBit);

    /**
     * @description 上一个时间戳
     * @create: 2021/11/25
     */
    private long lastMilli = -1L;

    /**
     * @description 起始使用时间
     * @create: 2021/11/25
     */
    private long startTimeStamp;
    
    /**
     * @description 毫秒内进行序列
     * @create: 2021/11/25
     */
    private long sequence = 0L;

    /**
     * @description 毫秒序列占用位数
     * @create: 2021/11/26
     */
    private final static long sequenceBit = 12L;

    /**
     * @description 毫秒序列使用掩码
     * @create: 2021/11/25
     */
    private final static long sequenceMask = -1L ^ (-1L << sequenceBit);

    /**
     * @description 机器ID移位数
     * @create: 2021/11/26
     */
    private final static long workIdShift = sequenceBit;

    /**
     * @description 数据ID移位数
     * @create: 2021/11/26
     */
    private final static long dataCenterIdShift = workIdBit + sequenceBit;

    /**
     * @description 时间戳移位数
     * @create: 2021/11/26
     */
    private final static long timeStampShift = dataCenterIdShift + dataCenterIdBit;

    /**
     * <p>
     *     雪花工具类通用构造
     * </p>
     * @param workId 机器ID
     * @param dataCenterId 数据标识ID
     * @return SnowFlakeIdUtil
     */
    public static SnowFlakeIdGenerator build(long workId, long dataCenterId, long startTimeStamp) {
        return new SnowFlakeIdGenerator(workId, dataCenterId, startTimeStamp);
    }

    /**
     * <p>
     *     设置机器ID
     * </p>
     * @param workId 机器ID
     * @return SnowFlakeIdUtil
     */
    private SnowFlakeIdGenerator setWorkId (long workId) {
        this.workId = workId;
        return this;
    }

    /**
     * <p>
     *     设置数据标识ID
     * </p>
     * @param dataCenterId 数据标识ID
     * @return SnowFlakeIdUtil
     */
    private SnowFlakeIdGenerator setDataCenterId (long dataCenterId) {
        this.dataCenterId = dataCenterId;
        return this;
    }

    /**
     * <p>
     *     设置起始日期
     * </p>
     * @param startDate 起始日期
     * @return SnowFlakeIdUtil
     */
    private SnowFlakeIdGenerator setStartTimeStamp (long startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
        return this;
    }

    private SnowFlakeIdGenerator() {}

    /**
     * <p>
     *     私有全参构造, 有校验
     *     @throws InspectedParameterException
     * </p>
     * @param workId 机器ID
     * @param dataCenterId 数据标识ID
     */
    private SnowFlakeIdGenerator(long workId, long dataCenterId, long startTimeStamp) {
        if (workId > workIdMax || workId < 0L) {
            throw new InspectedParameterException(CommonUtils.formatStr("WorkId can't be greater than %d or less than 0", String.valueOf(workIdMax)));
        }
        if (dataCenterId > dataCenterIdMax || dataCenterId < 0L) {
            throw new InspectedParameterException(CommonUtils.formatStr("DataCenterId can't be greater than %d or less than 0", String.valueOf(dataCenterIdMax)));
        }
        if (CommonUtils.isEmpty(startTimeStamp) || startTimeStamp < 0L) {
            throw new InspectedParameterException("StartTimeStamp can't be empty or less than 0");
        }
        this.workId = workId;
        this.dataCenterId = dataCenterId;
        this.startTimeStamp = startTimeStamp;
    }

    /**
     * <p>
     *     获取当前时间戳
     * </p>
     * @return long
     */
    private long timeGenerate () {
        return System.currentTimeMillis();
    }

    /**
     * <p>
     *     获取下一个时间戳
     *     递归
     * </p>
     * @param currentMilli 当前时间戳
     * @return nextMilli 下一个时间戳
     */
    private long blockNextMilli (long currentMilli) {
        long nextMilli = timeGenerate();
        return nextMilli <= currentMilli ? blockNextMilli(currentMilli) : nextMilli;
    }

    /**
     * <p>
     *     ID生成
     * </p>
     * @return ID long
     * @throws InspectedParameterException
     */
    public synchronized long generateId () {
        long nextMilli = timeGenerate();
        if (nextMilli < lastMilli) {
            throw new InspectedParameterException("The system clock was fallback ! Please check !");
        }
        if (nextMilli == lastMilli) {
            sequence = (sequence + 1L) & sequenceMask;
            if (sequence == 0L) {
                nextMilli = blockNextMilli(lastMilli);
            }
        } else {
            sequence = 0L;
        }
        lastMilli = nextMilli;
        return ((nextMilli - startTimeStamp) << timeStampShift)
                | (dataCenterId << dataCenterIdShift)
                | (workId << workIdShift)
                | sequence;
    }
}
