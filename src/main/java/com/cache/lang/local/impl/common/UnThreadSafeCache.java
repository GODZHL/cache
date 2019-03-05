package com.cache.lang.local.impl.common;

import com.cache.lang.BaseCache;

import java.util.HashMap;

/**
 * 非线程安全的simple cache
 */
public class UnThreadSafeCache<K,V> extends SimpleCache<K,V> implements BaseCache<K,V> {

    public UnThreadSafeCache(String id) {
        super(id, new HashMap<>(1024));
    }

}
