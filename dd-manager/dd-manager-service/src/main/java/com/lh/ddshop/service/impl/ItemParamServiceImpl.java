package com.lh.ddshop.service.impl;

import com.lh.ddshop.common.dto.Order;
import com.lh.ddshop.common.dto.Page;
import com.lh.ddshop.common.dto.Result;
import com.lh.ddshop.dao.TbItemParamCustomMapper;
import com.lh.ddshop.pojo.vo.TbItemCustom;
import com.lh.ddshop.pojo.vo.TbItemParamCustom;
import com.lh.ddshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemParamCustomMapper itemParamCustomDao;


    @Override
    public Result<TbItemParamCustom> listItemParamsByPage(Page page) {
        Result<TbItemParamCustom> result=null;
        try{
            HashMap<String, Object> map = new HashMap<>();
            map.put("page",page);
            //1.创建一个响应参数的实体类
            result=new Result<TbItemParamCustom>();
            //2.对total进行设值(符合条件的总条数)
            int total=itemParamCustomDao.countItemParams();
            result.setTotal(total);
            //3.对rows进行设值(指定页码显示的数据集合)
            List<TbItemParamCustom> list=itemParamCustomDao.listItemParamsByPage(map);
            result.setRows(list);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

        return result;
    }
}
