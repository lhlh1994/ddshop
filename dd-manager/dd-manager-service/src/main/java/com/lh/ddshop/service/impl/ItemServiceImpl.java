package com.lh.ddshop.service.impl;

import com.lh.ddshop.dao.TbItemMapper;
import com.lh.ddshop.pojo.po.TbItem;
import com.lh.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getById(Long itemId) {
        return itemMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public List<TbItem> findAll() {
        return itemMapper.selectAll();
    }
}
