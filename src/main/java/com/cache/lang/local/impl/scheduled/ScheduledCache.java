package com.cache.lang.local.impl.scheduled;

import com.cache.lang.BaseCache;

import java.io.Serializable;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

public class ScheduledCache<K, V> implements BaseCache<K, V>  {



    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

    private final BaseCache<K, V> cache;

    private long clearTime;

    private long lastClear;

    public ScheduledCache(BaseCache<K, V> cache) {
        this.cache = cache;
        this.clearTime = 60 * 60 * 1000;
        this.lastClear = System.currentTimeMillis();

        scheduledClear();
    }

    /**
     * 定时清除
     */
    private void scheduledClear() {

        executor.schedule(() -> {
            if (System.currentTimeMillis() - lastClear > clearTime) {
                cache.clear();
                lastClear = System.currentTimeMillis();
            }
        }, 60, TimeUnit.HOURS);

    }

    @Override
    public String getId() {
        return cache.getId();
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
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


}
