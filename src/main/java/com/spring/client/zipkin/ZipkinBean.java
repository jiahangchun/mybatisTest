package com.spring.client.zipkin;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

/**
 * @author jiahangchun
 */
@Slf4j
@Controller
public class ZipkinBean {

    @Bean
    public AlwaysSampler alwaysSampler() {
        return new AlwaysSampler();
    }

}
