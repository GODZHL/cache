package com.cache.log;

import com.cache.log.log4j.Log4JLoggerImpl;
import com.cache.log.slf4j.Slf4jAdapter;
import org.junit.Test;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;

import org.junit.After;


/**
 * @author zhanghanlin
 * LogFactory测试
 */
public class LoggerFactoryTest {

    private Logger logger;

    private void logSomething(Logger logger) {

        logger.debug("debug");
        logger.trace("trace");
        logger.warn("warn");
        logger.error("error");
    }


    @Test
    public void slf4jTest() {
        LoggerFactory.useSlf4j();
        logger = LoggerFactory.getLogger(LoggerFactoryTest.class);
        assertThat(logger, Matchers.instanceOf(com.cache.log.slf4j.Slf4jAdapter.class));
    }

    @Test
    public void log4jTest() {
        LoggerFactory.useLog4j();
        logger = LoggerFactory.getLogger(LoggerFactoryTest.class);
        assertThat(logger, Matchers.instanceOf(Log4JLoggerImpl.class));
    }


    @Test
    public void noLogTest() {
        LoggerFactory.useNolog();
        logger = LoggerFactory.getLogger(LoggerFactoryTest.class);
        assertThat(logger, Matchers.instanceOf(com.cache.log.nolog.Nologimpl.class));
    }


    @Test
    public void logFactoryTest() {
        logger = LoggerFactory.getLogger(LoggerFactoryTest.class);
        assertThat(logger, Matchers.instanceOf(Slf4jAdapter.class));
    }

    @After
    public void logtrue() {
        logSomething(logger);
    }
}
