package com.tao.controller;

import com.common.pojo.EUDataGridResult;
import com.common.pojo.TaotaoResult;
import com.tao.pojo.TbItem;
import com.tao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;


    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult alllist( int page, int rows) {
        EUDataGridResult e = itemService.getList(page, rows);
        return e;
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult siveItem(TbItem tbItem, String desc,String itemParams) {

        return itemService.saveItem(tbItem, desc,itemParams);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteItem(String ids) {

        return itemService.deleteItem(ids);
    }

    @RequestMapping("/rest/desc/{id}")
    @ResponseBody
    public TaotaoResult selectDesc(@PathVariable("id") Long id) {

        return itemService.selectDesc(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult updateItem(TbItem item,String desc) {

        return itemService.updateItem(item, desc);
    }
//    @RequestMapping("/list")
//    @ResponseBody
//    public TaotaoResult test() {

//        return null;
//    }
}
