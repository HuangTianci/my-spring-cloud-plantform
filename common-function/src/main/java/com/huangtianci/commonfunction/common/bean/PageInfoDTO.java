package com.huangtianci.commonfunction.common.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Huang Tianci
 * @date 2017/11/23
 * 分页查询DTO都需要继承该类
 */
public class PageInfoDTO implements Serializable {

    @ApiModelProperty(value = "一次请求的记录数，不传默认是10", required = false)
    private int pageSize = 10;

    @ApiModelProperty(value = "当前页码，默认0", required = false)
    private int pageNum = 0;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
