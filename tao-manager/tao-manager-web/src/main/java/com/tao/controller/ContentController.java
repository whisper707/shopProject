package com.tao.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.common.pojo.EUDataGridResult;
import com.common.pojo.TaotaoResult;
import com.tao.pojo.TbContent;
import com.tao.service.ContentService;
import com.tao.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/content ")
public class ContentController {

    @Autowired
    private ContentService contentService;
    @Reference
    private RedisService redisService;

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
        System.out.println("cid："+content.getCategoryId());
        try {
            redisService.syncContent(content.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContent(String ids) {
        //先删缓存
        try {
            String[] idss = ids.split(",");
            for (String s : idss) {
                long i = contentService.getbyids(Long.parseLong(s));
                redisService.syncContent(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //再删库
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
        try {
            redisService.syncContent(content.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}