package com.huangtianci.commonfunction.uploadanddownload.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Huang Tianci
 * @date 2017/11/27
 */
@Data
public class ExcelMappingUpdateDTO implements Serializable {

    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    @ApiModelProperty(value = "表头中文名称", required = true)
    private String name;

    @ApiModelProperty(value = "显示顺序", required = true)
    private String index;

}
