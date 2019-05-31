package com.dom;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author jiahangchun
 */
public class ParseUtil {
    /**
     * @param pathName
     * @return
     * @throws IOException
     */
    static String getResourcePath(String pathName) throws Exception {
        Enumeration<URL> keyPath = DomTest.getClassLoader().getResources(pathName);
        URL url = null;
        while (keyPath.hasMoreElements()) {
            url = keyPath.nextElement();
            return url.getPath();
        }
       throw new Exception("can not found resource");
    }
}
