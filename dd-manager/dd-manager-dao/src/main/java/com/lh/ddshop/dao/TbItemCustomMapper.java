package com.lh.ddshop.dao;

import com.lh.ddshop.common.dto.Page;
import com.lh.ddshop.pojo.po.TbItem;
import com.lh.ddshop.pojo.po.TbItemExample;
import com.lh.ddshop.pojo.vo.TbItemCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemCustomMapper {

    int countItems();

    List<TbItemCustom> listItemsByPage(Page page);
}