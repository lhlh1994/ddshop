package com.lh.ddshop.service.impl;

import com.lh.ddshop.common.dto.Order;
import com.lh.ddshop.common.dto.Page;
import com.lh.ddshop.common.dto.Result;
import com.lh.ddshop.dao.TbItemCustomMapper;
import com.lh.ddshop.dao.TbItemDescMapper;
import com.lh.ddshop.dao.TbItemMapper;
import com.lh.ddshop.pojo.po.TbItem;
import com.lh.ddshop.pojo.po.TbItemDesc;
import com.lh.ddshop.pojo.po.TbItemExample;
import com.lh.ddshop.pojo.vo.TbItemCustom;
import com.lh.ddshop.pojo.vo.TbItemQuery;
import com.lh.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import util.IDUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private TbItemMapper itemDao;
    @Autowired
    private TbItemCustomMapper itemCustomDao;
    @Autowired
    private TbItemDescMapper tbItemDescDao;




    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

    //添加事物
    @Transactional
    @Override
    public int saveItem(TbItem tbItem, String content) {
        int i=0;
        try{
            Date date = new Date();
            //这个方法要处理两张表
            //处理tb_item
            long itemId = IDUtils.getItemId();
            tbItem.setId(itemId);
            tbItem.setStatus((byte)2);
            tbItem.setCreated(date);
            tbItem.setUpdated(date);
            i = itemDao.insert(tbItem);

            //处理tb_item_desc
            TbItemDesc itemDesc = new TbItemDesc();
            itemDesc.setItemId(itemId);
            itemDesc.setItemDesc(content);
            itemDesc.setCreated(date);
            itemDesc.setUpdated(date);
            i += tbItemDescDao.insert(itemDesc);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }
}
