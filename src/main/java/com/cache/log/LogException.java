package com.cache.log;

import com.cache.exception.CacheException;

/**
 * @author zhanghanlin
 * 日志的异常
 */
public class LogException extends CacheException {
    public LogException() {
    }

    public LogException(String message) {
        super(message);
    }

    public LogException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogException(Throwable cause) {
        super(cause);
    }

    public LogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
