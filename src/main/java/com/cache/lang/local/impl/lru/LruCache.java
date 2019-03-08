package com.cache.lang.local.impl.lru;

import com.cache.lang.BaseCache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * lru(最近最少使用)算法
 */
public class LruCache<K,V> implements BaseCache<K,V> {

    private final BaseCache<K,V> cache;

    private Map<Object,Object> keyMap;

    private Object eldestKey;

    public LruCache(BaseCache<K, V> cache) {
        this.cache = cache;
        setSize(1024);
    }


    public void setSize(final int size){

        keyMap = new LinkedHashMap<Object, Object>(size,0.75F,true){

            @Override
            protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {

                boolean tooBig = size() > size;

                if(tooBig){
                    eldestKey = eldest.getKey();
                }

                return tooBig;
            }
        };


    }


    @Override
    public String getId() {
        return cache.getId();
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
        keyMap.put(key,key);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
        keyMap.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
        keyMap.clear();
    }

    @Override
    public boolean contains(K key) {
        return cache.contains(key);
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }

    /**
     * lru
     */
    private void lru(K key){

        keyMap.put(key,key);

        if (eldestKey != null){
            cache.remove(key);
            eldestKey = null;
        }

    }
}
