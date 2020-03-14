package com.tao.service;

import com.common.pojo.TaotaoResult;
import com.common.pojo.TreeNode;

import java.util.List;

public interface ContentCategoryService {
    public List<TreeNode> getContentCatList(Long parentId);
    public TaotaoResult insertContentCategory(Long parentId, String name);
    public TaotaoResult deleteContentCategory(Long id);
    public TaotaoResult updateContentCategory(Long id,String name);
}
