package com.lh.ddshop.service;

import com.lh.ddshop.pojo.po.TbItem;

import java.util.List;

public interface ItemService {

    TbItem getById(Long itemId);


    List<TbItem> findAll();
}
