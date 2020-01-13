package com.swagger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.util.JsonUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
public class SwaggerTest {
    /**
     * swagger url
     */
    private static String SWAGGER_URL = "http://localhost:8080/platform/v2/api-docs";

    private static final String ACCEPT_HEADER_VALUE = "application/json, application/yaml, */*";
    private static final String USER_AGENT_HEADER_VALUE = "Apache-HttpClient/Swagger";
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static void main(String[] args) throws Exception {
        //1.获取数据
        String data = SwaggerTest.data(SWAGGER_URL);
        System.out.println(data);

        //2.
        OpenApi openApi=SwaggerTest.transform2Obj(data);
    }

    /**
     * jackson转换出具体的java对象
     * 缺少：
     * 1/yml结构解析：之后swagger是yml结构？
     * 2/
     * @param data
     */
    public static OpenApi transform2Obj(String data) throws Exception {
        if(CommonUtil.isEmpty(data)){
            throw new Exception("data empty.");
        }
        JSONObject userJson = JSONObject.parseObject(data);
        OpenApi openApi = JSON.toJavaObject(userJson,OpenApi.class);
        log.info("transform2Obj:{}", JSON.toJSONString(openApi));
        return openApi;
    }

    /**
     * 缺少 url校验
     * 通过 url 获取数据
     * 1.文件
     * 2.url
     *
     * @return
     */
    public static String data(String url) {
        String data = "";
        try {
            data = SwaggerTest.urlToString(url);
        } catch (Exception e) {
            log.error(" can not found any result:{}", e.getMessage(), e);
        }
        return data;
    }

    /**
     * 还需要携带认证信息
     * ConnectionConfigurator SSL 认证
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String urlToString(String url) throws Exception {
        InputStream is = null;
        BufferedReader br = null;

        try {
            URLConnection conn;
            do {
                final URL inUrl = new URL(cleanUrl(url));
                conn = inUrl.openConnection();
                conn.setRequestProperty("Accept", ACCEPT_HEADER_VALUE);
                conn.setRequestProperty("User-Agent", USER_AGENT_HEADER_VALUE);
                conn.connect();
                url = ((HttpURLConnection) conn).getHeaderField("Location");
            } while (301 == ((HttpURLConnection) conn).getResponseCode());
            InputStream in = conn.getInputStream();

            StringBuilder contents = new StringBuilder();

            BufferedReader input = new BufferedReader(new InputStreamReader(in, UTF_8));

            for (int i = 0; i != -1; i = input.read()) {
                char c = (char) i;
                if (!Character.isISOControl(c)) {
                    contents.append((char) i);
                }
                if (c == '\n') {
                    contents.append('\n');
                }
            }

            in.close();

            return contents.toString();
        } catch (javax.net.ssl.SSLProtocolException e) {
            log.warn("there is a problem with the target SSL certificate");
            log.warn("**** you may want to run with -Djsse.enableSNIExtension=false\n\n");
            log.error("unable to read", e);
            throw e;
        } catch (Exception e) {
            log.error("unable to read", e);
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
            if (br != null) {
                br.close();
            }
        }
    }

    public static String cleanUrl(String url) {
        String result = null;
        try {
            result = url.replaceAll("\\{", "%7B").
                    replaceAll("\\}", "%7D").
                    replaceAll(" ", "%20");
        } catch (Exception t) {
            t.printStackTrace();
        }
        return result;
    }


}
