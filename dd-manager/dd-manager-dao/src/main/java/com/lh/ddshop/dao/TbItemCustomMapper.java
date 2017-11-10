package com.lh.ddshop.dao;

import com.lh.ddshop.common.dto.Order;
import com.lh.ddshop.common.dto.Page;
import com.lh.ddshop.pojo.po.TbItem;
import com.lh.ddshop.pojo.po.TbItemExample;
import com.lh.ddshop.pojo.vo.TbItemCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TbItemCustomMapper {

    int countItems(Map<String,Object> map);

    List<TbItemCustom> listItemsByPage(Map<String,Object> map);
}