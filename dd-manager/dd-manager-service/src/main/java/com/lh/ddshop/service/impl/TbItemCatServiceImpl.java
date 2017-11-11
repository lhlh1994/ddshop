package com.lh.ddshop.service.impl;

import com.lh.ddshop.common.dto.TreeNode;
import com.lh.ddshop.dao.TbItemCatMapper;
import com.lh.ddshop.pojo.po.TbItemCat;
import com.lh.ddshop.pojo.po.TbItemCatExample;
import com.lh.ddshop.service.TbItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TbItemCatServiceImpl implements TbItemCatService{

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TreeNode> listItemCatsByPid(Long parentId) {
        List<TreeNode> treeNodeList=null;
        try{
            //创建查询模板
            TbItemCatExample example = new TbItemCatExample();
            TbItemCatExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(parentId);
            //执行查询
            List<TbItemCat> tbItemCatList = tbItemCatMapper.selectByExample(example);
            //遍历查询出来的列表,生成新的列表
            treeNodeList=new ArrayList<>();
            for(int i=0;i<tbItemCatList.size();i++){
                TbItemCat itemCat = tbItemCatList.get(i);
                TreeNode treeNode=new TreeNode();
                treeNode.setId(itemCat.getId());
                treeNode.setText(itemCat.getName());
                treeNode.setState(itemCat.getIsParent() ? "closed":"open");
                treeNodeList.add(treeNode);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return treeNodeList;
    }
}