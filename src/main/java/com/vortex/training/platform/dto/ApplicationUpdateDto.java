package com.vortex.training.platform.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : light
 * @date: 2020/11/2 13:42
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("更改应用训练状态")
public class ApplicationUpdateDto {

    @ApiModelProperty(value = "主键", required = true)
    private Integer applicationId;

    @ApiModelProperty(value = "操作类型：1-停止训练；2-重新训练")
    private Byte operateType;
}
