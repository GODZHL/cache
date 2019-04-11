package stu.string;

import org.junit.Test;

import java.util.StringJoiner;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import org.hamcrest.Matchers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * java 8 中的String的新姿势
 */
public class StringOfJava8 {


    @Test
    public void StringJoinTest(){

        StringJoiner joiner = new StringJoiner("name");
        joiner.add(":zhanghanlin");

        assertThat(joiner.toString(),Matchers.equalToIgnoringCase(":zhanghanlin"));

    }
}
