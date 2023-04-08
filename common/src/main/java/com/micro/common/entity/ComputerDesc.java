package com.micro.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @program: www
 * @description: 客户端详情
 * @author: XiongJiaMin
 * @create: 2021-09-26 16:12
 **/
@Data
@NoArgsConstructor
@ApiModel(value = "客户端系统详细")
public class ComputerDesc implements Serializable {

    private static final long serialVersionUID = 6574479689669338313L;

    @ApiModelProperty(value = "用户名", readOnly = true)
    private String userName;

    @ApiModelProperty(value = "用户域名", readOnly = true)
    private String userDnsDomain;

    @ApiModelProperty(value = "计算机名", readOnly = true)
    private String computerName;

    @ApiModelProperty(value = "计算机域名", readOnly = true)
    private String userDomain;

    @ApiModelProperty(value = "操作系统类型", readOnly = true)
    private String osType;

    @ApiModelProperty(value = "操作系统名", readOnly = true)
    private String osName;

    @ApiModelProperty(value = "操作系统架构", readOnly = true)
    private String osArch;

    @ApiModelProperty(value = "操作系统版本", readOnly = true)
    private String osVersion;

    @ApiModelProperty(value = "ip", readOnly = true)
    private String hostAddress;

    @ApiModelProperty(value = "mac", readOnly = true)
    private String macAddress;
}
