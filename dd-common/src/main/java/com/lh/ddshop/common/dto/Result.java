package com.lh.ddshop.common.dto;

import java.util.List;

public class Result<T> {

    private int total;

    private List<T> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "Result{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
