package com.cache.log;

import com.cache.log.slf4j.Slf4jAdapter;
import org.junit.Test;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author zhanghanlin
 * LogFactory测试
 */
public class LogFactoryTest {

    private Log log;

    private void logSomething(Log log) {

        log.debug("debug");
        log.trace("trace");
        log.warn("warn");
        log.error("error");
    }


    @Test
    public void slf4jTest() {
        LogFactory.useSlf4j();
        log = LogFactory.getLog(LogFactoryTest.class);
        assertThat(log, Matchers.instanceOf(com.cache.log.slf4j.Slf4jAdapter.class));
    }

    @Test
    public void log4jTest() {
        LogFactory.useLog4j();
        log = LogFactory.getLog(LogFactoryTest.class);
        assertThat(log, Matchers.instanceOf(com.cache.log.log4j.Log4jLogImpl.class));
    }


    @Test
    public void noLogTest() {
        LogFactory.useNolog();
        log = LogFactory.getLog(LogFactoryTest.class);
        assertThat(log, Matchers.instanceOf(com.cache.log.nolog.Nologimpl.class));
    }


    @Test
    public void logFactoryTest() {
        log = LogFactory.getLog(LogFactoryTest.class);
        assertThat(log, Matchers.instanceOf(Slf4jAdapter.class));
    }

    @After
    public void logtrue() {
        logSomething(log);
    }
}
