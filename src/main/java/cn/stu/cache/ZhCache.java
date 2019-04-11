package cn.stu.cache;

import java.io.IOException;

/**
 * ZhCache 的缓存入口
 * @author zhanghanlin
 */
public class ZhCache {

	//配置ZhCache的文件地址
	private final static String CONFIG_FILE = "/j2cache.properties";

	private final static ZhCacheBuilder builder;


	//init and load properties
	static {
		try {
            ZhCacheConfig config = ZhCacheConfig.initFromConfig(CONFIG_FILE);
            builder = ZhCacheBuilder.init(config);
		} catch (IOException e) {
			throw new CacheException("Failed to load cache configuration " + CONFIG_FILE, e);
		}
	}

	/**
	 * 返回缓存操作接口
	 * @return CacheChannel
	 */
	public static CacheChannel getChannel(){
		return builder.getChannel();
	}

    /**
     * 关闭 ZhCache
     */
	public static void close() {
	    builder.close();
    }
}
