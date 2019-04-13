package cn.stu.cache;

import cn.stu.cache.util.FastjsonSerializer;
import cn.stu.cache.util.FstJSONSerializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;


import static org.junit.Assert.*;

/**
 * 测试 json 序列化
 */
public class JSONSerializerTest {

    Properties mapping;
    Person person ;

    @Before
    public void setUp() {
        person = new Person();
        person.setName("Winter Lau");
        person.setAge(19);
        person.setSchoolList(Arrays.asList(new School("西北工业大学"), new School("泉州第五中学"), new School("城东中学"), new School("洛南小学")));
        person.setJobs(new HashMap<String, Integer>(){{
            put("creawor", 3);
            put("moabc", 5);
            put("huateng", 3);
            put("stu", 8);
        }});

        mapping = new Properties();
        mapping.setProperty("map.person", "Person");
        mapping.setProperty("map.school", "School");
        mapping.setProperty("map.list", "java.util.Arrays$ArrayList");
    }

    @After
    public void tearDown() {
        person = null;
        mapping = null;
    }

    @Test
    @Ignore
    public void fst_json() {

        FstJSONSerializer serializer = new FstJSONSerializer(mapping);

        byte[] bytes = serializer.serialize(person);

        System.out.println(new String(bytes));


        Person p = (Person)serializer.deserialize(bytes);

        assertEquals(person.getName(), p.getName());
        assertEquals(person.getAge(), p.getAge());

        System.out.println(p);

    }


    @Test
    @Ignore
    public void fast_json() {

        FastjsonSerializer serializer = new FastjsonSerializer();

        byte[] bytes = serializer.serialize(person);

        System.out.println(new String(bytes));


        Person p = (Person)serializer.deserialize(bytes);

        assertEquals(person.getName(), p.getName());
        assertEquals(person.getAge(), p.getAge());

        System.out.println(p);
    }
}
