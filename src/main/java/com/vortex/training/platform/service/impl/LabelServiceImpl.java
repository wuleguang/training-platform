package com.vortex.training.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vortex.training.platform.dto.LabelDetailDto;
import com.vortex.training.platform.dto.LabelDto;
import com.vortex.training.platform.dto.LabelFindDto;
import com.vortex.training.platform.entity.Label;
import com.vortex.training.platform.exception.ParamErrorException;
import com.vortex.training.platform.mapper.LabelMapper;
import com.vortex.training.platform.service.ILabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vortex.training.platform.utils.ConvertUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author light
 * @since 2020-10-30
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {

    @Resource
    private LabelMapper labelMapper;

    @Override
    public Boolean add(LabelDto labelDto) throws ParamErrorException {
        QueryWrapper<Label> selectWrapper = new QueryWrapper<Label>().select("label_id")
                .and(e -> e.eq("label_name", labelDto.getLabelName()));
        Label label = labelMapper.selectOne(selectWrapper);
        if (label != null) {
            throw new ParamErrorException("该标签名称已存在，无法保存");
        }
        label = ConvertUtils.modelMap(labelDto, Label::new);
        Date date = new Date();
        label.setCreateTime(date);
        label.setUpdateTime(date);
        labelMapper.insert(label);
        return true;
    }

    @Override
    public Boolean update(LabelDto labelDto) throws ParamErrorException {
        QueryWrapper<Label> selectWrapper = new QueryWrapper<Label>().select("label_id")
                .and(e -> e.eq("label_name", labelDto.getLabelName()))
                .and(e -> e.ne("label_id", labelDto.getLabelId()));
        Label label = labelMapper.selectOne(selectWrapper);
        if (label != null) {
            throw new ParamErrorException("该标签名称已存在，无法修改");
        }
        label = ConvertUtils.modelMap(labelDto, Label::new);
        Date date = new Date();
        label.setUpdateTime(date);
        super.saveOrUpdate(label);
        return true;
    }

    @Override
    public Boolean delete(Integer labelId) throws ParamErrorException {
        Label label = labelMapper.selectById(labelId);
        if (label == null) {
            throw new ParamErrorException("该标签不存在，无法删除");
        }
        labelMapper.deleteById(labelId);
        return true;
    }

    @Override
    public IPage<LabelDetailDto> findPageBy(LabelFindDto labelFindDto) {
        return labelMapper.findPageBy(labelFindDto);
    }
}
