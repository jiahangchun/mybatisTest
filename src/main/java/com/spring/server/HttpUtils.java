package com.spring.server;

import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author jiahangchun
 */
@Slf4j
public class HttpUtils {

    /**
     * 读写文件
     * @param msg
     */
    public static void genReadChannel(Object msg) {
        try {
            if (msg instanceof HttpContent) {
                HttpContent httpContent = (HttpContent) msg;
                ByteBuf byteBuf = httpContent.content();
                int size = byteBuf.readableBytes();
                byte[] byteArray = new byte[size];
                byteBuf.readBytes(byteArray);
                String result = new String(byteArray);
                System.out.println(result);
            } else if (msg instanceof DefaultHttpResponse) {
                DefaultHttpResponse response = (DefaultHttpResponse) msg;
                System.out.println(response.toString());
            } else if (msg instanceof ByteBuf) {
                ByteBuf bf = (ByteBuf) msg;
                byte[] byteArray = new byte[bf.capacity()];
                bf.readBytes(byteArray);
                String result = new String(byteArray);
                System.out.println(result);
            } else {
                System.out.println(" can not found type.");
            }
        } catch (Exception e) {
            log.error(" read error :{}", e.getMessage(), e);
        }
    }

    /**
     * 测试环境
     * @return
     * @throws Exception
     */
    public static HttpRequestInfo genRequest() throws Exception {
        String method = "GET";
        String url = "http://erp.test.jimuitech.com/platform/client/eggSearch/order/detail/927145";
        Map<String, String> heads = Maps.newHashMap();
        heads.put("token", "%2BSOnbqqAr2%2FBVnhJZbKe1SaZx367f%2Benq%2FOJbnzZc3%2BObzj%2F7lRWIVP7XKPIoCyW5vEoQsMYH9mn4qi1u1r7sWJ1wSoJn%2BNGMPDyS%2FRvGEugIdHQ4plaYBkf3%2FaA2nYcpD%2Bfyqn5blBzskZObHmfFQ%3D%3D");

        String body = "";
        HttpRequestInfo request = RangeDownload.buildRequest(method, url, heads, body);
        return request;
    }

    public static HttpRequestInfo genLocalSpringRequest() throws Exception {
        String method = "GET";
        String url = "http://127.0.0.1:8080/platform/client/eggSearch/order/detail/927145";
        Map<String, String> heads = Maps.newHashMap();
        heads.put("token", "23vYj%2BFBj5cmeseSE4r1sjR8XCf%2BNevPh5Sh0xzRujiH3yYuJmIJnUs%2FKqimPUV2ASSdt2Vv%2B5sPbyLo2b5IJGujHaXdPb%2BHvbYfuWycDSNCGKhMCznthqSxQCpaQx66oZ2ddv%2Bi2GBVgUgsfI7EjQ%3D%3D");

        String body = "";
        HttpRequestInfo request = RangeDownload.buildRequest(method, url, heads, body);
        return request;
    }

    /**
     * 本地
     * @return
     * @throws Exception
     */
    public static HttpRequestInfo genLocalRequest() throws Exception {
        String method = "GET";
        String url = "http://127.0.0.1:9099";
        Map<String, String> heads = Maps.newHashMap();
        String body = "";
        HttpRequestInfo request = RangeDownload.buildRequest(method, url, heads, body);
        return request;
    }
}
