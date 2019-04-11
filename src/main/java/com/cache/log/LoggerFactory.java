package com.cache.log;

import com.cache.log.log4j.Log4JLoggerImpl;

import java.lang.reflect.Constructor;

/**
 * @author zhanghanlin
 * 通过工厂模式去适配已经有实现的日志包
 */
public class LoggerFactory {

    //cache系统标识
    public static final String MARKER = "CACHE";

    //记录当前使用的第三方日志组件所对应的适配器的构造方法
    private static Constructor<? extends Logger> logConstructor;

    private LoggerFactory() {

    }

    static {
        tryLoadImplClass(LoggerFactory::useSlf4j);
        tryLoadImplClass(LoggerFactory::useLog4j);
        tryLoadImplClass(LoggerFactory::useNolog);
    }

    /**
     * 获取Log
     *
     * @param clazz
     * @return
     */
    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    public static Logger getLogger(String clazzName) {

        try {

            if (logConstructor == null) {
                throw new LogException("logfactory中logConstructor不能为空");
            }

            return logConstructor.newInstance(clazzName);

        } catch (Throwable e) {

            throw new LogException("无法加载需要加载的类", e);
        }

    }

    public synchronized static void useSlf4j() {
        setImplClass(com.cache.log.slf4j.Slf4jAdapter.class);
    }

    public synchronized static void useLog4j() {
        setImplClass(Log4JLoggerImpl.class);
    }

    public synchronized static void useNolog() {
        setImplClass(com.cache.log.nolog.Nologimpl.class);
    }

    /**
     * 加载实现类
     */
    private static void tryLoadImplClass(Runnable runnable) {

        if (logConstructor == null) {
            try {
                runnable.run();

            } catch (Throwable e) {
                throw new LogException("LoggerFactory loadImplClass　发生错误");
            }
        }
    }

    /**
     * 设置日志的实现类
     */
    private static void setImplClass(Class<? extends Logger> clazz) {

        try {
            Constructor<? extends Logger> constructor = clazz.getConstructor(String.class);

            Logger logger = constructor.newInstance(LoggerFactory.class.getName());

            if (logger.isDebugEnabled()) {
                logger.debug("日志适配器初始化成功");
            }

            logConstructor = constructor;
        } catch (Throwable e) {

            throw new LogException("LogFactory初始化失败");
        }

    }

}
