package com.lh.ddshop.web;

import com.lh.ddshop.common.dto.Order;
import com.lh.ddshop.common.dto.Page;
import com.lh.ddshop.common.dto.Result;
import com.lh.ddshop.pojo.po.TbItemParam;
import com.lh.ddshop.pojo.vo.TbItemParamCustom;
import com.lh.ddshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemParamAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemParamService itemParamService;

    @ResponseBody
    @RequestMapping("/itemParams")
    public Result<TbItemParamCustom> listItemParamsByPage(Page page){

        Result<TbItemParamCustom> result = null;
        try {
            result = itemParamService.listItemParamsByPage(page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;


    }


    @ResponseBody
    @RequestMapping(value = "/itemParam/query/{cid}",method= RequestMethod.GET)
    public TbItemParam getItemByCid(@PathVariable("cid") Long cid){
        TbItemParam tbItemParam = null;
        try{
            tbItemParam = itemParamService.getItemByCid(cid);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        System.out.println(tbItemParam.getId());
        return tbItemParam;
    }


}
