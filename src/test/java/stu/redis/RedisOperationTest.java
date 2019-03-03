package stu.redis;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class RedisOperationTest {

    private Jedis jedis;

    @Before
    public void init() {
        jedis = new Jedis("localhost");
    }

    @Test
    public void redisConnnectTest() {
        assertThat("string", jedis.ping(), Matchers.equalToIgnoringCase("PONG"));
    }

    @Test
    public void redisStringOperationTest() {
        jedis.set("name", "zhanghanlin");
        assertThat("string", jedis.get("name"), Matchers.equalToIgnoringCase("zhanghanlin"));
    }

    @Test
    public void redisListOperationTest() {

        Long lpush = jedis.lpush("name", "zhanghanlin", "liuchenglong");
        assertThat(lpush, Matchers.is(2L));

        List<String> name = jedis.lrange("name", 0, 2);
        assertThat("list", name, Matchers.hasItems("zhanghanlin", "liuchenglong"));
    }

    @Test
    public void redisKeyOperationTest() {
        jedis.set("name", "zhanghanlin");
        Set<String> keys = jedis.keys("*");
        assertThat("key", keys, Matchers.hasItem("name"));
    }

    @Test
    public void redisSetOperationTest() {
        jedis.sadd("name", "zhanghanlin", "liuchenglong");
        Set<String> name = jedis.smembers("name");
        assertThat("set", name, Matchers.hasItems("zhanghanlin", "liuchenglong"));
    }

    @Test
    public void redisZsetOperationTest() {
        jedis.zadd("name", 1, "zhanghanlin");
        jedis.zadd("name", 2, "liuchenglong");
        Set<String> name = jedis.zrange("name", 0, 1);
        assertThat("zset", name, Matchers.hasItems("zhanghanlin", "liuchenglong"));
    }

    @Test
    public void redisHashOperationTest() {
        jedis.hmset("name", new HashMap<String, String>() {
            {
                put("name", "zhanghanlin");
                put("age", "24");
            }
        });

        List<String> hmget = jedis.hmget("name", "name");
        assertThat("hash", hmget, Matchers.hasItem("zhanghanlin"));
    }

    @Test
    public void redisHyperLogLogOperationTest() {

        jedis.pfadd("name", "1", "2");
        long name = jedis.pfcount("name");
        assertThat("hyperLogLog", 2, Matchers.is(2));

    }


    @After
    public void destory() {
        jedis.flushAll();
    }
}
