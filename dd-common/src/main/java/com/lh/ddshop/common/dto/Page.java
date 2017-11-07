package com.lh.ddshop.common.dto;

//封装分页请求的参数类
public class Page {

    private int page;//当前页的页码

    private int rows;//每页显示的条数

    //private int offset;  //偏移量  每页条数*(当前页数-1)

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * 获得偏移量,不需要手动设值
     * @return
     */
    public int getOffset() {
        return rows*(page-1);
    }

}
