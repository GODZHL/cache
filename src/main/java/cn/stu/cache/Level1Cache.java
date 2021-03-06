package cn.stu.cache;

/**
 * 一级缓存接口(本地内存缓存)
 * @author zhanghanlin
 */
public interface Level1Cache extends Cache {

    /**
     * 返回该缓存区域的 TTL 设置（单位：秒）
     * @return true if cache support ttl setting
     */
    long ttl();

    /**
     * 返回该缓存区域中，内存存储对象的最大数量
     * @return cache size in memory
     */
    long size();

}
