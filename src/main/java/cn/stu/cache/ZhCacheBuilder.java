package cn.stu.cache;

import cn.stu.cache.cluster.ClusterPolicy;
import cn.stu.cache.cluster.ClusterPolicyFactory;
import cn.stu.cache.util.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 使用自定义配置构建 ZhCache
 *
 * @author zhanghanlin
 */
public class ZhCacheBuilder {

    private final static Logger log = LoggerFactory.getLogger(ZhCacheBuilder.class);

    //持有的通道
    private CacheChannel channel;

    //缓存管理器的持有类
    private CacheProviderHolder holder;

    //不同的广播策略
    private ClusterPolicy policy;

    private AtomicBoolean opened = new AtomicBoolean(false);

    //配置类
    private ZhCacheConfig config;

    private ZhCacheBuilder(ZhCacheConfig config) {
        this.config = config;
    }

    /**
     * 初始化 ZhCache，这是一个很重的操作，请勿重复执行
     *
     * @param config cache config instance
     * @return ZhCacheBuilder instance
     */
    public final static ZhCacheBuilder init(ZhCacheConfig config) {
        return new ZhCacheBuilder(config);
    }

    /**
     * 返回缓存操作接口
     *
     * @return CacheChannel
     */
    public CacheChannel getChannel() {

        if (this.channel == null || !this.opened.get()) {

            synchronized (ZhCacheBuilder.class) {
                if (this.channel == null || !this.opened.get()) {
                    this.initFromConfig(config);
                    /* 初始化缓存接口 */
                    this.channel = new CacheChannel(config, holder) {
                        @Override
                        public void sendClearCmd(String region) {
                            policy.sendClearCmd(region);
                        }

                        @Override
                        public void sendEvictCmd(String region, String... keys) {
                            policy.sendEvictCmd(region, keys);
                        }

                        @Override
                        public void close() {
                            super.close();
                            policy.disconnect();
                            holder.shutdown();
                            opened.set(false);
                        }
                    };
                    this.opened.set(true);
                }
            }
        }

        return this.channel;
    }

    /**
     * 关闭 ZhCache
     */
    public void close() {
        this.channel.close();
        this.channel = null;
    }

    /**
     * 加载配置
     *
     * @return
     * @throws IOException
     */
    private void initFromConfig(ZhCacheConfig config) {

        SerializationUtils.init(config.getSerialization(), config.getSubProperties(config.getSerialization()));

        //初始化两级的缓存管理
        this.holder = CacheProviderHolder.init(config, (region, key) -> {
            //当一级缓存中的对象失效时，自动清除二级缓存中的数据
            Level2Cache level2 = this.holder.getLevel2Cache(region);

            level2.evict(key);

            if (!level2.supportTTL()) {
                //再一次清除一级缓存是为了避免缓存失效时再次从 L2 获取到值
                this.holder.getLevel1Cache(region).evict(key);
            }

            log.debug("Level 1 cache object expired, evict level 2 cache object [{},{}]", region, key);

            if (policy != null) {
                policy.sendEvictCmd(region, key);
            }
        });

        policy = ClusterPolicyFactory.init(holder, config.getBroadcast(), config.getBroadcastProperties());

        log.info("Using cluster policy : {}", policy.getClass().getName());
    }

}
