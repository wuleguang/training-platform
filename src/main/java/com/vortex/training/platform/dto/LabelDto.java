package com.vortex.training.platform.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @author : light
 * @date: 2020/11/2 10:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("新增修改标签参数")
public class LabelDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键，新增不必传，修改必传")
    private Integer labelId;

    @ApiModelProperty(value = "标签名称", required = true)
    private String labelName;

    // TODO: 2020/11/2 暂定该怎么传以及如何存储
    @ApiModelProperty(value = "图片")
    private String images;

    @ApiModelProperty(value = "数据集主键。新增必传，无法修改")
    private Integer datasetId;

}
