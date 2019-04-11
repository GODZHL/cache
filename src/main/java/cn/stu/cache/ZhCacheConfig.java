package cn.stu.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * ZhCache 配置类(不可继承)
 *
 * @author zhanghanlin
 */
public final class ZhCacheConfig {

    private final static Logger log = LoggerFactory.getLogger(ZhCacheConfig.class);

    //全局配置
    private Properties properties = new Properties();
    //广播配置
    private Properties broadcastProperties = new Properties();
    //一级缓存配置
    private Properties l1CacheProperties = new Properties();
    //二级缓存配置
    private Properties l2CacheProperties = new Properties();

    private String broadcast;
    private String l1CacheName;
    private String l2CacheName;
    private String serialization;
    private boolean syncTtlToRedis;
    private boolean defaultCacheNullObject;

    /**
     * Read configuration from resource（path ）
     *
     * @param configResource config resource
     */
    public final static ZhCacheConfig initFromConfig(String configResource) throws IOException {
        try (InputStream stream = getConfigStream(configResource)) {
            return initFromConfig(stream);
        }
    }

    /**
     * Read configuration from file
     *
     * @param configFile config file
     */
    public final static ZhCacheConfig initFromConfig(File configFile) throws IOException {
        try (FileInputStream stream = new FileInputStream(configFile)) {
            return initFromConfig(stream);
        }
    }

    /**
     * Read configuration from input stream
     *
     * @param stream config stream
     */
    public final static ZhCacheConfig initFromConfig(InputStream stream) throws IOException {

        Properties properties = new Properties();
        properties.load(stream);
        return initFromConfig(properties);

    }

    /**
     * Read configuration from properties(base method)
     * todo 修改配置属性
     * @param properties config properties
     */
    public final static ZhCacheConfig initFromConfig(Properties properties) {

        ZhCacheConfig config = new ZhCacheConfig();
        config.properties = properties;

        config.serialization = trim(config.properties.getProperty("j2cache.serialization"));
        config.broadcast = trim(config.properties.getProperty("j2cache.broadcast"));
        config.l1CacheName = trim(config.properties.getProperty("j2cache.L1.provider_class"));
        config.l2CacheName = trim(config.properties.getProperty("j2cache.L2.provider_class"));
        config.syncTtlToRedis = !"false".equalsIgnoreCase(trim(config.properties.getProperty("j2cache.sync_ttl_to_redis")));
        config.defaultCacheNullObject = "true".equalsIgnoreCase(trim(config.properties.getProperty("j2cache.default_cache_null_object")));

        String l2_config_section = trim(config.properties.getProperty("cache.L2.config_section"));

        if (l2_config_section == null || "".equals(l2_config_section.trim())) {
            l2_config_section = config.l2CacheName;
        }

        config.broadcastProperties = config.getSubProperties(config.broadcast);
        config.l1CacheProperties = config.getSubProperties(config.l1CacheName);
        config.l2CacheProperties = config.getSubProperties(l2_config_section);

        return config;
    }

    /**
     * read sub properties by prefix
     *
     * @param i_prefix prefix of config
     * @return properties without prefix
     */
    public Properties getSubProperties(String i_prefix) {
        Properties props = new Properties();
        final String prefix = i_prefix + '.';
        properties.forEach((k, v) -> {
            String key = (String) k;
            if (key.startsWith(prefix)) {
                props.setProperty(key.substring(prefix.length()), trim((String) v));
            }
        });
        return props;
    }

    /**
     * get cache properties stream
     *
     * @return
     */
    private static InputStream getConfigStream(String resource) {

        InputStream configStream = ZhCache.class.getResourceAsStream(resource);

        if (configStream == null) {
            configStream = ZhCache.class.getClassLoader().getParent().getResourceAsStream(resource);
        }

        if (configStream == null) {

            log.error("the configuration file is no found,the input path/filename is  {}",resource);
            throw new CacheException("Cannot find " + resource + " !!!");
        }

        return configStream;
    }

    public void dump(PrintStream writer) {
        writer.printf("cache.serialization = %s%n", this.serialization);
        writer.printf("[%s]%n", this.broadcast);
        broadcastProperties.list(writer);
        writer.printf("[%s]%n", this.l1CacheName);
        l1CacheProperties.list(writer);
        writer.printf("[%s]%n", this.l2CacheName);
        l2CacheProperties.list(writer);
    }

    public Properties getProperties() {
        return properties;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    public boolean isSyncTtlToRedis() {
        return syncTtlToRedis;
    }

    public void setSyncTtlToRedis(boolean syncTtlToRedis) {
        this.syncTtlToRedis = syncTtlToRedis;
    }

    public boolean isDefaultCacheNullObject() {
        return defaultCacheNullObject;
    }

    public void setDefaultCacheNullObject(boolean defaultCacheNullObject) {
        this.defaultCacheNullObject = defaultCacheNullObject;
    }

    public String getL1CacheName() {
        return l1CacheName;
    }

    public void setL1CacheName(String provider1) {
        this.l1CacheName = provider1;
    }

    public String getL2CacheName() {
        return l2CacheName;
    }

    public void setL2CacheName(String provider2) {
        this.l2CacheName = provider2;
    }

    public String getSerialization() {
        return serialization;
    }

    public void setSerialization(String serialization) {
        this.serialization = serialization;
    }

    public Properties getBroadcastProperties() {
        return broadcastProperties;
    }

    public void setBroadcastProperties(Properties broadcastProperties) {
        this.broadcastProperties = broadcastProperties;
    }

    public Properties getL1CacheProperties() {
        return l1CacheProperties;
    }

    public void setL1CacheProperties(Properties l1CacheProperties) {
        this.l1CacheProperties = l1CacheProperties;
    }

    public Properties getL2CacheProperties() {
        return l2CacheProperties;
    }

    public void setL2CacheProperties(Properties l2CacheProperties) {
        this.l2CacheProperties = l2CacheProperties;
    }

    private static String trim(String str) {
        return (str != null) ? str.trim() : null;
    }
}
