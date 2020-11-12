package com.vortex.training.platform.controller;


import com.vortex.training.platform.annotation.ResponseResult;
import com.vortex.training.platform.dto.*;
import com.vortex.training.platform.entity.Application;
import com.vortex.training.platform.exception.ParamErrorException;
import com.vortex.training.platform.service.IApplicationService;
import com.vortex.training.platform.utils.ParamUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author light
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/application")
@ResponseResult
public class ApplicationController {

    @Resource
    private IApplicationService applicationService;

    /**
     * 新增application
     * @param applicationDto applicationDto
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    @ApiOperation(value = "新增application", response = Result.class, notes = "新增应用")
    @PostMapping(value = "/add")
    public Boolean add(@ApiParam @RequestBody ApplicationDto applicationDto) throws ParamErrorException {
        ParamUtil.isObjectParamEmpty("应用所属模型id", applicationDto.getModelId());
        ParamUtil.isObjectParamEmpty("应用相关数据集id", applicationDto.getDatasetId());
        ParamUtil.isObjectParamEmpty("应用相关标签id", applicationDto.getModelId());
        return applicationService.add(applicationDto);
    }

    /**
     * 修改application
     * @param applicationUpdateDto applicationUpdateDto
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    @ApiOperation(value = "修改application", response = Result.class, notes = "修改应用")
    @PostMapping(value = "/update")
    public Boolean updateTrainingStatus(@ApiParam @RequestBody  ApplicationUpdateDto applicationUpdateDto)
            throws ParamErrorException {
        ParamUtil.isObjectParamEmpty("应用id", applicationUpdateDto.getApplicationId());
        ParamUtil.isObjectParamEmpty("操作类型", applicationUpdateDto.getOperateType());
        return applicationService.updateTrainingStatus(applicationUpdateDto);
    }

    /**
     * 删除application
     * @param applicationId applicationId
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    @ApiOperation(value = "删除application", response = Result.class, notes = "删除应用")
    @DeleteMapping(value = "/delete/{applicationId}")
    public Boolean delete(@ApiParam @PathVariable(value = "applicationId") Integer applicationId)
            throws ParamErrorException {
        ParamUtil.isObjectParamEmpty("应用id", applicationId);
        return applicationService.delete(applicationId);
    }

    /**
     * 查询application
     * @param applicationFindDto applicationFindDto
     * @return IPage
     */
    @ApiOperation(value = "查询application列表", response = Result.class, notes = "查询应用列表")
    @GetMapping(value = "/findPageBy")
    public List<ApplicationDetailDto> findListBy(ApplicationFindDto applicationFindDto) {
        return applicationService.findListBy(applicationFindDto);
    }
}

