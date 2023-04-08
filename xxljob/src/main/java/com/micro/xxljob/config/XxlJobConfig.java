package com.micro.xxljob.config;

import com.micro.common.util.other.CommonUtils;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: micro
 * @description: 自动任务配置类
 * @author: XiongJiaMin
 * @create: 2022-01-05 15:41
 **/
@Configuration
public class XxlJobConfig {

    private final static Logger logger = LogManager.getLogger(XxlJobConfig.class);

    @Value("${xxl.job.admin.addr}")
    private String adminAddresses;

    @Value("${xxl.job.executor.app}")
    private String appName;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${xxl.job.executor.log.path}")
    private String logPath;

    @Value("${xxl.job.executor.log.retention.days}")
    private int logRetentionDays;

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info(CommonUtils.formatStr("xxl job create starter as : adminAddresses-%d appName-%d ip-%d port-%d", adminAddresses, appName, ip, String.valueOf(port)));
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppName(appName);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }
}
