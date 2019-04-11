package cn.stu.cache;

import java.util.Collection;
import java.util.Map;

/**
 * 缓存操作的接口
 *
 * @author zhanghanlin
 *
 */
public interface Cache {

	/**
	 * 从cache中获取值
	 * 
	 * @param key cache key
	 * @return the cached object or null
	 */
	Object get(String key) ;

	/**
	 * 批量获取缓存对象
	 * @param keys cache keys
	 * @return return key-value objects
	 */
	Map<String, Object> get(Collection<String> keys);

	/**
	 * 判断缓存是否存在
	 * @param key cache key
	 * @return true if key exists
	 */
	default boolean exists(String key) {
		return get(key) != null;
	}
	
	/**
	 * 向cache中添加key
	 * @param key cache key
	 * @param value cache value
	 */
	void put(String key, Object value);

	/**
	 * 批量插入数据
	 * @param elements 需要返回的值
	 */
	void put(Map<String, Object> elements);

	/**
	 * Return all keys
	 *
	 * @return 返回键的集合
	 */
	Collection<String> keys() ;
	
	/**
	 * Remove items from the cache
	 *
	 * @param keys Cache key
	 */
	void evict(String... keys);

	/**
	 * Clear the cache
	 */
	void clear();

}
