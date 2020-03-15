package com.tao.service.impl;

import com.common.pojo.EUDataGridResult;
import com.common.pojo.TaotaoResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tao.mapper.TbContentMapper;
import com.tao.pojo.TbContent;
import com.tao.pojo.TbContentExample;
import com.tao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;
    @Override
    public EUDataGridResult getContentList(Integer page, Integer rows, Long categoryId) {

        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        PageHelper.startPage(page,rows);
        List<TbContent> clist = tbContentMapper.selectByExample(example);
        PageInfo<TbContent> info = new PageInfo<>(clist);
        EUDataGridResult e = new EUDataGridResult();
        e.setTotal(info.getTotal());
        e.setRows(clist);
        return e;
    }

    @Override
    public TaotaoResult saveContent(TbContent content) {
        try {
            //补全pojo内容
            content.setCreated(new Date());
            content.setUpdated(new Date());
            tbContentMapper.insert(content);
        } catch (Exception e) {
            e.printStackTrace();
            TaotaoResult.build(400,"添加失败");
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteContent(String ids) {
        try {
            String[] idss = ids.split(",");
            for (String s:idss
                 ) {
                tbContentMapper.deleteByPrimaryKey(Long.parseLong(s));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            TaotaoResult.build(400,"删除失败");
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateContent(TbContent content) {
        try {
            content.setUpdated(new Date());
            tbContentMapper.updateByPrimaryKeyWithBLOBs(content);
        } catch (Exception e) {
            e.printStackTrace();
            TaotaoResult.build(400,"修改失败");
        }
        return TaotaoResult.ok();

    }

    @Override
    public long getbyids(Long ids) {
        System.out.println(ids);
        TbContent t = tbContentMapper.selectByPrimaryKey(ids);
        return t.getCategoryId();
    }
}
