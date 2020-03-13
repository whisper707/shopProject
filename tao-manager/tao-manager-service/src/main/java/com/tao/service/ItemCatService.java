package com.tao.service;

import com.common.pojo.TreeNode;

import java.util.List;

public interface ItemCatService {
    public List<TreeNode> getItemCatList(long parentId);
}
