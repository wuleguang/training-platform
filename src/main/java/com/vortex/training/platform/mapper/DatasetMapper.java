package com.vortex.training.platform.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vortex.training.platform.dto.DatasetDetailDto;
import com.vortex.training.platform.dto.DatasetFindDto;
import com.vortex.training.platform.entity.Dataset;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author light
 * @since 2020-10-30
 */
public interface DatasetMapper extends BaseMapper<Dataset> {
    /**
     * 分页查询Dataset
     * @param datasetFindDto datasetFindDto
     * @return IPage
     */
    IPage<DatasetDetailDto> findPageBy(DatasetFindDto datasetFindDto);
}
