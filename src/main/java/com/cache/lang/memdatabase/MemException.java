package com.cache.lang.memdatabase;

import com.cache.exception.CacheException;

/**
 * 内存数据库缓存异常
 */
public class MemException extends CacheException {
    public MemException() {
    }

    public MemException(String message) {
        super(message);
    }

    public MemException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemException(Throwable cause) {
        super(cause);
    }

    public MemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
