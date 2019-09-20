//package com.es;
//
//import com.alipay.sofa.rpc.common.utils.JSONUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.action.bulk.BulkRequestBuilder;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.rest.RestStatus;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import java.net.InetAddress;
//
//@Slf4j
//public class EsTest {
//
//    private static final String ip="";
//    private static final Integer port=9300;
//
//    public static void main(String[] args) throws Exception {
//        Settings settings = Settings.builder().put("cluster.name", "aaaaa").build();
//        TransportClient client = new PreBuiltTransportClient(settings);
//        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), port));
//
//        BulkRequestBuilder prepareBulk = client.prepareBulk();
//        prepareBulk.add(client.prepareIndex(INDEX, TYPE, UUID.randomUUID().toString()).setSource(jsonObject));
//        BulkResponse bulkResponse = prepareBulk.get();
//        RestStatus status = bulkResponse.status();
//        if (status.getStatus() == 200) {
//            log.warn("[add] - batch add log fail, statue: {}, data: {}", status, JSONUtils.toJSONString(bulkResponse));
//        }
//
//    }
//}
