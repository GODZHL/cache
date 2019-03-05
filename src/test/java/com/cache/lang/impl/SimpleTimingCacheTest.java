package com.cache.lang.impl;

import com.cache.lang.TimingCache;
import com.cache.lang.local.impl.timing.UnThreadTimeCache;
import org.junit.Before;
import org.junit.Test;

import org.hamcrest.Matchers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 过期cache测试
 */
public class SimpleTimingCacheTest {

    TimingCache<Integer,String> timingCache;


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
