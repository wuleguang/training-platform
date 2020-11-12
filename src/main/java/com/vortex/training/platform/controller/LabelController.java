package com.vortex.training.platform.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vortex.training.platform.annotation.ResponseResult;
import com.vortex.training.platform.dto.LabelDetailDto;
import com.vortex.training.platform.dto.LabelDto;
import com.vortex.training.platform.dto.LabelFindDto;
import com.vortex.training.platform.dto.Result;
import com.vortex.training.platform.exception.ParamErrorException;
import com.vortex.training.platform.service.ILabelService;
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
@RequestMapping("/label")
@ResponseResult
public class LabelController {

    @Resource
    private ILabelService labelService;

    /**
     * 新增label
     * @param labelDto labelDto
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    @ApiOperation(value = "新增label", response = Result.class, notes = "新增标签")
    @PostMapping(value = "/add")
    public Boolean add(@ApiParam @RequestBody LabelDto labelDto) throws ParamErrorException {
        ParamUtil.isStringParamEmpty("标签名称", labelDto.getLabelName());
        ParamUtil.isObjectParamEmpty("标签所属数据集id", labelDto.getDatasetId());
        return labelService.add(labelDto);
    }

    /**
     * 修改label
     * @param labelDto labelDto
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    @ApiOperation(value = "修改label", response = Result.class, notes = "修改标签")
    @PostMapping(value = "/update")
    public Boolean update(@ApiParam @RequestBody LabelDto labelDto) throws ParamErrorException {
        ParamUtil.isStringParamEmpty("标签名称", labelDto.getLabelName());
        ParamUtil.isObjectParamEmpty("标签id", labelDto.getLabelId());
        return labelService.update(labelDto);
    }

    /**
     * 删除label
     * @param labelId labelId
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    @ApiOperation(value = "删除label", response = Result.class, notes = "删除标签")
    @DeleteMapping(value = "/delete/{labelId}")
    public Boolean delete(@ApiParam @PathVariable(value = "labelId") Integer labelId)
            throws ParamErrorException {
        ParamUtil.isObjectParamEmpty("标签id", labelId);
        return labelService.delete(labelId);
    }

    /**
     * 分页查询label
     * @param labelFindDto labelFindDto
     * @return IPage
     */
    @ApiOperation(value = "分页查询label", response = Result.class, notes = "分页查询标签")
    @GetMapping(value = "/findPageBy")
    @ApiImplicitParams(value = {@ApiImplicitParam(name = "pageIndex", value = "当前页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据条数")})
    public IPage<LabelDetailDto> findPageBy(LabelFindDto labelFindDto) {
        return labelService.findPageBy(labelFindDto);
    }
}

