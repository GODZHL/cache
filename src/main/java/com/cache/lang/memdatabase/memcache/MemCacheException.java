package com.cache.lang.memdatabase.memcache;

import com.cache.lang.memdatabase.MemException;

/**
 * Memcache异常
 */
public class MemCacheException extends MemException {
    public MemCacheException() {
    }

    public MemCacheException(String message) {
        super(message);
    }

    public MemCacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemCacheException(Throwable cause) {
        super(cause);
    }

    public MemCacheException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
