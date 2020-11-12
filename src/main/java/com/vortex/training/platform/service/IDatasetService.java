package com.vortex.training.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vortex.training.platform.dto.DatasetDetailDto;
import com.vortex.training.platform.dto.DatasetDto;
import com.vortex.training.platform.dto.DatasetFindDto;
import com.vortex.training.platform.entity.Dataset;
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
public interface IDatasetService extends IService<Dataset> {
    /**
     * 新增dataset
     * @param datasetDto datasetDto
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    Boolean add(DatasetDto datasetDto) throws ParamErrorException;

    /**
     * 修改dataset
     * @param datasetDto datasetDto
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    Boolean update(DatasetDto datasetDto) throws ParamErrorException;

    /**
     * 删除dataset
     * @param datasetId datasetId
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    Boolean delete(Integer datasetId) throws ParamErrorException;

    /**
     * 分页查询dataset
     * @param datasetFindDto datasetFindDto
     * @return IPage
     */
    IPage<DatasetDetailDto> findPageBy(DatasetFindDto datasetFindDto);
}
