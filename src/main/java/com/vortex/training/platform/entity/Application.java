package com.vortex.training.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author light
 * @since 2020-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@TableName("application")
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "application_id", type = IdType.AUTO)
    private Integer applicationId;

    /**
     * 应用类型
     */
    @TableField("application_type")
    private String applicationType;

    /**
     * 版本
     */
    @TableField("application_version")
    private String applicationVersion;

    @TableField("training_status")
    private Integer trainingStatus;

    @TableField("training_start_time")
    private Date trainingStartTime;

    @TableField("training_end_time")
    private Date trainingEndTime;

    /**
     * 评估报告
     */
    @TableField("appraisal_report")
    private String appraisalReport;

    @TableField("model_id")
    private Integer modelId;

    @TableField("dataset_id")
    private Integer datasetId;

    /**
     * 标签id
     */
    @TableField("label_ids")
    private String labelIds;


}
