package com.tao.controller;

import com.common.pojo.TreeNode;
import com.tao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;
    @RequestMapping("/list")
    @ResponseBody
    public List<TreeNode> catList(@RequestParam(value = "id" ,defaultValue = "0") Long id) {
        List<TreeNode> list = itemCatService.getItemCatList(id);
        return list;
    }
}
