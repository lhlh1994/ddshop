package com.lh.ddshop.service.impl;

import com.lh.ddshop.common.dto.Order;
import com.lh.ddshop.common.dto.Page;
import com.lh.ddshop.common.dto.Result;
import com.lh.ddshop.dao.TbItemCustomMapper;
import com.lh.ddshop.dao.TbItemMapper;
import com.lh.ddshop.pojo.po.TbItem;
import com.lh.ddshop.pojo.po.TbItemExample;
import com.lh.ddshop.pojo.vo.TbItemCustom;
import com.lh.ddshop.pojo.vo.TbItemQuery;
import com.lh.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private TbItemMapper itemDao;
    @Autowired
    private TbItemCustomMapper itemCustomDao;


    @Override
    public TbItem getById(Long itemId) {

        return itemDao.selectByPrimaryKey(itemId);
    }

    @Override
    public Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery query) {
        Result<TbItemCustom> result=null;
        try{
            HashMap<String, Object> map = new HashMap<>();
            map.put("page",page);
            map.put("order",order);
            map.put("query",query);
            //1.创建一个响应参数的实体类
           result=new Result<TbItemCustom>();
           //2.对total进行设值(符合条件的总条数)
           int total=itemCustomDao.countItems(map);
            result.setTotal(total);
           //3.对rows进行设值(指定页码显示的数据集合)
           List<TbItemCustom> list=itemCustomDao.listItemsByPage(map);
            result.setRows(list);
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int updateItemsByIds(List<Long> ids) {
        TbItem record = new TbItem();
        record.setStatus((byte)3);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return itemDao.updateByExampleSelective(record,example);
    }

    @Override
    public int upItemsByIds(List<Long> ids) {
        TbItem record = new TbItem();
        record.setStatus((byte)1);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return itemDao.updateByExampleSelective(record,example);
    }

    @Override
    public int downItemsByIds(List<Long> ids) {
        TbItem record = new TbItem();
        record.setStatus((byte)2);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return itemDao.updateByExampleSelective(record,example);
    }
}
