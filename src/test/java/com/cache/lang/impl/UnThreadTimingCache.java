package com.cache.lang.impl;

import com.cache.lang.TimingCache;
import com.cache.lang.local.impl.timing.ThreadSafeTimingCache;
import org.junit.Before;

public class UnThreadTimingCache extends SimpleTimingCacheTest {

    private TimingCache<Integer,String> TimingCache = new ThreadSafeTimingCache("name");

    @Before
    public void init(){
        super.setCache(TimingCache);

        TimingCache.put(1,"1",3);
    }

}
