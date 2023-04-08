package com.micro.customer.controller;

import com.micro.common.anno.ApiHandleAll;
import com.micro.common.web.vo.CommRequest;
import com.micro.common.web.vo.CommResponse;
import com.micro.www.api.ISystemInfoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: micro
 * @description: 系统信息
 * @author: XiongJiaMin
 * @create: 2021-12-16 17:38
 **/
@RestController
@ApiHandleAll
public class SysInfoController {

    @DubboReference
    ISystemInfoService iSystemInfoService;

    @RequestMapping(value = "/sys_info", method = RequestMethod.POST)
    public CommResponse getSysInfo (CommRequest request) {
        return iSystemInfoService.getSystemInfo(request);
    }
}
