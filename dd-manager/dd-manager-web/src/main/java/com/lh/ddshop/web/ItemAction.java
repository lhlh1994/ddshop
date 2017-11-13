package com.lh.ddshop.web;

import com.lh.ddshop.common.dto.Order;
import com.lh.ddshop.common.dto.Page;
import com.lh.ddshop.common.dto.Result;
import com.lh.ddshop.pojo.po.TbItem;
import com.lh.ddshop.pojo.vo.TbItemCustom;
import com.lh.ddshop.pojo.vo.TbItemQuery;
import com.lh.ddshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@Scope("prototype")
public class ItemAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public TbItem getById(@PathVariable("itemId") Long itemId) {
        System.out.println(itemId);
        TbItem item = itemService.getById(itemId);
        return item;
    }

    //
    @ResponseBody
    @RequestMapping("/items")
    public Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery query) {

        Result<TbItemCustom> list = null;
        try {
            list = itemService.listItemsByPage(page, order,query);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return list;
    }

    //批量删除
    @ResponseBody
    @RequestMapping(value = "/items/batch", method = RequestMethod.POST)
    public int updateItemsByIds(@RequestParam("ids[]") List<Long> ids) {
        return itemService.updateItemsByIds(ids);
    }

    //批量上架
    @ResponseBody
    @RequestMapping(value = "/items/up", method = RequestMethod.POST)
    public int upItemsByIds(@RequestParam("ids[]") List<Long> ids) {
        return itemService.upItemsByIds(ids);
    }

    //批量下架
    @ResponseBody
    @RequestMapping(value = "/items/down", method = RequestMethod.POST)
    public int downItemsByIds(@RequestParam("ids[]") List<Long> ids) {
        return itemService.downItemsByIds(ids);
    }


}