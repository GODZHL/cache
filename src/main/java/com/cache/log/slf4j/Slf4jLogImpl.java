package com.cache.log.slf4j;

import com.cache.log.Log;
import org.slf4j.Logger;

/**
 * @author zhanghanlin
 * slf4j的log实现类(slf4j < 1.6)
 */
public class Slf4jLogImpl implements Log {

    private final Logger logger;

    public Slf4jLogImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void error(String s, Throwable e) {
        logger.error(s, e);
    }

    @Override
    public void error(String s) {
        logger.error(s);
    }

    @Override
    public void warn(String s) {
        logger.warn(s);
    }

    @Override
    public void debug(String s) {
        logger.debug(s);
    }

    @Override
    public void trace(String s) {
        logger.trace(s);
    }


}
