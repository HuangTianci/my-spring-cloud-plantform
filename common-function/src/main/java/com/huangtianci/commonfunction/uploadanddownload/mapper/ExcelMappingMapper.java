package com.huangtianci.commonfunction.uploadanddownload.mapper;

import com.huangtianci.commonfunction.uploadanddownload.bean.dto.ExcelMappingQueryDTO;
import com.huangtianci.commonfunction.uploadanddownload.bean.entity.ExcelMapping;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ExcelMappingMapper extends Mapper<ExcelMapping> {

    List<ExcelMapping> selectByPage(ExcelMappingQueryDTO queryDTO);

    List<ExcelMapping> selectByTableName(@Param("tableName") String tableName);

}