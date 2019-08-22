package com.spring.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import zipkin.server.EnableZipkinServer;

/**
 * @author jiahangchun
 */
@EnableSwagger2
@EnableZipkinServer
@SpringBootApplication
public class SpringMain implements EmbeddedServletContainerCustomizer {
    public static void main(String[] args) {
        SpringApplication.run(SpringMain.class,args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(9411);
    }
}
