package com.cache.lang.local.impl.fifo;

import com.cache.lang.BaseCache;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * First in first out的cache
 */
public class FifoCache<K, V> implements BaseCache<K, V> {

    private final BaseCache<K, V> cache;

    private final Deque<K> keyList;

    //设置fifo的默认的上限
    private int size;

    public FifoCache(BaseCache<K, V> cache) {
        this.cache = cache;
        keyList = new LinkedList<>();
        //默认的cache的大小
        this.size = 1024;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String getId() {
        return cache.getId();
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
        fifo(key);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
        keyList.remove(key);
    }

    @Override
    public void clear() {
        cache.size();
        keyList.clear();
    }

    @Override
    public boolean contains(K key) {
        return keyList.contains(key);
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
     * fifo的核心算法
     */
    private void fifo(K key) {

        keyList.add(key);

        if (keyList.size() > size) {
            K k = keyList.removeFirst();
            cache.remove(k);
        }


    }
}
