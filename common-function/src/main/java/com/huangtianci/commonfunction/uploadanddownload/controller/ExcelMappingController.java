package com.huangtianci.commonfunction.uploadanddownload.controller;

import com.github.pagehelper.Page;
import com.huangtianci.commonfunction.common.bean.Out;
import com.huangtianci.commonfunction.common.bean.PageInfo;
import com.huangtianci.commonfunction.uploadanddownload.bean.dto.ExcelMappingQueryDTO;
import com.huangtianci.commonfunction.uploadanddownload.bean.dto.ExcelMappingUpdateDTO;
import com.huangtianci.commonfunction.uploadanddownload.service.ExcelMappingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huang Tianci
 * @date 2017/11/27
 */
@RestController
@RequestMapping("/excelMappings")
public class ExcelMappingController {

    @Autowired
    ExcelMappingService excelMappingService;

    @ApiOperation(value = "查询表字段映射", notes = "查询表字段映射")
    @GetMapping
    public Out list(ExcelMappingQueryDTO queryDTO) {
        Page page = excelMappingService.selectByPage(queryDTO);
        return Out.builder().success().result(PageInfo.page(page)).build();
    }

    @ApiOperation(value = "更新映射中文名和顺序", notes = "更新映射中文名和顺序")
    @PostMapping
    public Out update(ExcelMappingUpdateDTO excelMappingUpdateDTO) {
        return excelMappingService.updateNameAndIndex(excelMappingUpdateDTO);
    }

}
