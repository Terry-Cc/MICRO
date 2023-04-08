package com.micro.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author XiongJiaMin
 * @apiNote 用户
 * @since 2022-09-07 16:42
 **/
@Data
@NoArgsConstructor
@ApiModel(value = "用户信息")
public class User implements Serializable {

    private static final long serialVersionUID = 8559307644684809928L;

    @ApiModelProperty(value = "创建人", readOnly = true)
    private String createdBy;

    @ApiModelProperty(value = "创建时间", readOnly = true)
    private Date createdDate;

    @ApiModelProperty(value = "更新人", readOnly = true)
    private String updatedBy;

    @ApiModelProperty(value = "更新时间", readOnly = true)
    private Date updatedDate;

    @ApiModelProperty(value = "用户编号", readOnly = true)
    private String userId;

    @ApiModelProperty(value = "用户名", readOnly = true)
    private String userName;

    @ApiModelProperty(value = "密码", readOnly = true)
    private String password;

    @ApiModelProperty(value = "最后登录的时间", readOnly = true)
    private Date lastLoginDate;

    @ApiModelProperty(value = "访问次数", readOnly = true)
    private Integer visitTime;
}
