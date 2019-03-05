package com.cache.lang.impl;

import com.cache.lang.BaseCache;
import com.cache.lang.local.impl.common.UnThreadSafeCache;
import org.junit.Before;

public class UnthreadCacheTest extends SimpleCacheTest{

    private BaseCache<Integer,String> cache = new UnThreadSafeCache<>("name");

    @Before
    public void init(){

        cache.put(1, "1");
        cache.put(2, "2");
        cache.put(3, "3");

        super.setCache(cache);
    }
}
