package stu.memcached;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import org.hamcrest.Matchers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MemcacheOperationTest {

    MemCachedClient client;

    SockIOPool pool;

    @Before
    public void init() {

        client = new MemCachedClient();

        //服务器地址
        String[] servers = {"127.0.0.1:11211"};
        //服务器权重
        Integer[] weights = {3};

        pool = SockIOPool.getInstance();
        pool.setServers(servers);
        pool.setWeights(weights);
        pool.initialize();
    }

    @Test
    public void memOperationConnectTest() {

        boolean isSuccess = client.set("name", "name");
        assertTrue(isSuccess);

        String name = (String)client.get("name");
        assertThat("con",name,Matchers.equalToIgnoringCase("name"));
    }

}
