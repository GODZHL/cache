package com.cache.log;

/**
 * @author zhanghanlin
 * 日志的统一接口
 */
public interface Log {


    boolean isDebugEnabled();

    boolean isTraceEnabled();


    void error(String s, Throwable e);

    void error(String s);

    void warn(String s);

    void debug(String s);

    void trace(String s);


}
