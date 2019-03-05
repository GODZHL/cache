package com.cache.lang.local.impl.common;

import com.cache.lang.BaseCache;
import com.cache.lang.local.LocalCacheException;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Cache的简单实现
 * @param <K>
 * @param <V>
 */
class SimpleCache<K, V> implements BaseCache<K, V> {

    private final String id;

    private Map<K, V> cache ;

    public SimpleCache(String id, Map<K, V> cache) {
        this.id = id;
        this.cache = cache;
    }

    @Override
    public String getId() {
        return this.id;
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
        return cache.containsKey(key);
    }

    @Override
    public int size() {
        return cache.size();
    }


    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }

    @Override
    public int hashCode() {
        if (this.id == null) {
            throw new LocalCacheException("没有的Cache  id");
        }

        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (getId() == null || !(obj instanceof BaseCache)) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        return getId() == (((BaseCache) obj).getId());

    }

}
