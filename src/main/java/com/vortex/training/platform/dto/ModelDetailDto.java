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
 * @date: 2020/11/2 15:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("模型详情")
public class ModelDetailDto implements Serializable {

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
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 模型类别：图像分类模型、目标检测模型
     */
    @ApiModelProperty(value = "模型类别：1.图像分类模型 2.目标检测模型。新增必传，无法修改")
    private Integer modelType;

    /**
     * 应用详情列表
     */
    @ApiModelProperty(value = "应用详情列表")
    private List<ApplicationDetailDto> applicationDetailList;
}
