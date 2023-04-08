package com.micro.xxljob.jobhandler;

import com.micro.common.exception.BusinessException;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.ShardingUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @program: micro
 * @description: 测试JOB类
 * @author: XiongJiaMin
 * @create: 2022-01-05 17:17
 **/
@JobHandler("microDemoJobHandler")
@Component
public class MicroDemoJobHandler extends IJobHandler {

    private final static Logger logger = LogManager.getLogger(MicroDemoJobHandler.class);

    @Override
    public ReturnT<String> execute(String s) throws BusinessException {
        // 可根据入参和责任人等设置自定义分片逻辑
        logger.info("job任务参数: " + s);
        // 分片参数
        ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
        XxlJobLogger.log("分片参数：当前分片序号 = {}, 总分片数 = {}", shardingVO.getIndex(), shardingVO.getTotal());
        logger.info("分片参数：当前分片序号 = {}, 总分片数 = {}", shardingVO.getIndex(), shardingVO.getTotal());
        // 业务逻辑
        for (int i = 0; i < shardingVO.getTotal(); i++) {
            if (i == shardingVO.getIndex()) {
                XxlJobLogger.log("第 {} 片, 命中分片开始处理", i);
                logger.info("第 {} 片, 命中分片开始处理", i);
            } else {
                XxlJobLogger.log("第 {} 片, 忽略", i);
                logger.info("第 {} 片, 忽略", i);
            }
        }
        return SUCCESS;
    }
}
