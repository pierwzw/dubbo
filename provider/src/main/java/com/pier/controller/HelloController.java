package com.pier.controller;

import com.pier.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @auther: zhongweiwu
 * @Description:
 * @Date: 2018/4/1
 */
@Controller
@RequestMapping("hello")
public class HelloController {

    /**
     * 注入远程接口
     */
    @Resource
    private HelloService helloService;

    /**
     * 调用远程接口，返回index.jsp页面
     * @param
     * @return
     */
    @RequestMapping("index")
    @ResponseBody
    public String index(){
        String str = helloService.sayHello("ant");
        return str;
    }
}
