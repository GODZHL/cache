package com.cache.lang.impl;

import com.cache.lang.TimingCache;
import com.cache.lang.local.impl.timing.ThreadSafeTimingCache;
import com.cache.lang.local.impl.timing.UnThreadTimeCache;
import org.junit.*;

import org.hamcrest.Matchers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 过期cache测试
 */
public class SimpleTimingCacheTest {

    TimingCache<Integer,String> timingCache;

    @Before
    public void init(){
        timingCache =  new ThreadSafeTimingCache("name");
        timingCache.put(1,"1",3);
    }


    protected void setCache(TimingCache cache){
        this.timingCache = cache;
    }

    @Test
    public void makeTimingCacheTureTest() throws Exception{

        String s = timingCache.get(1);

        assertThat(s,Matchers.equalToIgnoringCase("1"));

        Thread.sleep(10000);

        timingCache.get(1);

        assertThat(timingCache.get(1),Matchers.isEmptyOrNullString());


    }
}
