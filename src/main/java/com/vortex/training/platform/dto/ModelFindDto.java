package com.vortex.training.platform.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vortex.training.platform.entity.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author : light
 * @date: 2020/10/30 18:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("分页查询model参数")
public class ModelFindDto extends Page<Model> implements Serializable {
    /**
     * 模型名称
     */
    @ApiModelProperty(value = "模型名称")
    private String modelName;

    /**
     * 模型描述
     */
    @ApiModelProperty(value = "模型描述")
    private String modelDescribe;

    /**
     * 模型类别：图像分类模型、目标检测模型
     */
    @ApiModelProperty(value = "模型类别：1.图像分类模型 2.目标检测模型。新增必传，无法修改")
    private Integer modelType;
}
