package com.micro.services.provider.config;

import com.micro.common.constant.DateTimePattern;
import com.micro.common.util.idutils.SnowFlakeIdGenerator;
import com.micro.common.util.other.DateTimeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: www
 * @description: 雪花ID配置类
 * @author: XiongJiaMin
 * @create: 2021-11-26 17:01
 **/
@Configuration
public class SnowFlakeConfig {

    @Value("${SnowFlakeIdUtil.startDate}")
    private String startDate;

    @Value("${SnowFlakeIdUtil.workIdDef}")
    private long workId;

    @Value("${SnowFlakeIdUtil.dataCenterIdDef}")
    private long dataCenterId;

    @Bean
    public SnowFlakeIdGenerator build() {
        return SnowFlakeIdGenerator.build(workId, dataCenterId, DateTimeUtils.parseDate(startDate, DateTimePattern.YYYY_MM_DD_HH_MM_SS).getTime());
    }
}
