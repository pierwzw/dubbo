package com.pier.provider;

import com.pier.api.HelloService;
import org.springframework.stereotype.Service;

/**
 * @auther: zhongweiwu
 * @Description:
 * @Date: 2018/4/1
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {

    public String sayHello(String name) {
        return "hello,"+name+"!";
    }

}
