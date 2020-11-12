package com.vortex.training.platform.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : light
 * @date: 2020/11/2 15:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("应用详情")
public class ApplicationDetailDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id", required = true)
    private Integer applicationId;

    // TODO: 2020/11/2 类型暂定。
    @ApiModelProperty(value = "应用类型", required = true)
    private String applicationType;

    @ApiModelProperty(value = "版本", required = true)
    private String applicationVersion;

    @ApiModelProperty(value = "训练状态：1-训练中；2-训练停止；3-训练完成", required = true)
    private Integer trainingStatus;

    @ApiModelProperty(value = "训练开始时间", required = true)
    private Date trainingStartTime;

    @ApiModelProperty(value = "训练预计结束时间", required = true)
    private Date trainingEndTime;

    @ApiModelProperty(value = "评估报告", required = true)
    private String appraisalReport;

    @ApiModelProperty(value = "模型id", required = true)
    private Integer modelId;

    @ApiModelProperty(value = "数据集id", required = true)
    private Integer datasetId;

    @ApiModelProperty(value = "数据集", required = true)
    private DatasetDetailDto datasetDetail;

    @ApiModelProperty(value = "标签id，多个用英文逗号隔开", required = true)
    private String labelIds;
}
