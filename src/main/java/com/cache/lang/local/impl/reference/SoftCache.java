package com.cache.lang.local.impl.reference;

import com.cache.lang.BaseCache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.ReadWriteLock;

//todo 还没有写完
public class SoftCache<K, V> implements BaseCache<K, V> {


    private final BaseCache<K,V> cache;

    //已经被回收的object的队列
    private final ReferenceQueue<V> gcObjectQueue;

    private final Deque<V> hardLinksToAvoidCollection;

    //硬连接数
    private int hardListNumbers;

    public SoftCache(BaseCache cache) {
        this.cache = cache;
        this.hardListNumbers = 256;
        this.gcObjectQueue = new ReferenceQueue<>();
        this.hardLinksToAvoidCollection = new LinkedList<>();
    }

    @Override

    public String getId() {
        return cache.getId();
    }

    @Override
    public void put(K key, V value) {
        clearSoftRef();
        cache.put(key, (V) new SoftEntity<K,V>(key,value,gcObjectQueue));
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public void clear() {
        synchronized (hardLinksToAvoidCollection){
            hardLinksToAvoidCollection.clear();
        }
        clearSoftRef();
        cache.clear();
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }

    /**
     * 清除soft
     */
    private void clearSoftRef(){
        SoftEntity<K,V> sv;
        while ( (sv = (SoftEntity)gcObjectQueue.poll()) != null ){
            cache.remove(sv.key);
        }

    }

    private static class SoftEntity<K,V> extends SoftReference<V> {

        private final K key;

        SoftEntity(K key, V value, ReferenceQueue<V> q) {
            super(value, q);
            this.key = key;
        }
    }
}
