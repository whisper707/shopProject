package com.tao.service.impl;

import com.common.pojo.TreeNode;
import com.tao.mapper.TbItemCatMapper;
import com.tao.pojo.TbItemCat;
import com.tao.pojo.TbItemCatExample;
import com.tao.pojo.TbItemExample;
import com.tao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Override
    public List<TreeNode> getItemCatList(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> tlist =  tbItemCatMapper.selectByExample(example);
        List<TreeNode> list = new ArrayList<>();
        for (TbItemCat c: tlist
             ) {
            TreeNode tn = new TreeNode();
            tn.setId(c.getId());
            tn.setText(c.getName());
            tn.setState(c.getIsParent()?"closed":"open");
            list.add(tn);
        }
        return list;
    }
}
