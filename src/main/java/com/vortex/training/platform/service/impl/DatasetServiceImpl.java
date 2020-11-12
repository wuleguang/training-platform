package com.vortex.training.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vortex.training.platform.dto.DatasetDetailDto;
import com.vortex.training.platform.dto.DatasetDto;
import com.vortex.training.platform.dto.DatasetFindDto;
import com.vortex.training.platform.entity.Dataset;
import com.vortex.training.platform.exception.ParamErrorException;
import com.vortex.training.platform.mapper.DatasetMapper;
import com.vortex.training.platform.service.IDatasetService;
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
public class DatasetServiceImpl extends ServiceImpl<DatasetMapper, Dataset> implements IDatasetService {

    @Resource
    private DatasetMapper datasetMapper;

    @Override
    public Boolean add(DatasetDto datasetDto) throws ParamErrorException {
        QueryWrapper<Dataset> selectWrapper = new QueryWrapper<Dataset>().select("dataset_id")
                .and(e -> e.eq("dataset_name", datasetDto.getDatasetName()));
        Dataset dataset = datasetMapper.selectOne(selectWrapper);
        if (dataset != null) {
            throw new ParamErrorException("该数据集名称已存在，无法保存");
        }
        dataset = ConvertUtils.modelMap(datasetDto, Dataset::new);
        Date date = new Date();
        dataset.setCreateTime(date);
        dataset.setUpdateTime(date);
        datasetMapper.insert(dataset);
        return true;
    }

    @Override
    public Boolean update(DatasetDto datasetDto) throws ParamErrorException {
        QueryWrapper<Dataset> selectWrapper = new QueryWrapper<Dataset>().select("dataset_id")
                .and(e -> e.eq("dataset_name", datasetDto.getDatasetName()))
                .and(e -> e.ne("dataset_id", datasetDto.getDatasetId()));
        Dataset dataset = datasetMapper.selectOne(selectWrapper);
        if (dataset != null) {
            throw new ParamErrorException("该数据集名称已存在，无法修改");
        }
        dataset = ConvertUtils.modelMap(datasetDto, Dataset::new);
        Date date = new Date();
        dataset.setUpdateTime(date);
        super.saveOrUpdate(dataset);
        return true;
    }

    @Override
    public Boolean delete(Integer datasetId) throws ParamErrorException {
        Dataset model = datasetMapper.selectById(datasetId);
        if (model == null) {
            throw new ParamErrorException("该数据集不存在，无法删除");
        }
        datasetMapper.deleteById(datasetId);
        return true;
    }

    @Override
    public IPage<DatasetDetailDto> findPageBy(DatasetFindDto datasetFindDto) {
        return datasetMapper.findPageBy(datasetFindDto);
    }
}
