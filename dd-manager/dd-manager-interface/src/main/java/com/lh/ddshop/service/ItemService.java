package com.lh.ddshop.service;

import com.lh.ddshop.common.dto.Page;
import com.lh.ddshop.common.dto.Result;
import com.lh.ddshop.pojo.po.TbItem;
import com.lh.ddshop.pojo.vo.TbItemCustom;
import org.springframework.ui.Model;

import java.util.List;

public interface ItemService {
    TbItem getById(Long itemId);

    Result<TbItemCustom> listItemsByPage(Page page);

    int updateItemsByIds(List<Long> ids);

    int upItemsByIds(List<Long> ids);

    int downItemsByIds(List<Long> ids);
}
