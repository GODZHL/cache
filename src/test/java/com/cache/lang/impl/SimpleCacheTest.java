package com.cache.lang.impl;

import com.cache.lang.BaseCache;
import com.cache.lang.local.impl.common.ThreadSafeCache;
import org.junit.Before;
import org.junit.Test;


import org.hamcrest.Matchers;

import org.junit.After;

import static org.junit.Assert.*;

/**
 * simplecache 的测试
 */
public abstract class SimpleCacheTest {

    private BaseCache<Integer, String> cache;

    protected void setCache(BaseCache cache) {
        this.cache = cache;
    }



    @Test
    public void cacehSaveAndGetTest() {

        assertThat(cache.get(1), Matchers.equalToIgnoringCase("1"));
        assertThat(cache.get(2), Matchers.equalToIgnoringCase("2"));

    }


    @Test
    public void getIdAndHashCodeTest() {

        assertThat(cache.getId(), Matchers.equalToIgnoringCase("name"));
    }

    @Test
    public void removeAndClearTest() {

        cache.remove(1);
        assertFalse(cache.contains(1));
    }


    @Test
    public void countTest() {
        assertThat(cache.size(), Matchers.is(3));
    }

    @After
    public void destory() {
        cache.clear();
    }

}
