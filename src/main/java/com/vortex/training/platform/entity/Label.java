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
@TableName("label")
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "label_id", type = IdType.AUTO)
    private Integer labelId;

    /**
     * 标签名称
     */
    @TableField("label_name")
    private String labelName;

    /**
     * 标签下面的图片
     */
    @TableField("images")
    private String images;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("dataset_id")
    private Integer datasetId;


}
