package com.spring.client.controller;

import com.spring.client.service.ZipkinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiahangchun
 */
@Slf4j
@RestController
public class ZipkinController {

    @Autowired
    private ZipkinService zipkinService;

    @GetMapping("getMessage")
    public void getMessage(){
        log.info("ssss");
        zipkinService.getInfo();
    }
}
