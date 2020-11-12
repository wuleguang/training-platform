package com.vortex.training.platform.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vortex.training.platform.annotation.ResponseResult;
import com.vortex.training.platform.dto.ModelDetailDto;
import com.vortex.training.platform.dto.ModelDto;
import com.vortex.training.platform.dto.ModelFindDto;
import com.vortex.training.platform.dto.Result;
import com.vortex.training.platform.exception.ParamErrorException;
import com.vortex.training.platform.service.IModelService;
import com.vortex.training.platform.utils.ParamUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author light
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/model")
@ResponseResult
public class ModelController {

    @Resource
    private IModelService modelService;

    /**
     * 新增model
     * @param modelDto modelDto
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    @ApiOperation(value = "新增model", response = Result.class, notes = "新增模型")
    @PostMapping(value = "/add")
    public Boolean add(@ApiParam @RequestBody ModelDto modelDto)  throws ParamErrorException {
        ParamUtil.isStringParamEmpty("模型名称", modelDto.getModelName());
        ParamUtil.isStringParamEmpty("模型描述", modelDto.getModelDescribe());
        ParamUtil.isObjectParamEmpty("模型类别", modelDto.getModelType());
        return modelService.add(modelDto);
    }

    /**
     * 修改model
     * @param modelDto modelDto
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    @ApiOperation(value = "修改model", response = Result.class, notes = "修改模型")
    @PostMapping(value = "/update")
    public Boolean update(@ApiParam @RequestBody ModelDto modelDto) throws ParamErrorException {
        ParamUtil.isStringParamEmpty("模型名称", modelDto.getModelName());
        ParamUtil.isStringParamEmpty("模型描述", modelDto.getModelDescribe());
        ParamUtil.isObjectParamEmpty("模型id", modelDto.getModelId());
        return modelService.update(modelDto);
    }

    /**
     * 删除model
     * @param modelId modelId
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    @ApiOperation(value = "删除model", response = Result.class, notes = "删除模型")
    @DeleteMapping(value = "/delete/{modelId}")
    public Boolean delete(@ApiParam @PathVariable(value = "modelId") Integer modelId)
            throws ParamErrorException {
        ParamUtil.isObjectParamEmpty("模型id", modelId);
        return modelService.delete(modelId);
    }

    /**
     * 分页查询model
     * @param modelFindDto modelFindDto
     * @return IPage
     */
    @ApiOperation(value = "分页查询model", response = Result.class, notes = "分页查询模型")
    @GetMapping(value = "/findPageBy")
    @ApiImplicitParams(value = {@ApiImplicitParam(name = "pageIndex", value = "当前页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据条数")})
    public IPage<ModelDetailDto> findPageBy(ModelFindDto modelFindDto) {
        return modelService.findPageBy(modelFindDto);
    }
}

