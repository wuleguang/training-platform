package com.vortex.training.platform.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author : light
 * @date: 2020/11/2 16:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("数据集详情")
public class DatasetDetailDto implements Serializable {
    @ApiModelProperty(value = "主键")
    private Integer datasetId;

    @ApiModelProperty(value = "数据集名称")
    private String datasetName;

    @ApiModelProperty(value = "数据集类型：1-训练集，2-校验集。新增必传，无法修改")
    private Integer datasetType;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "标签详情")
    private List<LabelDetailDto> labelDetailList;
}
