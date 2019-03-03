package com.cache.log.slf4j;

import com.cache.log.Log;
import com.cache.log.LogFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.spi.LocationAwareLogger;

/**
 * @author zhanghanlin
 * slf4j的log实现类(slf4j > 1.6)
 */
public class Slf4jLocationAwareLogImpl implements Log {

    //标志
    private final Marker MARkER = MarkerFactory.getMarker(LogFactory.MARKER);

    private static final String FQCN = Slf4jAdapter.class.getName();


    private LocationAwareLogger logger;

    public Slf4jLocationAwareLogImpl(LocationAwareLogger logger) {
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
        log(s, e, LocationAwareLogger.ERROR_INT);
    }

    @Override
    public void error(String s) {
        error(s, null);
    }

    @Override
    public void warn(String s) {
        log(s, null, LocationAwareLogger.WARN_INT);
    }

    @Override
    public void debug(String s) {
        log(s, null, LocationAwareLogger.DEBUG_INT);
    }

    @Override
    public void trace(String s) {
        log(s, null, LocationAwareLogger.TRACE_INT);
    }

    /**
     * 抽象出来的函数
     * @param s
     * @param e
     * @param level
     */
    private void log(String s, Throwable e, int level) {
        logger.log(MARkER, FQCN, level, s, null, e);
    }
}
