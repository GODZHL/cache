package com.cache.lang;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author zhanghanlin
 * 最基础的cache
 */
public interface BaseCache<K, V> {


    /**
     * 获取缓存器的id
     * @return
     */
    String getId();


    /**
     * 放入数据
     *
     * @param key
     * @param value
     */
    void put(K key, V value);

    /**
     * 拿到数据
     *
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 移除数据
     *
     * @param key
     */
    void remove(K key);

    /**
     * 清楚数据
     */
    void clear();

    /**
     * 包含数据key
     *
     * @param key
     * @return
     */
    boolean contains(K key);

    /**
     * 获取cache的大小
     *
     * @return
     */
    int size();

    /**
     * 读写缩
     * @return
     */
    ReadWriteLock getReadWriteLock();
}
