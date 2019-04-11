package cn.stu.cache.redis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.JedisPoolConfig;
import sun.security.pkcs11.P11Util;

import java.util.Properties;

import static org.junit.Assert.*;

/**
 * create by 19-4-11
 *
 * @author zhanghanlin
 **/
public class RedisUtilsTest {

    private final Properties properties = new Properties();

    @Before
    public void init(){


    }

    @Test
    public void newPoolConfig(){

        JedisPoolConfig zhConfig = RedisUtils.newPoolConfig(properties, "zh");

        Assert.assertNotNull(zhConfig);


    }


}