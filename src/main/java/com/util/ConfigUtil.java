package com.util;

import org.yaml.snakeyaml.Yaml;

import java.util.Map;

public class ConfigUtil {
    /**
     * 获取配置文件中相关参数
     *
     */
    private static Map<String, Object> map = null;

    static {
        Yaml yaml = new Yaml();
        map = (Map<String, Object>) yaml.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.yml"));
        String active = getString("spring.profiles.active");
        merge(map, (Map<String, Object>) yaml.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application-" + active + ".yml")));
    }

    public static String getString(String key) {
        return getString(map, key);
    }

    private static String getString(Map<String, Object> map, String key) {
        return String.valueOf(get(map, key));
    }

    static Object get(Map<String, Object> map, String key) {
        String[] keyArray = key.split("\\.");
        if (keyArray.length == 1) {
            return map.get(key);
        } else {
            for (int i = 0; i < keyArray.length - 1; i++) {
                map = (Map<String, Object>) get(map, keyArray[i]);
            }
            return map.get(keyArray[keyArray.length - 1]);
        }
    }

    private static void merge(Map<String, Object> map1, Map<String, Object> map2) {
        for (Map.Entry<String, Object> entry : map2.entrySet()) {
            if (map1.containsKey(entry.getKey())) {
                if (entry.getValue() instanceof Map && map2.get(entry.getKey()) instanceof Map) {
                    merge(map1, (Map<String, Object>) map2.get(entry.getKey()));
                } else {
                    map1.put(entry.getKey(), map2.get(entry.getKey()));
                }
            } else {
                map1.put(entry.getKey(), map2.get(entry.getKey()));
            }
        }
    }


}
