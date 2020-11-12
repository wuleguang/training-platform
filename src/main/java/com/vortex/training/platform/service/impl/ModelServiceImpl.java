package com.vortex.training.platform.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vortex.training.platform.config.KafkaEnvConfig;
import com.vortex.training.platform.dto.ModelDetailDto;
import com.vortex.training.platform.dto.ModelDto;
import com.vortex.training.platform.dto.ModelFindDto;
import com.vortex.training.platform.entity.Model;
import com.vortex.training.platform.exception.ParamErrorException;
import com.vortex.training.platform.mapper.ModelMapper;
import com.vortex.training.platform.mq.kafka.KafkaProducer;
import com.vortex.training.platform.service.IModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vortex.training.platform.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements IModelService {

    @Resource
    private ModelMapper modelMapper;

    @Override
    public Boolean add(ModelDto modelDto) throws ParamErrorException {
        log.info("新增model参数：{}", JSONObject.toJSONString(modelDto));
        QueryWrapper<Model> selectWrapper = new QueryWrapper<Model>().select("model_id")
                .and(e -> e.eq("model_name", modelDto.getModelName()));
        Model model = modelMapper.selectOne(selectWrapper);
        if (model != null) {
            log.error("该模型名称已存在,无法保存：{}", JSONObject.toJSONString(modelDto));
            throw new ParamErrorException("该模型名称已存在，无法保存");
        }
        model = ConvertUtils.modelMap(modelDto, Model::new);
        Date date = new Date();
        model.setCreateTime(date);
        model.setUpdateTime(date);
        modelMapper.insert(model);
        return true;
    }

    @Override
    public Boolean update(ModelDto modelDto) throws ParamErrorException {
        QueryWrapper<Model> selectWrapper = new QueryWrapper<Model>().select("model_id")
                .and(e -> e.eq("model_name", modelDto.getModelName()))
                .and(e -> e.ne("model_id", modelDto.getModelId()));
        Model model = modelMapper.selectOne(selectWrapper);
        if (model != null) {
            throw new ParamErrorException("该模型名称已存在，无法修改");
        }
        model = ConvertUtils.modelMap(modelDto, Model::new);
        Date date = new Date();
        model.setUpdateTime(date);
        super.saveOrUpdate(model);
        return true;
    }

    @Override
    public Boolean delete(Integer modelId) throws ParamErrorException {
        Model model = modelMapper.selectById(modelId);
        if (model == null) {
            throw new ParamErrorException("该模型不存在，无法删除");
        }
        modelMapper.deleteById(modelId);
        return true;
    }

    @Override
    public IPage<ModelDetailDto> findPageBy(ModelFindDto modelFindDto) {
        return modelMapper.findPageBy(modelFindDto);
    }
}
