package com.huangtianci.commonfunction.uploadanddownload.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huangtianci.commonfunction.common.bean.Out;
import com.huangtianci.commonfunction.common.enums.ResultCodeEnum;
import com.huangtianci.commonfunction.uploadanddownload.bean.dto.ExcelMappingQueryDTO;
import com.huangtianci.commonfunction.uploadanddownload.bean.dto.ExcelMappingUpdateDTO;
import com.huangtianci.commonfunction.uploadanddownload.bean.entity.ExcelMapping;
import com.huangtianci.commonfunction.uploadanddownload.mapper.ExcelMappingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Huang Tianci
 * @date 2017/11/25
 */
@Service
public class ExcelMappingService {

    @Autowired
    ExcelMappingMapper excelMappingMapper;

    public Page selectByPage(ExcelMappingQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize(), true);
        List<ExcelMapping> list = excelMappingMapper.selectByPage(queryDTO);
        return (Page) list;
    }

    public List<ExcelMapping> selectByTableName(String tableName) {
        return excelMappingMapper.selectByTableName(tableName);
    }

    public Out updateNameAndIndex(ExcelMappingUpdateDTO excelMappingUpdateDTO) {
        ExcelMapping excelMapping = new ExcelMapping();
        excelMapping.setId(excelMappingUpdateDTO.getId());
        excelMapping.setName(excelMappingUpdateDTO.getName());
        excelMapping.setIndex(excelMappingUpdateDTO.getIndex());
        int count = excelMappingMapper.updateByPrimaryKeySelective(excelMapping);
        if(count == 1) {
            return Out.builder().success().build();
        }
        return Out.builder().fail(ResultCodeEnum.FAIL).build();
    }
}
