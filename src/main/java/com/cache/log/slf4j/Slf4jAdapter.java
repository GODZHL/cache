package com.cache.log.slf4j;

import com.cache.log.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;

/**
 * @author zhanghanlin
 * 适配器模式
 */
public class Slf4jAdapter implements Logger {

    private Logger logger;

    public Slf4jAdapter(String clazz) {

        org.slf4j.Logger logger = LoggerFactory.getLogger(clazz);

        //尝试判断版本是否大于1.6
        if (logger instanceof LocationAwareLogger) {

            try {
                logger.getClass().getMethod("name", Marker.class, String.class, Integer.class, String.class, Object[].class, Throwable.class);
                this.logger = new Slf4JLocationAwareLoggerImpl((LocationAwareLogger) logger);
                return;
            } catch (SecurityException e) {

            } catch (NoSuchMethodException e) {

            }

        }

        this.logger = new Slf4JLoggerImpl(logger);

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

    }
}
