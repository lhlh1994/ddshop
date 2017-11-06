package com.lh.ddshop.web;

import com.lh.ddshop.pojo.po.TbItem;
import com.lh.ddshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @RequestMapping(value = "/{page}")
    public ModelAndView page(@PathVariable("page") String page, ModelAndView modelAndView) {

        if ("item-list".equals(page)) {
            List<TbItem> list = itemService.findAll();
            System.out.println(list);
            modelAndView.addObject("list", list);
            modelAndView.setViewName(page);
        }

        return modelAndView;
    }


}
