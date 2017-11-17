package com.lh.ddshop.service;

import com.lh.ddshop.common.dto.Order;
import com.lh.ddshop.common.dto.Page;
import com.lh.ddshop.common.dto.Result;
import com.lh.ddshop.pojo.po.TbItemParam;
import com.lh.ddshop.pojo.vo.TbItemParamCustom;

public interface ItemParamService {

    //分页显示商品规格参数
    Result<TbItemParamCustom> listItemParamsByPage(Page page);

    //根据商品类目,查询商品规格参数
    TbItemParam getItemByCid(Long cid);
}
