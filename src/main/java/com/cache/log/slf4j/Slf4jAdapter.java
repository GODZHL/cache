package com.cache.log.slf4j;

import com.cache.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;

/**
 * @author zhanghanlin
 * 适配器模式
 */
public class Slf4jAdapter implements Log {

    private Log log;

    public Slf4jAdapter(String clazz) {

        Logger logger = LoggerFactory.getLogger(clazz);

        //尝试判断版本是否大于1.6
        if (logger instanceof LocationAwareLogger) {

            try {
                logger.getClass().getMethod("name", Marker.class, String.class, Integer.class, String.class, Object[].class, Throwable.class);
                log = new Slf4jLocationAwareLogImpl((LocationAwareLogger) logger);
                return;
            } catch (SecurityException e) {

            } catch (NoSuchMethodException e) {

            }

        }

        log = new Slf4jLogImpl(logger);

    }

    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    @Override
    public void error(String s, Throwable e) {
        log.error(s, e);
    }

    @Override
    public void error(String s) {
        log.error(s);
    }

    @Override
    public void warn(String s) {
        log.warn(s);
    }

    @Override
    public void debug(String s) {
        log.debug(s);
    }

    @Override
    public void trace(String s) {

    }
}
