package com.lh.ddshop.service;

import com.lh.ddshop.common.dto.TreeNode;

import java.util.List;

public interface TbItemCatService {
    List<TreeNode> listItemCatsByPid(Long parentId);
}

