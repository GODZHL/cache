package cn.stu.cache;

import java.util.Collection;
import java.util.Properties;

/**
 * 可拔插的cache
 * @author zhanghanlin
 */
public interface CacheProvider {

	/**
	 * 缓存的标识名称
	 * @return return cache provider name
	 */
	String name();

	/**
	 * 缓存的层级
	 * @return current provider level
	 */
	int level();

	/**
	 * 判断当前的缓存是不是该等级
	 * @param level
	 * @return
	 */
	default boolean isLevel(int level) {
		return (level() & level) == level;
	}
	
	/**
	 * Configure the cache
	 *
	 * @param regionName the name of the cache region
	 * @param listener listener for expired elements
	 * @return return cache instance
	 */
	Cache buildCache(String regionName, CacheExpiredListener listener);

	/**
	 * Configure the cache with timeToLiveInMills（设置缓存失效时间）
	 * @param region cache region name
	 * @param timeToLiveInSeconds time to live in second
	 * @param listener listener for expired elements
	 * @return return cache instance
	 */
	Cache buildCache(String region, long timeToLiveInSeconds, CacheExpiredListener listener);

	/**
	 * Remove a cache region
	 * @param region cache region name
	 */
	default void removeCache(String region) {}

	/**
	 * Return all channels defined in first level cache
	 * @return all regions name
	 */
	Collection<CacheChannel.Region> regions();

	/**
	 * Callback to perform any necessary initialization of the underlying cache implementation
	 * during SessionFactory construction.
	 *
	 * @param props current configuration settings.
	 */
	void start(Properties props);

	/**
	 * Callback to perform any necessary cleanup of the underlying cache implementation
	 * during SessionFactory.close().
	 */
	void stop();

}
