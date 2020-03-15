package com.rest.service.impl;

import com.rest.dao.JedisClient;
import com.rest.pojo.CatNode;
import com.rest.pojo.CatResult;
import com.rest.service.ItemCatService;
import com.tao.mapper.TbItemCatMapper;
import com.tao.pojo.TbItemCat;
import com.tao.pojo.TbItemCatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        catResult.setData(this.getCatList1((long)0));
        return catResult;
    }
    
    private List<Object> getCatList1(Long parentId){
        //获取第一层列表
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria cri = example.createCriteria();
        cri.andParentIdEqualTo(parentId);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        List<Object> clist = new ArrayList<>();
        //list存值
        for (TbItemCat t : list) {
            CatNode c = new CatNode();
            //判断是否有子分类
            if (t.getIsParent()) {
                //name
                if (parentId == 0) {
                    c.setName("<a href='/products/" + t.getId() + ".html'>" + t.getName() + "</a>");
                } else {
                    c.setName(t.getName());
                }
                //url
                c.setUrl("/products/" + t.getId());
                //item
                c.setItem(getCatList1(t.getId()));
                clist.add(c);
            //无子分类
            }else {
                System.out.println("/products/"+t.getId()+"|" + t.getName());
                clist.add("/products/"+t.getId()+"|" + t.getName());
            }
        }
        return clist;
    }
    /**
     * 查询分类列表
     * <p>Title: getCatList</p>
     * <p>Description: </p>
     * @param parentId
     * @return
     */
    private List<?> getCatList(long parentId) {
        //创建查询条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        //返回值list
        List resultList = new ArrayList<>();
        //向list中添加节点
        for (TbItemCat tbItemCat : list) {
            //判断是否为父节点
            if (tbItemCat.getIsParent()) {
                CatNode catNode = new CatNode();
                if (parentId == 0) {
                    catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                } else {
                    catNode.setName(tbItemCat.getName());
                }
                catNode.setUrl("/products/"+tbItemCat.getId()+".html");
                catNode.setItem(getCatList(tbItemCat.getId()));

                resultList.add(catNode);
                //如果是叶子节点
            } else {
                resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
            }
        }
        return resultList;
    }

}
