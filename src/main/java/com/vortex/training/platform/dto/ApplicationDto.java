package com.vortex.training.platform.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author : light
 * @date: 2020/11/2 11:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("新增训练模型参数")
public class ApplicationDto implements Serializable {

    private static final long serialVersionUID = 1L;

    // TODO: 2020/11/2 类型暂定。
    @ApiModelProperty(value = "应用类型", required = true)
    private String applicationType;

    @ApiModelProperty(value = "模型id", required = true)
    private Integer modelId;

    @ApiModelProperty(value = "数据集id", required = true)
    private Integer datasetId;

    @ApiModelProperty(value = "标签id，多个用英文逗号隔开", required = true)
    private String labelIds;
}
