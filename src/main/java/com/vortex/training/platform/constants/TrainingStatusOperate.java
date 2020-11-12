package com.vortex.training.platform.constants;

/**
 * @author : light
 * @date: 2020/11/2 14:01
 */
public enum TrainingStatusOperate {

    /**
     * 训练停止
     */
    STOP_THE_TRAINING(1),
    /**
     * 训练完成
     */
    RESTART_THE_TRAINING(2);

    TrainingStatusOperate(Integer operateType) {
        this.operateType = operateType;
    }

    private Integer operateType;

    public Byte getOperateType() {
        return operateType.byteValue();
    }
}
