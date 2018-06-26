package com.huangtianci.commonfunction.uploadanddownload.service;

import com.huangtianci.commonfunction.common.bean.Out;
import com.huangtianci.commonfunction.common.enums.BaseEnum;
import com.huangtianci.commonfunction.common.exception.ServiceException;
import com.huangtianci.commonfunction.uploadanddownload.bean.entity.ExcelMapping;
import com.huangtianci.commonfunction.uploadanddownload.bean.entity.Tax;
import com.huangtianci.commonfunction.uploadanddownload.common.enums.CellTypeEnum;
import com.huangtianci.commonfunction.utils.DateFormatUtil;
import com.huangtianci.commonfunction.utils.EnumUtils;
import com.huangtianci.commonfunction.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author Huang Tianci
 * @date 2017/11/25
 * excel文件解析服务类
 */
@Service
public class ExcelFileResolveService {

    @Autowired
    TaxService taxService;

    @Autowired
    ExcelMappingService excelMappingService;

    /**
     * 先实现一个简单的，直接将流对象传入工作簿
     * 复杂的是专门写一套逻辑来处理大文件上传，之后将文件保存到文件存储服务器上
     * 解析的时候再从文件存储服务器上读取。
     *
     * @param excelFile
     * @return
     */
    public Out resolve(MultipartFile excelFile) throws IOException, InvalidFormatException {
        /** If using HSSFWorkbook or XSSFWorkbook directly,
         * you should generally go through NPOIFSFileSystem or OPCPackage,
         * to have full control of the lifecycle
         * (including closing the file when done) */
        /** HSSFWorkbook, InputStream, needs more memory  */
        OPCPackage pkg = OPCPackage.open(excelFile.getInputStream());
        XSSFWorkbook wb = new XSSFWorkbook(pkg);

        Sheet sheet1 = wb.getSheetAt(0);

        int rowStart = sheet1.getFirstRowNum();
        int rowEnd = sheet1.getLastRowNum();

        /** 处理header */

        List<ExcelMapping> headerMappings = excelMappingService.selectByTableName("tax");
        Map<String, String> headMap = new HashMap<>();
        Row headRow = sheet1.getRow(rowStart);

        for (int cn = 0; cn < headRow.getLastCellNum(); cn++) {
            Cell cell = headRow.getCell(cn, Row.RETURN_BLANK_AS_NULL);
            if (headRow != null) {
                String value = cell.getStringCellValue();
                CellReference cellRef = new CellReference(headRow.getRowNum(), cell.getColumnIndex());
                String cellRefValue = cellRef.formatAsString();
                for (ExcelMapping excelMapping : headerMappings) {
                    /** 保存单元格与pojo类属性的映射关系 */
                    if(excelMapping.getName().equals(value)) {
                        headMap.put(cellRefValue, excelMapping.getPropName());
                    }
                }
            }
        }

        System.out.println("headMap: " + headMap);

        List<Map<String, Object>> rowList = new ArrayList<>();
        for (int rowIndex = rowStart + 1; rowIndex < rowEnd; rowIndex++) {
            Row row = sheet1.getRow(rowIndex);
            if (row == null) {
                continue;
            }
            Map<String, Object> cellMap = new HashMap<>();
            Iterator<Cell> iterator = row.cellIterator();
            while (iterator.hasNext()) {
                Cell cell = iterator.next();
                CellReference cellRef = new CellReference(headRow.getRowNum(), cell.getColumnIndex());
                String cellRefValue = cellRef.formatAsString();
                if (headMap.containsKey(cellRefValue)) {
                    cellMap.put(headMap.get(cellRefValue), getCellValue(cell));
                }
            }
            if(!cellMap.isEmpty() && isNotNullValue(cellMap)) {
                rowList.add(cellMap);
            }
        }

        List<Tax> taxList = new ArrayList<>();
        for (Map<String, Object> cellMap:rowList) {
            Tax tax = JsonUtils.fromJson(JsonUtils.toJson(cellMap), Tax.class);
            taxList.add(tax);
        }

        taxService.batchInsert(taxList);
        return Out.builder().success().build();
    }

    private boolean isNotNullValue(Map<String, Object> cellMap) {
        for (Map.Entry<String, Object> entry : cellMap.entrySet()) {
            if(entry.getValue() != null) {
                return true;
            } else if(entry.getValue() instanceof String) {
                if(StringUtils.isNotBlank((String) entry.getValue())){
                    return true;
                }
            }
        }
        return false;
    }

