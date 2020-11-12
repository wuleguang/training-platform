package com.vortex.training.platform.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vortex.training.platform.dto.LabelDetailDto;
import com.vortex.training.platform.dto.LabelFindDto;
import com.vortex.training.platform.entity.Label;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author light
 * @since 2020-10-30
 */
public interface LabelMapper extends BaseMapper<Label> {
    /**
     * 分页查询Label
     * @param labelFindDto labelFindDto
     * @return IPage
     */
    IPage<LabelDetailDto> findPageBy(LabelFindDto labelFindDto);
}
