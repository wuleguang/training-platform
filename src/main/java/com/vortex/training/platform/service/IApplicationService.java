package com.vortex.training.platform.service;

import com.vortex.training.platform.dto.ApplicationDetailDto;
import com.vortex.training.platform.dto.ApplicationDto;
import com.vortex.training.platform.dto.ApplicationFindDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vortex.training.platform.dto.ApplicationUpdateDto;
import com.vortex.training.platform.entity.Application;
import com.vortex.training.platform.exception.ParamErrorException;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author light
 * @since 2020-10-30
 */
public interface IApplicationService extends IService<Application> {
    /**
     * 新增application
     * @param applicationDto applicationDto
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    Boolean add(ApplicationDto applicationDto) throws ParamErrorException;

    /**
     * 修改application
     * @param applicationUpdateDto applicationUpdateDto
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    Boolean updateTrainingStatus(ApplicationUpdateDto applicationUpdateDto) throws ParamErrorException;

    /**
     * 删除application
     * @param applicationId applicationId
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    Boolean delete(Integer applicationId) throws ParamErrorException;

    /**
     * 查询application
     * @param applicationFindDto applicationFindDto
     * @return IPage
     */
    List<ApplicationDetailDto> findListBy(ApplicationFindDto applicationFindDto);
}
