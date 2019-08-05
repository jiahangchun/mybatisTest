package com.util;

import com.alipay.sofa.rpc.common.RpcConfigs;
import com.alipay.sofa.rpc.common.json.JSON;
import com.alipay.sofa.rpc.common.utils.ClassLoaderUtils;
import com.alipay.sofa.rpc.common.utils.FileUtils;
import com.alipay.sofa.rpc.common.utils.JSONUtils;
import com.alipay.sofa.rpc.core.exception.SofaRpcRuntimeException;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiahangchun
 * 加载资源文件
 */
@Log4j2
public class ResourceUtil {

    private ResourceUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 全局配置
     */
    private static Map<String, Object> CFG = new ConcurrentHashMap<String, Object>();

    /*************************************根据当前文件位置，加载相应文件（sofa）**********************************************/
    /**
     * 获取json配置文件 & 系统参数配置
     * 一般使用static代码块进行处理.
     *
     * @return
     */
    public static Map<String, Object> mergeArguments() {

        try {
            // loadDefault
            String json = FileUtils.file2String(RpcConfigs.class, "rpc-config-default.json", "UTF-8");
            Map map = JSON.parseObject(json, Map.class);
            CFG.putAll(map);

            // loadCustom
//            sofa 还处理了本地问价加载的情况，具体处理方式和getResourcePath类似
            loadCustom("sofa-rpc/rpc-config.json");
            loadCustom("META-INF/sofa-rpc/rpc-config.json");

            // load system properties
            // 注意部分属性可能被覆盖为字符串
            CFG.putAll(new HashMap(System.getProperties()));
        } catch (Exception e) {
            log.error("Catch Exception when load RpcConfigs", e);
        }
        return CFG;
    }

    /**
     * 加载自定义配置文件
     *
     * @param fileName 文件名
     * @throws IOException 加载异常
     */
    private static void loadCustom(String fileName) throws IOException {
        ClassLoader classLoader = ClassLoaderUtils.getClassLoader(ResourceUtil.class);
        Enumeration<URL> urls = classLoader != null ? classLoader.getResources(fileName)
                : ClassLoader.getSystemResources(fileName);
        // 可能存在多个文件
        if (urls != null) {
            while (urls.hasMoreElements()) {
                // 读取每一个文件
                URL url = urls.nextElement();
                InputStreamReader input = null;
                BufferedReader reader = null;
                try {
                    input = new InputStreamReader(url.openStream(), "utf-8");
                    reader = new BufferedReader(input);
                    StringBuilder context = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        context.append(line).append("\n");
                    }
                    Map map = JSON.parseObject(context.toString(), Map.class);
                    System.out.println(JSONUtils.toJSONString(map));
                } finally {
                    if (reader != null) {
                        reader.close();
                    }
                    if (input != null) {
                        input.close();
                    }
                }
            }
        }
    }

    /**
     * Gets int value.
     *
     * @param primaryKey the primary key
     * @return the int value
     */
    public static int getIntValue(String primaryKey) {
        Object val = CFG.get(primaryKey);
        if (val == null) {
            throw new SofaRpcRuntimeException("Not found key: " + primaryKey);
        } else {
            return Integer.parseInt(val.toString());
        }
    }

    /*************************************测试**************************************************************/

    public static void main(String[] args) throws Exception {
        String json = FileUtils.file2String(RpcConfigs.class, "rpc-config-default.json", "UTF-8");
    }

}
