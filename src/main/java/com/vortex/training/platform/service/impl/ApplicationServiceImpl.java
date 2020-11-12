package com.vortex.training.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vortex.training.platform.constants.Constants;
import com.vortex.training.platform.constants.TrainingStatus;
import com.vortex.training.platform.constants.TrainingStatusOperate;
import com.vortex.training.platform.dto.ApplicationDetailDto;
import com.vortex.training.platform.dto.ApplicationDto;
import com.vortex.training.platform.dto.ApplicationFindDto;
import com.vortex.training.platform.dto.ApplicationUpdateDto;
import com.vortex.training.platform.entity.Application;
import com.vortex.training.platform.exception.ParamErrorException;
import com.vortex.training.platform.mapper.ApplicationMapper;
import com.vortex.training.platform.service.IApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vortex.training.platform.utils.ParamUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author light
 * @since 2020-10-30
 */
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements IApplicationService {

    @Resource
    private ApplicationMapper applicationMapper;

    @Override
    public Boolean add(ApplicationDto applicationDto) throws ParamErrorException {
        ParamUtil.isObjectParamEmpty("模型Id", applicationDto.getModelId());
        String version = this.getApplicationVersion(applicationDto.getModelId());
        Application application = Application.builder()
                .applicationType(applicationDto.getApplicationType())
                .applicationVersion(version)
                .trainingStatus(TrainingStatus.IN_THE_TRAINING.getStatus())
                .modelId(applicationDto.getModelId())
                .datasetId(applicationDto.getDatasetId())
                .labelIds(applicationDto.getLabelIds())
                .build();
        this.setTrainingTime(application);
        applicationMapper.insert(application);
        return true;
    }

    /**
     * 获取application版本
     * @param modelId modelId
     * @return String
     */
    private String getApplicationVersion(Integer modelId) {
        QueryWrapper<Application> selectWrapper = new QueryWrapper<Application>().select()
                .and(e -> e.eq("model_id", modelId))
                .orderByDesc("application_version");
        List<Application> applicationList = applicationMapper.selectList(selectWrapper);
        String version = null;
        if (!CollectionUtils.isEmpty(applicationList)) {
            String lastVersion = applicationList.get(0).getApplicationVersion();
            if (!StringUtils.isEmpty(lastVersion)) {
                Integer versionNum = Integer.valueOf(lastVersion.replace("V", "")) + Constants.NUM_ONE;
                version = new StringBuilder().append("V").append(versionNum).toString();
            }
        }
        if (StringUtils.isEmpty(version)) {
            version = "V1";
        }
        return version;
    }

    /**
     * 赋值application相关训练时间
     * @param application application
     */
    private void setTrainingTime(Application application) {
        Calendar calendar = Calendar.getInstance();
        Date startTime = calendar.getTime();
        application.setTrainingStartTime(startTime);
        calendar.add(Calendar.HOUR, Constants.NUM_ONE);
        Date endTime = calendar.getTime();
        application.setTrainingEndTime(endTime);
    }

    @Override
    public Boolean updateTrainingStatus(ApplicationUpdateDto applicationUpdateDto) throws ParamErrorException {
        ParamUtil.isObjectParamEmpty("应用主键", applicationUpdateDto.getApplicationId());
        ParamUtil.isObjectParamEmpty("操作类型", applicationUpdateDto.getOperateType());
        Application application = applicationMapper.selectById(applicationUpdateDto.getApplicationId());
        if (application == null) {
            throw new ParamErrorException("该应用不存在，无法操作");
        }
        if (TrainingStatusOperate.STOP_THE_TRAINING.getOperateType().equals(applicationUpdateDto.getOperateType())) {
            //停止训练
            if (!TrainingStatus.IN_THE_TRAINING.getStatus().equals(application.getTrainingStatus())) {
                throw new ParamErrorException("该应用不是训练中的状态，无法操作");
            }
            applicationMapper.updateTrainingStatus(applicationUpdateDto.getApplicationId(),
                    TrainingStatus.STOP_THE_TRAINING.getStatus());
        } else if (TrainingStatusOperate.STOP_THE_TRAINING.getOperateType()
                .equals(applicationUpdateDto.getOperateType())) {
            //重新训练
            if (!TrainingStatus.STOP_THE_TRAINING.getStatus().equals(application.getTrainingStatus())) {
                throw new ParamErrorException("该应用不是停止训练的状态，无法操作");
            }
            String version = this.getApplicationVersion(application.getModelId());
            application.setApplicationVersion(version);
            this.setTrainingTime(application);
            application.setTrainingStatus(TrainingStatus.IN_THE_TRAINING.getStatus());
            super.saveOrUpdate(application);
        }
        return true;
    }

    @Override
    public Boolean delete(Integer applicationId) throws ParamErrorException {
        Application application = applicationMapper.selectById(applicationId);
        if (application == null) {
            throw new ParamErrorException("该应用不存在，无法删除");
        }
        applicationMapper.deleteById(applicationId);
        return true;
    }

    @Override
    public List<ApplicationDetailDto> findListBy(ApplicationFindDto applicationFindDto) {
        return applicationMapper.findListBy(applicationFindDto);
    }
}
