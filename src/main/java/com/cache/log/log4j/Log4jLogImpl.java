package com.cache.log.log4j;

import com.cache.log.Log;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author zhanghanlin
 * log4j的Log实现类
 */
public class Log4jLogImpl implements Log {

    private static final String FQUN = Log4jLogImpl.class.getName();

    private Logger logger;


    public Log4jLogImpl(String clazz) {
        this.logger = Logger.getLogger(clazz);
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
