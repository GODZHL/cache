package com.cache.exception;

/**
 * @author zhanghanlin
 * 主要用于不确定异常类型时提供的异常工厂
 */
public class ExceptionFactory {

    //私有化构造器,防止创建对象
    private ExceptionFactory() {

    }

    public static RuntimeException getWrapException(String message, Throwable e) {
        return new CacheException(message, e);
    }

    public static RuntimeException getWrapException(String message) {

        return new CacheException(message);
    }
}
