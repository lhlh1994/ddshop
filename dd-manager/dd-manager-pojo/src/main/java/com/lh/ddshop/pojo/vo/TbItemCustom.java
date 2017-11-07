package com.lh.ddshop.pojo.vo;

import com.lh.ddshop.pojo.po.TbItem;
import com.lh.ddshop.pojo.po.TbItemCat;

public class TbItemCustom extends TbItem{

    private String catName;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
