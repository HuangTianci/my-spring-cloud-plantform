package com.huangtianci.commonfunction.uploadanddownload.bean.dto;

import com.huangtianci.commonfunction.common.bean.PageInfoDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Huang Tianci
 * @date 2017/11/27
 */
@Data
public class TaxQueryDTO extends PageInfoDTO implements Serializable {

    @ApiModelProperty(value = "姓名", required = false)
    private String name;

    @ApiModelProperty(value = "身份证号", required = false)
    private String idNumber;

    @ApiModelProperty(value = "开始时间", required = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @ApiModelProperty(value = "结束时间", required = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

}
