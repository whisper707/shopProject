package com.tao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rest.dao.JedisClient;
import com.tao.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Service
public class RedisServiceImpl implements RedisService {

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public boolean syncContent(long contentCid) {
        try {
            jedisClient.hdel(INDEX_CONTENT_REDIS_KEY,contentCid+"");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
