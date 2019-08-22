package com.util.log;


import com.util.CommonUtil;

/**
 * @author jiahangchun
 */
public class LoggerFactory {
    /**
     * 配置的实现类 动态设置
     * 方便之后更换log实现类
     */
    private static String implClass = "com.alipay.sofa.rpc.log.SLF4JLoggerImpl";

    public static Logger getLogger(String name) {
        try {
            return (Logger) CommonUtil.forName(implClass).getConstructor(String.class).newInstance(name);
        } catch (Exception e) {
            throw new RuntimeException("Error when getLogger of " + name
                    + ", implement is " + implClass + "", e);
        }
    }

    public static Logger getLogger(Class clazz) {
        try {
            return (Logger) CommonUtil.forName(implClass).getConstructor(Class.class).newInstance(clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error when getLogger of " + clazz.getName()
                    + ", implement is " + implClass + "", e);
        }
    }
}
