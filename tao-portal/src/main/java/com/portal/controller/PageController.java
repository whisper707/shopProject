package com.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.common.util.JsonUtils;
import com.portal.service.ContentService;
import com.tao.pojo.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {

    @Reference//import com.alibaba.dubbo.config.annotation.Reference;
    private ContentService contentService;

    @RequestMapping("/index")
    public String init(Model model){
        System.out.println("index");
        List<TbContent> tblist=contentService.getContentList(89);

        System.out.println("index"+tblist.size());
        List<Map> resultList = new ArrayList<>();
        //创建一个jsp页码要求的pojo列表
        for (TbContent tbContent : tblist) {
            Map map = new HashMap<>();
            map.put("src", tbContent.getPic());
            map.put("height", 240);
            map.put("width", 670);
            map.put("srcB", tbContent.getPic2());
            map.put("widthB", 550);
            map.put("heightB", 240);
            map.put("href", tbContent.getUrl());
            map.put("alt", tbContent.getSubTitle());
            resultList.add(map);
        }


        String adJson = JsonUtils.objectToJson(resultList);
        model.addAttribute("ad1", adJson);
        System.out.println("index"+adJson);

        return "index";
    }

//    @RequestMapping("/index")
//    public String page(){
//        return "index";
//    }
}
