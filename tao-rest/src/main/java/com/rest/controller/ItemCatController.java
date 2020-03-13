package com.rest.controller;

import com.rest.pojo.CatResult;
import com.rest.pojo.JsonUtils;
import com.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemCatController {
    @Autowired
    ItemCatService itemCatService;

    @RequestMapping(value="/itemcat/list",
            produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody

    public String  getItemCatList(String callback){
        CatResult catResult = itemCatService.getItemCatList();
        //转型json
        String json = JsonUtils.objectToJson(catResult);
        //转jsonp
        String data = callback + "(" + json + ");";
        return data;
    }
    @RequestMapping(value="/t")
    @ResponseBody
    public String  t(String callback){
        System.out.println("t");
        CatResult catResult = itemCatService.getItemCatList();
        //转型json
        String json = JsonUtils.objectToJson(catResult);
        //转jsonp
        String data = callback + "(" + json + ");";
        return data;
    }

}
