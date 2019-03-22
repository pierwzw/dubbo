package com.pier.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.pier.service.DemoService;
import com.pier.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @auther: zhongweiwu
 * @Description:
 * @Date: 2018/4/1
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {

    public String sayHello(String name) {
        return name;
    }

    public void textContext(){
        // 本端是否为提供端，这里会返回true
        boolean isProviderSide = RpcContext.getContext().isProviderSide();
        // 获取调用方IP地址
        String clientIP = RpcContext.getContext().getRemoteHost();
        // 获取当前服务配置信息，所有配置信息都将转换为URL的参数
        String application = RpcContext.getContext().getUrl().getParameter("application");
        // 注意：每发起RPC调用，上下文状态会变化
        //yyyService.yyy();
        // 此时本端变成消费端，这里会返回false
        isProviderSide = RpcContext.getContext().isProviderSide();
    }
}
