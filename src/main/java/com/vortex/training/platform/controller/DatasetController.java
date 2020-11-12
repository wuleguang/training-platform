package com.vortex.training.platform.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vortex.training.platform.annotation.ResponseResult;
import com.vortex.training.platform.dto.DatasetDetailDto;
import com.vortex.training.platform.dto.DatasetDto;
import com.vortex.training.platform.dto.DatasetFindDto;
import com.vortex.training.platform.dto.Result;
import com.vortex.training.platform.exception.ParamErrorException;
import com.vortex.training.platform.service.IDatasetService;
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
@RequestMapping("/dataset")
@ResponseResult
public class DatasetController {

    @Resource
    private IDatasetService datasetService;

    /**
     * 新增dataset
     * @param datasetDto datasetDto
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    @ApiOperation(value = "新增dataset", response = Result.class, notes = "新增数据集")
    @PostMapping(value = "/add")
    public Boolean add(@ApiParam @RequestBody DatasetDto datasetDto) throws ParamErrorException {
        ParamUtil.isStringParamEmpty("数据集名称", datasetDto.getDatasetName());
        ParamUtil.isObjectParamEmpty("数据集类型", datasetDto.getDatasetType());
        return datasetService.add(datasetDto);
    }

    /**
     * 修改dataset
     * @param datasetDto datasetDto
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    @ApiOperation(value = "修改dataset", response = Result.class, notes = "修改数据集")
    @PostMapping(value = "/update")
    public Boolean update(@ApiParam @RequestBody DatasetDto datasetDto) throws ParamErrorException {
        ParamUtil.isStringParamEmpty("数据集名称", datasetDto.getDatasetName());
        ParamUtil.isObjectParamEmpty("数据集id", datasetDto.getDatasetId());
        return datasetService.update(datasetDto);
    }

    /**
     * 删除dataset
     * @param datasetId datasetId
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    @ApiOperation(value = "删除dataset", response = Result.class, notes = "删除数据集")
    @DeleteMapping(value = "/delete/{datasetId}")
    public Boolean delete(@ApiParam @PathVariable(value = "datasetId") Integer datasetId)
            throws ParamErrorException {
        ParamUtil.isObjectParamEmpty("数据集id", datasetId);
        return datasetService.delete(datasetId);
    }

    /**
     * 分页查询dataset
     * @param datasetFindDto datasetFindDto
     * @return IPage
     */
    @ApiOperation(value = "分页查询dataset", response = Result.class, notes = "分页查询数据集")
    @GetMapping(value = "/findPageBy")
    @ApiImplicitParams(value = {@ApiImplicitParam(name = "pageIndex", value = "当前页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据条数")})
    public IPage<DatasetDetailDto> findPageBy(DatasetFindDto datasetFindDto) {
        return datasetService.findPageBy(datasetFindDto);
    }
}

