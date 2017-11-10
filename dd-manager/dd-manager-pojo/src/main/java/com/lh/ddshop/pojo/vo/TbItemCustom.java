package com.lh.ddshop.pojo.vo;

import com.lh.ddshop.pojo.po.TbItem;
import com.lh.ddshop.pojo.po.TbItemCat;

public class TbItemCustom extends TbItem{

    private String catName;

    private String statusName;

    private String priceView;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPriceView() {
        return priceView;
    }

    public void setPriceView(String priceView) {
        this.priceView = priceView;
    }
}