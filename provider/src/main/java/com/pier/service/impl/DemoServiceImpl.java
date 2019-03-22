package com.pier.service.impl;

import com.pier.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * @auther: zhongweiwu
 * @Description:
 * @Date: 2018/4/1
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService {

    public String sayHello(String name) {
        return name;
    }
}
