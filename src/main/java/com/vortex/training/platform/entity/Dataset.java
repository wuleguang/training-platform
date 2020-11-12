package com.vortex.training.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author light
 * @since 2020-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dataset")
public class Dataset implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dataset_id", type = IdType.AUTO)
    private Integer datasetId;

    /**
     * 数据集名称
     */
    @TableField("dataset_name")
    private String datasetName;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    /**
     * 数据集类型：1-训练集 2-校验集
     */
    @TableField("dataset_type")
    private Integer datasetType;


}
