package com.lh.ddshop.dao;

import com.lh.ddshop.pojo.vo.TbItemParamCustom;

import java.util.HashMap;
import java.util.List;

public interface TbItemParamCustomMapper {

    List<TbItemParamCustom> listItemParamsByPage(HashMap<String, Object> map);

    int countItemParams();
}
