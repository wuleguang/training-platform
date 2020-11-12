package com.vortex.training.platform.constants;

/**
 * @author : light
 * @date: 2020/11/2 11:44
 */
public enum TrainingStatus {
    /**
     * 训练中
     */
    IN_THE_TRAINING(1),
    /**
     * 训练停止
     */
    STOP_THE_TRAINING(2),
    /**
     * 训练完成
     */
    COMPLETE_THE_TRAINING(3);

    TrainingStatus(Integer status) {
        this.status = status;
    }

    private Integer status;

    public Integer getStatus() {
        return status;
    }
}
