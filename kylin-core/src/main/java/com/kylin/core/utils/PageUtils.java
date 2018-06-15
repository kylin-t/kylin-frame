package com.kylin.core.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页工具类
 * @author: kylin
 * @create: 2018-01-30 15:06
 **/
public class PageUtils implements Serializable{
    private static final long serialVersionUID = 1L;
    //总记录数
    private int total;
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
     * @param total  总记录数
     * @param pageSize    每页记录数
     * @param pageNum    当前页数
     */
    public PageUtils(List<?> list, int total, int pageSize, int pageNum) {
        this.list = list;
        this.total = total;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.totalPage = (int)Math.ceil((double)total/pageSize);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public void setPageNum(int currPage) {
        this.pageNum = pageNum;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
