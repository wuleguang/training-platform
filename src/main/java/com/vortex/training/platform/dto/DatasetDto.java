package com.vortex.training.platform.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author : light
 * @date: 2020/11/2 10:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("新增修改数据集参数")
public class DatasetDto implements Serializable {

    @ApiModelProperty(value = "主键，新增不必传，修改必传")
    private Integer datasetId;

    @ApiModelProperty(value = "数据集名称", required = true)
    private String datasetName;

    @ApiModelProperty(value = "数据集类型：1-训练集，2-校验集。新增必传，无法修改")
    private Integer datasetType;
}
