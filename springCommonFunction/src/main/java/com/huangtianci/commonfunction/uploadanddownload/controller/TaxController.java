package com.huangtianci.commonfunction.uploadanddownload.controller;

import com.github.pagehelper.Page;
import com.huangtianci.commonfunction.common.bean.Out;
import com.huangtianci.commonfunction.common.bean.PageInfo;
import com.huangtianci.commonfunction.uploadanddownload.bean.dto.TaxQueryDTO;
import com.huangtianci.commonfunction.uploadanddownload.service.ExcelFileResolveService;
import com.huangtianci.commonfunction.uploadanddownload.service.TaxService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Huang Tianci
 * @date 2017/11/27
 */
@RestController
@RequestMapping("/tax")
public class TaxController {

    @Autowired
    TaxService taxService;

    @Autowired
    ExcelFileResolveService excelFileResolveService;

    @ApiOperation(value = "查询Tax列表", notes = "查询Tax列表")
    @GetMapping
    public Out list(TaxQueryDTO queryDTO) {
        Page page = taxService.selectByPage(queryDTO);
        return Out.builder().success().result(PageInfo.page(page)).build();
    }

    @ApiOperation(value = "导出Tax列表", notes = "导出Tax列表")
    @GetMapping("/export")
    public void export(TaxQueryDTO queryDTO, HttpServletResponse response) throws IOException {
        Page page = taxService.selectByPage(queryDTO);
        excelFileResolveService.download(page.getResult(), response);
    }
}
