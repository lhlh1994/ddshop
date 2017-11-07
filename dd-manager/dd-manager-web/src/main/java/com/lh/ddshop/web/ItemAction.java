package com.lh.ddshop.web;

import com.lh.ddshop.common.dto.Page;
import com.lh.ddshop.common.dto.Result;
import com.lh.ddshop.pojo.po.TbItem;
import com.lh.ddshop.pojo.vo.TbItemCustom;
import com.lh.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Scope("prototype")
public class ItemAction {

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
    public Result<TbItemCustom> listItemsByPage(Page page){

        Result<TbItemCustom> list=null;
        try {
            list = itemService.listItemsByPage(page);
        }catch (Exception e){

            e.printStackTrace();
        }
        return list;
    }


}
