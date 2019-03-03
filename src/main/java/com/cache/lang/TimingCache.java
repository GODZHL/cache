package com.cache.lang;

public interface TimingCache<K,V> extends BaseCache<K,V> {

    /**
     * 缓存时有时间要求
     * @param key
     * @param value
     * @param time
     */
    void put(K key, V value,int time);


    /**
     * 重置缓存时间
     * @param key
     * @param time
     */
    void resetDataTime(K key,int time);

}
