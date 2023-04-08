package com.micro.common.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

/**
 * @program: micro
 * @description: 统一请求
 * @author: XiongJiaMin
 * @create: 2022-03-25 10:26
 **/
@Data
@AllArgsConstructor
@ApiModel(value = "请求封装")
public class CommRequest implements Serializable {

    private static final long serialVersionUID = 6209282202107265815L;

    @ApiModelProperty(value = "测试入参ID")
    private String id;
}
