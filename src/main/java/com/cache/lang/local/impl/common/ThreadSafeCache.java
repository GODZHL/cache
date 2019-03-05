package com.cache.lang.local.impl.common;

import com.cache.lang.BaseCache;

import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeCache<K,V> extends SimpleCache<K,V> implements BaseCache<K,V> {


    public ThreadSafeCache(String id) {
        super(id, new ConcurrentHashMap<>(1024));
    }
}
