package com.sofa.server;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;

/**
 * @author jiahangchun
 */
public class QuickStartServer {

    public static void main(String[] args) {
        ServerConfig serverConfig = new ServerConfig()
                // 设置一个协议，默认bolt
                .setProtocol("bolt")
                // 设置一个端口，默认12200
                .setPort(12200)
                // 非守护线程
                .setDaemon(false);

        ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
                // 指定接口
                .setInterfaceId(HelloService.class.getName())
                // 指定实现
                .setRef(new HelloServiceImpl())
                // 指定服务端
                .setServer(serverConfig);
        // 发布服务
        providerConfig.export();
    }
}