    private Object getCellValue(Cell cell) {
        Object value = null;
        switch (CellType.forInt(cell.getCellType())) {
            case STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    //value = DateFormatUtil.formatDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, cell.getDateCellValue());
                    value = cell.getDateCellValue();
                } else {
                    value = cell.getNumericCellValue();
                }
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case BLANK:
            default:
        }
        return value;
    }

    /**
     * 下载当月最后一次导入的数据
     * @param response
     * @throws IOException
     */
    public void downloadCurrentMouthLastVersion(HttpServletResponse response) throws IOException {
        List<Tax> dataList = taxService.selectThisMouthLastVersionData();
        download(dataList, response);
    }

    /**
     * 下载当月所有数据
     * @param response
     */
    public void downloadCurrentMouth(HttpServletResponse response) throws IOException {
        Date current = new Date();
        downloadGivingMouth(response, current);
    }

    /**
     * 下载指定月份的数据
     * @param response
     * @param date
     * @throws IOException
     */
    public void downloadGivingMouth(HttpServletResponse response, Date date) throws IOException {
        Date startDate = com.huangtianci.commonfunction.utils.DateUtil.getMonthStart(date);
        Date endDate = com.huangtianci.commonfunction.utils.DateUtil.getMonthEnd(date);
        downloadTimeSlot(response, startDate, endDate);
    }

    /**
     * 下载指定时间段内的数据
     * @param response
     * @param startDate
     * @param endDate
     */
    public void downloadTimeSlot(HttpServletResponse response, Date startDate, Date endDate) throws IOException {
        List<Tax> dataList = taxService.selectByDate(startDate, endDate);
        download(dataList, response);
    }

    /**
     * 下载所有数据
     * @param response
     * @throws IOException
     */
    public void downloadAllData(HttpServletResponse response) throws IOException {
        List<Tax> dataList = taxService.selectAll();
        download(dataList, response);
    }

    public void download(List<Tax> dataList,HttpServletResponse response) throws IOException{
        List<ExcelMapping> headerMappings = excelMappingService.selectByTableName("tax");

        List<JsonNode> taxMapList = new ArrayList<>();
        for (Tax tax:dataList) {
            JsonNode jsonNode = JsonUtils.fromJson(JsonUtils.toJson(tax), JsonNode.class);
            taxMapList.add(jsonNode);
        }

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Row rowHead = sheet.createRow(0);
        int headCount = 0;
        for(ExcelMapping excelMapping : headerMappings) {
            rowHead.createCell(headCount).setCellValue(excelMapping.getName());
            headCount++;
        }

        int i = 1;
        for (JsonNode jsonNode : taxMapList) {
            Row row = sheet.createRow(i);
            i++;
            int j = 0;
            for(ExcelMapping excelMapping : headerMappings) {
                BaseEnum type = EnumUtils.getEnumByCode(CellTypeEnum.class, excelMapping.getPropType());
                if (type == null) {
                    throw new ServiceException("表头配置错误");
                } else if(type.getCode().equals(CellTypeEnum.STRING.getCode())) {
                    JsonNode node = jsonNode.get(excelMapping.getPropName());
                    if(node != null) {
                        row.createCell(j).setCellValue(node.getTextValue());
                    } else {
                        row.createCell(j).setCellValue("");
                    }
                } else if(type.getCode().equals(CellTypeEnum.BIGDECIMAL.getCode())) {
                    JsonNode node = jsonNode.get(excelMapping.getPropName());
                    if(node != null) {
                        row.createCell(j).setCellValue(node.getDecimalValue().toString());
                    } else {
                        row.createCell(j).setCellValue("");
                    }
                } else if(type.getCode().equals(CellTypeEnum.DATE.getCode())) {
                    JsonNode node = jsonNode.get(excelMapping.getPropName());
                    if(node != null) {
                        String dateStr = DateFormatUtil.formatDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, node.getLongValue());
                        row.createCell(j).setCellValue(dateStr);
                    } else {
                        row.createCell(j).setCellValue("");
                    }
                }
                j++;
            }

        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        wb.write(bos);

        String fileName = "Tsx.xlsx";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.addHeader("Content-Length", "" + bos.toByteArray().length);
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        outputStream.write(bos.toByteArray());
        outputStream.flush();
        outputStream.close();
    }
}

