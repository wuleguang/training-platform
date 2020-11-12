package com.vortex.training.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vortex.training.platform.dto.ModelDetailDto;
import com.vortex.training.platform.dto.ModelDto;
import com.vortex.training.platform.dto.ModelFindDto;
import com.vortex.training.platform.entity.Model;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vortex.training.platform.exception.ParamErrorException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author light
 * @since 2020-10-30
 */
public interface IModelService extends IService<Model> {

    /**
     * 新增model
     * @param modelDto modelDto
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    Boolean add(ModelDto modelDto) throws ParamErrorException;

    /**
     * 修改model
     * @param modelDto modelDto
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    Boolean update(ModelDto modelDto) throws ParamErrorException;

    /**
     * 删除model
     * @param modelId modelId
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    Boolean delete(Integer modelId) throws ParamErrorException;

    /**
     * 分页查询model
     * @param modelFindDto modelFindDto
     * @return IPage
     */
    IPage<ModelDetailDto> findPageBy(ModelFindDto modelFindDto);
}
