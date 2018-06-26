package com.huangtianci.commonfunction.common.bean;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

/**
 * @author Huang Tianci
 * @date 2017/11/23
 * 返回分页信息的包装类
 */
@Data
public class PageInfo {

    private long total;

    private List list;

    private PageInfo() {}

    public static PageInfo page(Page page) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(page.getTotal());
        pageInfo.setList(page.getResult());
        return pageInfo;
    }

    public static PageInfo page(List list, long total) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(total);
        pageInfo.setList(list);
        return pageInfo;
    }

    /**
     * 没有total信息
     * @param list
     * @return
     */
    public static PageInfo all(List list) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }

}
