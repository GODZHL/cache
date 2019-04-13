package com.stu.springzhcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(scanBasePackages = {"com.stu.springzhcache"})
public class SpringZhcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringZhcacheApplication.class, args);
    }

}
