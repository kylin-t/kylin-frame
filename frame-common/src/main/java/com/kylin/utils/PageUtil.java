package com.kylin.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:  分页工具类
 * @Auther: kylin
 * @Email: tongzhq94@163.com
 * @Date: 2018/06/05 23:36
 */
public class PageUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    //总记录数
    private int totalCount;
    //每页记录数
    private int pageSize;
    //总页数
    private int totalPage;
    //当前页数
    private int pageNum;
    //列表数据
    private List<?> list;

    /**
     * 分页
     * @param list        列表数据
     * @param totalCount  总记录数
     * @param pageSize    每页记录数
     * @param pageNum    当前页数
     */
    public PageUtil(List<?> list, int totalCount, int pageSize, int pageNum) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
