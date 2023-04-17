package com.micro.services.provider.config;

import com.micro.common.constant.Constants;
import com.micro.common.util.idutils.SnowFlakeIdGenerator;
import com.micro.common.util.other.CommonUtils;
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
        return SnowFlakeIdGenerator.build(workId, dataCenterId, CommonUtils.parseTimeStamp(startDate, Constants.EXACT_DATE));
    }
}
