package com.huangtianci.commonfunction.uploadanddownload.mapper;

import com.huangtianci.commonfunction.uploadanddownload.bean.dto.TaxQueryDTO;
import com.huangtianci.commonfunction.uploadanddownload.bean.entity.Tax;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

@Repository
public interface TaxMapper extends Mapper<Tax> {

    Integer getLastVersion(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<Tax> selectByIdNumberAndDate(@Param("idNumber") String idNumber, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<Tax> selectByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<Tax> selectByQueryDTO(TaxQueryDTO taxQueryDTO);
}