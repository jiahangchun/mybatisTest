package com.util;

import com.dom.DomTest;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author jiahangchun
 */
public class CommonUtil {

    private CommonUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断对象是否为空
     *
     * @param obj 对象
     * @return {@code true}: 为空<br>{@code false}: 不为空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String && obj.toString().length() == 0) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 判断对象是否非空
     *
     * @param obj 对象
     * @return {@code true}: 非空<br>{@code false}: 空
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }


    /**
     * 获取资源文件编译后的真实位置
     *
     * @param pathName
     * @return
     * @throws IOException
     */
    public static String getResourcePath(String pathName) throws Exception {
        Enumeration<URL> keyPath = DomTest.getClassLoader().getResources(pathName);
        while (keyPath.hasMoreElements()) {
            URL url = keyPath.nextElement();
            return url.getPath();
        }
        throw new Exception("can not found resource");
    }
}
