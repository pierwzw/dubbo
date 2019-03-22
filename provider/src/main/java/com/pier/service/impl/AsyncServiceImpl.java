package com.pier.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.pier.service.HelloService;
import com.pier.service.async.AsyncService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @auther: zhongweiwu
 * @Description:
 * @Date: 2018/4/1
 */
@Service("asyncService")
public class AsyncServiceImpl implements AsyncService {

    @Override
    public String sayHello(String name) {
        return name;
    }

    @Override
    public CompletableFuture<String> asyncSayHello(String name) {
        //return CompletableFuture.supplyAsync(() -> name);
        return null;
    }
}
