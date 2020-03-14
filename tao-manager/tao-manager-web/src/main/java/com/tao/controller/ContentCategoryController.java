package com.tao.controller;

import com.common.pojo.TaotaoResult;
import com.common.pojo.TreeNode;
import com.tao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;
    //查询内容分类管理list
    @RequestMapping("/list")
    @ResponseBody
    public List<TreeNode> getContentCatList(@RequestParam(value = "id",defaultValue = "0") Long parentId) {
        List<TreeNode> list = contentCategoryService.getContentCatList(parentId);
        return list;
    }
    //添加
    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createContentCategory(@RequestParam(defaultValue = "0") Long parentId, String name) {
        System.out.println("i:"+parentId+"n:"+name);
        TaotaoResult result = contentCategoryService.insertContentCategory(parentId, name);
        return result;
    }

    //删除
    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContentCategory(Long id) {

        TaotaoResult result = contentCategoryService.deleteContentCategory( id);
        return result;
    }

    //删除
    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult updateContentCategory(Long id,String name) {

        TaotaoResult result = contentCategoryService.updateContentCategory(id,name);
        return result;
    }
}
