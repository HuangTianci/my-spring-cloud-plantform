package com.huangtianci.commonfunction.uploadanddownload.controller;

import com.huangtianci.commonfunction.common.bean.Out;
import com.huangtianci.commonfunction.uploadanddownload.service.ExcelFileResolveService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Huang Tianci
 * @date 2017/11/25
 * excel文件上传
 */
@RestController
public class UploadExcelController {

    @Autowired
    ExcelFileResolveService excelFileResolveService;

    @ApiOperation(value = "上传Excel文件", notes = "上传本次发放工资的Excel文件")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "form", name = "excelFile", dataType = "file", required = true, value = "Excel文件")
    })
    @PostMapping("/upload")
    public Out uplaod(MultipartFile excelFile) throws Exception {
        return excelFileResolveService.resolve(excelFile);
    }

    @ApiOperation(value = "下载当月最后一次导入的数据", notes = "下载当月最后一次发放工资的详细Excel文件")
    @GetMapping("/download/currentMouthLastVersion")
    public void downloadCurrentMouthLastVersion(HttpServletResponse response) throws Exception {
        excelFileResolveService.downloadCurrentMouthLastVersion(response);
    }

    @ApiOperation(value = "下载当月所有数据", notes = "下载当月所有数据")
    @GetMapping("/download/currentMouth")
    public void downloadCurrentMouth(HttpServletResponse response) throws Exception {
        excelFileResolveService.downloadCurrentMouth(response);
    }

    @ApiOperation(value = "下载指定月份的数据", notes = "下载指定月份的数据")
    @GetMapping("/download/byMouth")
    public void downloadGivingMouth(HttpServletResponse response, @DateTimeFormat(pattern = "yyyy-MM") Date date) throws Exception {
        excelFileResolveService.downloadGivingMouth(response, date);
    }

    @ApiOperation(value = "下载指定时间段内的数据", notes = "下载指定时间段内的数据")
    @GetMapping("/download/byDate")
    public void downloadByDate(HttpServletResponse response, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate) throws Exception {
        excelFileResolveService.downloadTimeSlot(response, startDate, endDate);
    }

    @ApiOperation(value = "下载所有数据", notes = "下载所有数据")
    @GetMapping("/download/allData")
    public void downloadByDate(HttpServletResponse response) throws Exception {
        excelFileResolveService.downloadAllData(response);
    }

}
