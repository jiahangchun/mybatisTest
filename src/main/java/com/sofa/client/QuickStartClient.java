package com.sofa.client;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.sofa.server.HelloService;

/**
 * Quick Start client
 * @author jiahangchun
 */
public class QuickStartClient {
    public static void main(String[] args) {
        ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
                // 指定接口
                .setInterfaceId(HelloService.class.getName())
                // 指定协议
                .setProtocol("bolt")
                // 指定直连地址
                .setDirectUrl("bolt://127.0.0.1:12200");
        // 生成代理类
        HelloService helloService = consumerConfig.refer();
        while (true) {
            System.out.println(helloService.sayHello("world"));
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
    }
}