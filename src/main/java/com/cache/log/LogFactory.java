package com.cache.log;

import java.lang.reflect.Constructor;

/**
 * @author zhanghanlin
 * 通过工厂模式去适配已经有实现的日志包
 */
public class LogFactory {

    //cache系统标识
    public static final String MARKER = "CACHE";

    //记录当前使用的第三方日志组件所对应的适配器的构造方法
    private static Constructor<? extends Log> logConstructor;

    private LogFactory() {

    }

    static {
        tryLoadImplClass(LogFactory::useSlf4j);
        tryLoadImplClass(LogFactory::useLog4j);
        tryLoadImplClass(LogFactory::useNolog);
    }

    /**
     * 获取Log
     *
     * @param clazz
     * @return
     */
    public static Log getLog(Class<?> clazz) {
        return getLog(clazz.getName());
    }

    public static Log getLog(String clazzName) {

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
        setImplClass(com.cache.log.log4j.Log4jLogImpl.class);
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
                throw new LogException("LogFactory loadImplClass　发生错误");
            }
        }
    }

    /**
     * 设置日志的实现类
     */
    private static void setImplClass(Class<? extends Log> clazz) {

        try {
            Constructor<? extends Log> constructor = clazz.getConstructor(String.class);

            Log log = constructor.newInstance(LogFactory.class.getName());

            if (log.isDebugEnabled()) {
                log.debug("日志适配器初始化成功");
            }

            logConstructor = constructor;
        } catch (Throwable e) {

            throw new LogException("LogFactory初始化失败");
        }

    }

}
