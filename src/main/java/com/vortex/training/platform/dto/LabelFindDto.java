package com.vortex.training.platform.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vortex.training.platform.entity.Label;
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
@ApiModel("分页查询Label参数")
public class LabelFindDto extends Page<Label> implements Serializable {
    @ApiModelProperty(value = "标签名称", required = true)
    private String labelName;

    @ApiModelProperty(value = "数据集主键。新增必传，无法修改")
    private Integer datasetId;

    @ApiModelProperty(value = "图片")
    private String images;
}
