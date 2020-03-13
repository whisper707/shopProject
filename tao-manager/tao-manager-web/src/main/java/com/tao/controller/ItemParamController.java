package com.tao.controller;

import com.common.pojo.EUDataGridResult;
import com.common.pojo.TaotaoResult;
import com.tao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/query/itemcatid/{id}")
    @ResponseBody
    public TaotaoResult test(@PathVariable("id") Long itemCatId) {

        return itemParamService.selectItemParamByItemId(itemCatId);
    }
    @RequestMapping("save/{cid}")
    @ResponseBody
    public TaotaoResult saveParam(@PathVariable("cid") Long itemCatId , String paramData) {

        return itemParamService.saveItemParam(itemCatId, paramData);
    }

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult list(Integer page,Integer rows) {
        return itemParamService.getItemList(page, rows);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult test(String ids) {

        return itemParamService.deleteItemParam(ids);
    }

//@RequestMapping("/list")
//    @ResponseBody
//    public TaotaoResult test() {

//        return null;
//    }
}
