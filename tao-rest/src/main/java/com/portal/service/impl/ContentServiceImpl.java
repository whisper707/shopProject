package com.portal.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rest.dao.JedisClient;
import com.rest.pojo.JsonUtils;
import com.tao.pojo.TbContent;
import com.portal.service.ContentService;
import com.tao.mapper.TbContentMapper;

import com.tao.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    TbContentMapper tbContentMapper;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public List<TbContent> getContentList(long contentCid) {
        try {
            String rdata = jedisClient.hget(INDEX_CONTENT_REDIS_KEY,contentCid+"");
            if (!StringUtils.isBlank(rdata)){
                List<TbContent> rlist = JsonUtils.jsonToList(rdata,TbContent.class);
                return rlist;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria cri = example.createCriteria();
        cri.andCategoryIdEqualTo(contentCid);
        List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
        try {
            String data = JsonUtils.objectToJson(list);
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY,contentCid+"",data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  list;
    }
}
