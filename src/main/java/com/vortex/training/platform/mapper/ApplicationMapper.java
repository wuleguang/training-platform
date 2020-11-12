package com.vortex.training.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vortex.training.platform.dto.ApplicationDetailDto;
import com.vortex.training.platform.dto.ApplicationFindDto;
import com.vortex.training.platform.entity.Application;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author light
 * @since 2020-10-30
 */
public interface ApplicationMapper extends BaseMapper<Application> {
    /**
     * 更改application状态
     * @param applicationId applicationId
     * @param trainingStatus trainingStatus
     */
    @Update("update application set training_status = #{trainingStatus} where application_id = #{applicationId}")
    void updateTrainingStatus(@Param("applicationId") Integer applicationId,
                              @Param("trainingStatus") Integer trainingStatus);

    /**
     * 查询应用list
     * @param applicationFindDto applicationFindDto
     * @return List
     */
    List<ApplicationDetailDto> findListBy(ApplicationFindDto applicationFindDto);
}
