package com.vortex.training.platform.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author : light
 * @date: 2020/10/30 13:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("新增修改模型参数")
public class ModelDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键，新增不必传，修改必传")
    private Integer modelId;

    /**
     * 模型名称
     */
    @ApiModelProperty(value = "模型名称", required = true)
    private String modelName;

    /**
     * 模型描述
     */
    @ApiModelProperty(value = "模型描述", required = true)
    private String modelDescribe;

    /**
     * 模型类别：图像分类模型、目标检测模型
     */
    @ApiModelProperty(value = "模型类别：1.图像分类模型 2.目标检测模型。新增必传，无法修改")
    private Integer modelType;

}
