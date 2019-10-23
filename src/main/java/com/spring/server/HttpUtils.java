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
            }else if(msg instanceof String){
                System.out.println(msg);
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
        String url = "http://localhost:8080/platform/tms/stockInLoading/list";
        Map<String, String> heads = Maps.newHashMap();
        heads.put("token", "9CpHnCvkUx3tlzwFSpg%2B%2BZGLPqOl6P%2Bqzr4WhLW57X4uu52vFQvi1edIsVR%2BALF0%2Bb%2BVNwRyQdqocpdxPW42M5fpWVaBUalIEMYGWPR7hzrW4wf0ouQUBS%2F6MIFr2t5agC4iiBie3fdPyuUAyTwzzw%3D%3D");

        String body = "";
        HttpRequestInfo request = RangeDownload.buildRequest(method, url, heads, body);
        return request;
    }

    /**
     * 上面的HttpRequestInfo好像不太对，说是header存在问题
     * @return
     */
    public static String genHttpRequestStr(){
        String str="{\"version\":\"HTTP_1_1\",\"method\":\"GET\",\"uri\":\"/platform/tms/stockInLoading/list\",\"headers\":{\"Host\":\"localhost\",\"Connection\":\"keep-alive\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.75 Safari/537.36\",\"Accept\":\"application/json,text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\",\"Referer\":\"localhost\",\"token\":\"9CpHnCvkUx3tlzwFSpg%2B%2BZGLPqOl6P%2Bqzr4WhLW57X4uu52vFQvi1edIsVR%2BALF0%2Bb%2BVNwRyQdqocpdxPW42M5fpWVaBUalIEMYGWPR7hzrW4wf0ouQUBS%2F6MIFr2t5agC4iiBie3fdPyuUAyTwzzw%3D%3D\"},\"content\":null}\n";

//        String str="{\"requestProto\":{\"host\":\"localhost\",\"port\":8080,\"ssl\":false},\"version\":\"HTTP_1_1\",\"method\":\"GET\",\"uri\":\"/platform/tms/stockInLoading/list\",\"headers\":{\"Host\":\"localhost\",\"Connection\":\"keep-alive\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.75 Safari/537.36\",\"Accept\":\"application/json,text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\",\"Referer\":\"localhost\",\"token\":\"9CpHnCvkUx3tlzwFSpg%2B%2BZGLPqOl6P%2Bqzr4WhLW57X4uu52vFQvi1edIsVR%2BALF0%2Bb%2BVNwRyQdqocpdxPW42M5fpWVaBUalIEMYGWPR7hzrW4wf0ouQUBS%2F6MIFr2t5agC4iiBie3fdPyuUAyTwzzw%3D%3D\"},\"content\":null}\n";
        return str;
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
