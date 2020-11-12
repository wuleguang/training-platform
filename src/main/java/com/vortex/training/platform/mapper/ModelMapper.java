package com.vortex.training.platform.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vortex.training.platform.dto.ModelDetailDto;
import com.vortex.training.platform.dto.ModelFindDto;
import com.vortex.training.platform.entity.Model;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author light
 * @since 2020-10-30
 */
public interface ModelMapper extends BaseMapper<Model> {

    /**
     * 分页查询model
     * @param modelFindDto modelFindDto
     * @return IPage
     */
    IPage<ModelDetailDto> findPageBy(ModelFindDto modelFindDto);
}
