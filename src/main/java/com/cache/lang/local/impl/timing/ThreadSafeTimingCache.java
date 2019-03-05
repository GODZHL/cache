package com.cache.lang.local.impl.timing;

import com.cache.lang.TimingCache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadSafeTimingCache<K, V> extends SimpleTimingCache<K, V> implements TimingCache<K, V> {

    public ThreadSafeTimingCache(String id) {
        super(id, new ConcurrentHashMap<>(1024), new CopyOnWriteArrayList());
    }
}
