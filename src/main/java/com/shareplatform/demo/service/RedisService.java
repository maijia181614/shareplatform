package com.shareplatform.demo.service;


import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis操作service
 * 对象信息和数组都以json格式存储
 */
public interface RedisService {

    /**
     * 存储数据
     * @param key
     * @param value
     */
    void set(String key, Object value);

    /**
     * 存储数据
     * @param key
     * @param value
     * @param time 过期时间
     */
    void set(String key, Object value,long time);

    /**
     * 获取数据
     * @param key
     * @return
     */
    Object get(String key);


    /**
     *删除属性
     * @param key
     * @return
     */
    Boolean del(String key);

    /**
     * 批量删除属性
     * @param key
     * @return
     */
    Long del(List<String> keys);

    /**
     * 设置过期时间
     * @param key
     * @param expire
     * @return
     */
    boolean expire(String key,long expire);

    /**
     *获取过期时间
     * @param key
     * @return
     */
    Long getExpire(String key);

    /**
     * 判断是否有该属性
     * @param key
     * @return
     */
    Boolean hasKey(String key);

    /**
     * 删除数据
     * @param key
     */
    void remove(String key);

    /**
     * 批量删除数据
     * @param key
     */
    Long remove(List<String> keys);

    /**
     * 按delta递增
     * @param key
     * @param delta
     * @return
     */
    Long increment(String key, long delta);

    /**
     *按delta递减
     * @param key
     * @param delta
     * @return
     */
    Long decrement(String key, long delta);

    /**
     * 获取hash结构中的属性
     * @param key
     * @param hashKey
     * @return
     */
    Object hGet(String key, String hashKey);

    /**
     *向hash结构中加入属性，带过期时间
     * @param key
     * @param hashKey
     * @param value
     * @param time
     * @return
     */
    Boolean hSet(String key, String hashKey, Object value, long time);

    /**
     * 向hash结构中加入属性
     * @param key
     * @param hashKey
     * @param value
     */
    void hSet(String key, String hashKey, Object value);

    /**
     * 获取整个hash结构
     * @param key
     * @return
     */
    Map<Object,Object> hGetAll(String key);

    /**
     *直接设置整个hash结构,带过期时间
     * @param key
     * @param map
     * @param time
     * @return
     */
    Boolean hSetAll(String key, Map<String, Object> map,  long time);

    /**
     *直接设置整个hash结构
     * @param key
     * @param map
     * @return
     */
    void hSetAll(String key, Map<String,?> map);

    /**
     *删除hash结构中的属性
     * @param key
     * @param hashKey
     */
    void hDel(String key, Object... hashKey);

    /**
     *判断hash中是否有该属性
     * @param key
     * @param hashKey
     * @return
     */
    Boolean hHasKey(String key, String hashKey);

    /**
     *Hash结构中的属性自增
     * @param key
     * @param hashKey
     * @param delta
     * @return
     */
    Long hIncr(String key, String hashKey, Long delta);

    /**
     *Hash结构中的属性自减
     * @param key
     * @param hashKey
     * @param delta
     * @return
     */
    Long hDecr(String key, String hashKey, Long delta);

    /**
     *获取set结构
     * @param key
     * @return
     */
    Set<Object> sMembers(String key);

    /**
     *向set结构中添加属性
     * @param key
     * @param values
     * @return
     */
    Long sAdd(String key, Object... values);

    /**
     *向set结构中添加属性,带过期时间
     * @param key
     * @param time
     * @param values
     * @return
     */
    Long sAdd(String key, long time, Object... values);

    /**
     * 是否为set中的属性
     * @param key
     * @param value
     * @return
     */
    Boolean sIsMember(String key, Object value);

    /**
     *获取set结构的长度
     * @param key
     * @return
     */
    Long sSize(String key);

    /**
     * 删除set结构中的属性
     * @param key
     * @param values
     * @return
     */
    Long sRemove(String key, Object... values);

    /**
     * 获取list结构中的属性
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<Object> lRange(String key, long start, long end);

    /**
     *获取List结构的长度
     * @param key
     * @return
     */
    Long lSize(String key);

    /**
     * 根据索引获取list结构中的属性
     * @param key
     * @param index
     * @return
     */
    Object lIndex(String key, long index);

    /**
     *向list结构中添加属性
     * @param key
     * @param value
     * @return
     */
    Long lPush(String key, Object value);

    /**
     *向list结构中添加属性,带过期时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    Long lPush(String key, Object value, long time);

    /**
     * 向list结构中批量添加属性
     * @param key
     * @param values
     * @return
     */
    Long lPushAll(String key, Object... values);

    /**
     * 向list结构中批量添加属性,带过期时间
     * @param key
     * @param time
     * @param values
     * @return
     */
    Long lPushAll(String key, Long time, Object... values);

    /**
     * 从list结构中删除属性
     * @param key
     * @param count
     * @param value
     * @return
     */
    Long lRemove(String key, long count, Object value);

}
