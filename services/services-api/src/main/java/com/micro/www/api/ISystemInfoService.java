package com.micro.www.api;

import com.micro.common.exception.BusinessException;
import com.micro.common.web.vo.CommRequest;
import com.micro.common.web.vo.CommResponse;

import java.sql.SQLException;

/**
 * @program: micro
 * @description: 系统参数查询 (对外暴露的接口), 服务提供方不会直接使用源项目
 * @author: XiongJiaMin
 * @create: 2021-12-03 17:53
 **/
public interface ISystemInfoService {

    CommResponse getSystemInfo (CommRequest request) throws BusinessException;

    void testAop () throws BusinessException;
}
