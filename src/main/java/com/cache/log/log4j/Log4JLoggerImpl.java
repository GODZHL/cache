package com.cache.log.log4j;

import com.cache.log.Logger;
import org.apache.log4j.Level;

/**
 * @author zhanghanlin
 * log4j的Log实现类
 */
public class Log4JLoggerImpl implements Logger {

    private static final String FQUN = Log4JLoggerImpl.class.getName();

    private org.apache.log4j.Logger logger;


    public Log4JLoggerImpl(String clazz) {
        this.logger = org.apache.log4j.Logger.getLogger(clazz);
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
        logger.log(FQUN, Level.ERROR, s, e);
    }

    @Override
    public void error(String s) {
        logger.log(FQUN, Level.ERROR, s, null);
    }

    @Override
    public void warn(String s) {
        logger.log(FQUN, Level.WARN, s, null);
    }

    @Override
    public void debug(String s) {
        logger.log(FQUN, Level.DEBUG, s, null);
    }

    @Override
    public void trace(String s) {
        logger.log(FQUN, Level.TRACE, s, null);
    }


}
