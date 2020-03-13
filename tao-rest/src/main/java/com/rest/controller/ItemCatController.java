package com.rest.controller;

import com.rest.pojo.CatResult;
import com.rest.pojo.JsonUtils;
import com.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ItemCatController {
    @Autowired
    ItemCatService itemCatService;

    public String  getItemCatList(String callback){
        CatResult catResult = itemCatService.getItemCatList();
        //转型json
        String json = JsonUtils.objectToJson(catResult);
        //转jsonp
        String data = callback + "(" + json + ");";
        return data;
    }

}
