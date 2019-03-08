package com.cache.lang.local.impl.serializable;

import com.cache.lang.BaseCache;

import java.io.Serializable;
import java.util.concurrent.locks.ReadWriteLock;

public class SerializableCache<K,V extends Serializable> implements BaseCache<K,V> {

    private final BaseCache<K,V> cache;

    public SerializableCache(BaseCache<K, V> cache) {
        this.cache = cache;
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
