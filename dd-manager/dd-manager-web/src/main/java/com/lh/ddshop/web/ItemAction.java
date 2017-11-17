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
import org.springframework.ui.Model;
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

    @ResponseBody
    @RequestMapping("/items")
    public Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery query) {

        Result<TbItemCustom> result = null;
        try {
            result = itemService.listItemsByPage(page, order,query);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
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

    //添加商品
    @ResponseBody
    @RequestMapping("/item/add")
    public  int saveItem(TbItem tbItem,String content,String paramData){
        int i=0;
        try{
            i = itemService.saveItem(tbItem,content,paramData);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    //编辑商品
    @RequestMapping("/itemUpdate/{id}")
    public String getItemById(@PathVariable("id") Long id, Model model){
        TbItemCustom tbItemCustom = new TbItemCustom();
        try{
            tbItemCustom=itemService.getItemById(id);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        System.out.println(tbItemCustom);
        model.addAttribute("tbItemCustom", tbItemCustom);
        return "item-update";
    }

}