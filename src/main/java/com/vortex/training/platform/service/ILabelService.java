package com.vortex.training.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vortex.training.platform.dto.LabelDetailDto;
import com.vortex.training.platform.dto.LabelDto;
import com.vortex.training.platform.dto.LabelFindDto;
import com.vortex.training.platform.entity.Label;
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
public interface ILabelService extends IService<Label> {
    /**
     * 新增label
     * @param labelDto labelDto
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    Boolean add(LabelDto labelDto) throws ParamErrorException;

    /**
     * 修改label
     * @param labelDto labelDto
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    Boolean update(LabelDto labelDto) throws ParamErrorException;

    /**
     * 删除label
     * @param labelId labelId
     * @return Boolean
     * @throws ParamErrorException ParamErrorException
     */
    Boolean delete(Integer labelId) throws ParamErrorException;

    /**
     * 分页查询label
     * @param labelFindDto labelFindDto
     * @return IPage
     */
    IPage<LabelDetailDto> findPageBy(LabelFindDto labelFindDto);
}
