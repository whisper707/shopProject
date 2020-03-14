package com.portal.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tao.pojo.TbContent;
import com.portal.service.ContentService;
import com.tao.mapper.TbContentMapper;

import com.tao.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    TbContentMapper tbContentMapper;
    @Override
    public List<TbContent> getContentList(long contentCid) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria cri = example.createCriteria();
        cri.andCategoryIdEqualTo(contentCid);
        List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
        return  list;
    }
}
