package com.cache.log.nolog;

import com.cache.log.Log;

/**
 * @author zhanghanlin
 * 没有导入日志相关的包,全是空实现
 */
public class Nologimpl implements Log {
    public Nologimpl(String className) {
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public void error(String s, Throwable e) {

    }

    @Override
    public void error(String s) {

    }

    @Override
    public void warn(String s) {

    }

    @Override
    public void debug(String s) {

    }

    @Override
    public void trace(String s) {

    }

}
