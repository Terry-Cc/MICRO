package com.micro.common.web.vo;

import com.micro.common.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: www
 * @description: 统一响应
 * @author: XiongJiaMin
 * @create: 2021-09-24 17:52
 **/
@Data
@AllArgsConstructor
@ApiModel(value = "响应封装")
public class CommResponse implements Serializable {

    private static final long serialVersionUID = 1972569994027653839L;

    @ApiModelProperty(value = "响应编码")
    private String rspCode;

    @ApiModelProperty(value = "响应信息")
    private String rspMsg;

    @ApiModelProperty(value = "响应数据")
    private Map<String, Object> data;

    /**
     * 全参默认构造
     * @param resultEnum 响应枚举
     * @param data 响应参数
     */
    public CommResponse (ResultEnum resultEnum, Map<String, Object> data) {
        this(resultEnum.getRspCode(), resultEnum.getRspMsg(), data);
    }

    /**
     * 默认无参构造, 空响应
     */
    public CommResponse () {
        this(ResultEnum.COMMON_UNKNOWN_RESP);
    }

    /**
     * 默认构造
     * @param resultEnum 响应枚举
     */
    public CommResponse (ResultEnum resultEnum) {
        this(resultEnum, new HashMap<>());
    }

    /**
     * <p>
     *     修改响应编码和信息
     * </p>
     * @param resultEnum 响应枚举
     * @return this
     */
    public CommResponse setResult (ResultEnum resultEnum) {
        this.setRspCode(resultEnum.getRspCode());
        this.setRspMsg(resultEnum.getRspMsg());
        return this;
    }

    /**
     * <p>
     *     设置响应数据
     * </p>
     * @param data 数据
     * @return this
     */
    public CommResponse setAllData (Map<String, Object> data) {
        this.data = data;
        return this;
    }

    /**
     * <p>
     *     添加响应数据
     * </p>
     * @param paramName 参数名
     * @param param 参数
     * @return this
     */
    public CommResponse addData (String paramName, Object param) {
        this.getData().put(paramName, param);
        return this;
    }

    /**
     * <p>
     *     添加相应数据 (全)
     * </p>
     * @param data 参数集
     * @return this
     */
    public CommResponse addAllData (Map<String, Object> data) {
        this.getData().putAll(data);
        return this;
    }
}
