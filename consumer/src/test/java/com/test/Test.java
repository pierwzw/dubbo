package com.test;

import com.pier.api.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @auther: zhongweiwu
 * @Description:
 * @Date: 2018/4/1
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:springmvc.xml" });

        context.start();
        DemoService demoService = (DemoService) context.getBean("demoService");

        String str = demoService.sayHello("哈哈哈");
        System.out.println(str);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
