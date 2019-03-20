package com.test;

import com.pier.api.DemoService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @auther: zhongweiwu
 * @Description:
 * @Date: 2018/4/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc.xml"})
public class Test {

    @Resource
    private DemoService demoService;

    @org.junit.Test
    public void testConsumer(){
        String str = demoService.sayHello("哈哈哈");
        System.out.println(str);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
