package com.cache.lang.local;

import com.cache.exception.CacheException;

/**
 * @author zhanghanlin
 * 本地缓存异常
 */
public class LocalCacheException extends CacheException {
    public LocalCacheException() {
    }

    public LocalCacheException(String message) {
        super(message);
    }

    public LocalCacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocalCacheException(Throwable cause) {
        super(cause);
    }

    public LocalCacheException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
