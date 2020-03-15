package com.rest.dao;

public interface JedisClient {
    /**
     * 取
     * @param key
     * @return
     */
    public String get(String key);
    /**
     * 存
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value);

    /**
     * 取h
     * @param hkey
     * @param key
     * @return
     */
    public String hget(String hkey, String key);


    /**
     * 存 h
     *  a  11   get(a)
     *

     *  get c c1
     * @param hkey
     * @param key
     * @param value
     * @return
     */
    public long hset(String hkey, String key, String value) ;

    /**
     * 取自增长的值
     * @param key
     * @return
     */
    public long incr(String key);
    /**
     * 设置有效时间
     * @param key
     * @param second
     * @return
     */
    public long expire(String key, int second) ;

    /**
     * 查看有效时间
     * @param key
     * @return
     */
    public long ttl(String key) ;

    /**
     * 删除普通
     * @param key
     * @return
     */
    public long del(String key) ;
    /**
     * 删除hash 值
     * @param key
     * @return
     */
    public long hdel(String hkey,String key) ;

}
