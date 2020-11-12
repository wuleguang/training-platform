package com.vortex.training.platform.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vortex.training.platform.entity.Dataset;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author : light
 * @date: 2020/11/2 10:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("分页查询model参数")
public class DatasetFindDto extends Page<Dataset> implements Serializable {

    @ApiModelProperty(value = "数据集名称")
    private String datasetName;

    @ApiModelProperty(value = "数据集类型：1-训练集，2-校验集")
    private Integer datasetType;
}
