package com.huangtianci.commonfunction.uploadanddownload.bean.dto;

import com.huangtianci.commonfunction.common.bean.PageInfoDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Huang Tianci
 * @date 2017/11/27
 */
@Data
public class ExcelMappingQueryDTO extends PageInfoDTO implements Serializable {

    @ApiModelProperty(value = "表名称", required = false)
    private String tableName;

}
