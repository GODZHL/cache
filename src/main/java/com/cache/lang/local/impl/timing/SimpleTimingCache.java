package com.cache.lang.local.impl.timing;

import com.cache.lang.TimingCache;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.stream.Collectors;

/**
 * @author zhanghanlin
 * 带计时的缓存
 * timing cache
 */
class SimpleTimingCache<K, V> implements TimingCache<K, V> {

    //设置线程池用于清除过期数据
    private static ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(5);

    //默认超时时间(5 分钟)
    private int timing = 5 * 60;

    private final String id;

    private Map<K, V> cache;

    private List<TimeNode> timingList;

    /**
     * 封装队列中的相关参数
     */
    private static class TimeNode<K, V> {

        K key;
        //存活时间
        int time;
        //存入的系统时间
        long systime;


        public TimeNode(K key, int time, long systime) {
            this.key = key;
            this.time = time;
            this.systime = systime;

        }

        /**
         * 获取过期时间
         *
         * @return
         */
        public long getExpiredTime() {

            return systime + time * 1000;
        }


    }

    public SimpleTimingCache(String id, Map<K, V> cache, List<?> timingList) {
        this.id = id;
        this.cache = cache;
        this.timingList = (List<TimeNode>) timingList;

        deleteExpiredData();
    }

    /**
     * 清除过期数据
     */
    private void deleteExpiredData() {
        //设置清楚程序,默认为5秒启动一次
        executorService.schedule(() -> {


            List<TimeNode> expired = timingList.stream().map(timeNode -> {

                if (timeNode.getExpiredTime() < System.currentTimeMillis()) {
                    return timeNode;
                }

                return null;

            }).collect(Collectors.toList());

            timingList.removeAll(expired);

            List<K> collect = expired.stream().map(timeNode -> {
                return (K) timeNode.key;
            }).collect(Collectors.toList());

            collect.forEach(o -> {
                remove(o);
            });

        }, 5, TimeUnit.SECONDS);
    }

    @Override
    public void put(K key, V value, int time) {

        //先缓存
        cache.put(key, value);

        //创建队列节点
        TimeNode node = new TimeNode(key, time, System.currentTimeMillis());

        //存入节点
        timingList.add(node);

    }

    @Override
    public void resetDataTime(K key, int time) {

        timingList.stream().map(timeNode -> {

            if (timeNode.key == key) {
                timeNode.time = time;
            }

            return null;
        }).close();

    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void put(K key, V value) {
        put(key, value, timing);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
        timingList.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
        timingList.clear();
    }

    @Override
    public boolean contains(K key) {
        return timingList.contains(key);
    }

    @Override
    public int size() {
        return timingList.size();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }


}
