package com.spring.client.service.impl;

import com.spring.client.service.ZipkinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author jiahangchun
 */
@Slf4j
@Service
public class ZipkinServiceImpl implements ZipkinService {

    @Override
    public void getInfo() {
        log.info("getInfo()");
    }
}
