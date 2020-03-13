package com.rest.service.impl;

import com.rest.pojo.CatNode;
import com.rest.pojo.CatResult;
import com.rest.service.ItemCatService;
import com.tao.mapper.TbItemCatMapper;
import com.tao.pojo.TbItemCat;
import com.tao.pojo.TbItemCatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public CatResult getItemCatList() {
        CatResult catResult = new CatResult();
        catResult.setData(this.getCatnodelist((long)0));
        return catResult;
    }
    
    private List<CatNode> getCatnodelist(Long parentId){
        //获取第一层列表
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria cri = example.createCriteria();
        cri.andParentIdEqualTo(parentId);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        List<CatNode> clist = new ArrayList<>();
        for (TbItemCat t : list) {
            CatNode c = new CatNode();
            if (parentId == 0) {
                c.setName("<a href='/products/"+t.getId()+".html'>"+t.getName()+"</a>");
            } else {
                c.setName(t.getName());
            }
            c.setUrl("/products/"+t.getId()+".html");
            if (t.getIsParent()){
                c.setItem(this.getCatnodelist(t.getParentId()));
            }else {
                c.setItem(null);
            }
            clist.add(c);
        }
        return clist;
    }
}
