package com.cache.lang.local.impl.timing;

import com.cache.lang.TimingCache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnThreadTimeCache<K, V> extends SimpleTimingCache<K, V> implements TimingCache<K, V> {

    public UnThreadTimeCache(String id) {
        super(id, new HashMap<>(1024), new ArrayList<>());
    }

}
