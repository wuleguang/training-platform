package com.vortex.training.platform.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author : light
 * @date: 2020/11/2 11:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("分页查询Application参数")
public class ApplicationFindDto implements Serializable {
    @ApiModelProperty(value = "模型id", required = true)
    private Integer modelId;

    @ApiModelProperty(value = "应用类型")
    private String applicationType;
}
