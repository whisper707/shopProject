package com.tao.controller;

import com.common.pojo.EUDataGridResult;

import com.common.pojo.TaotaoResult;
import com.tao.pojo.TbContent;
import com.tao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/content ")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/query/list")
    @ResponseBody
    public EUDataGridResult getContentList(Integer page, Integer rows, Long categoryId) {
        EUDataGridResult e = contentService.getContentList(page, rows, categoryId);
        return e;
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult saveContent(TbContent content) {
        TaotaoResult result = contentService.saveContent(content);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContent(String ids) {
        TaotaoResult result = contentService.deleteContent(ids);
        return result;
    }

    /**
     * 更新
     * @param content
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public TaotaoResult editContent(TbContent content) {
        TaotaoResult result = contentService.updateContent(content);
        return result;
    }

}