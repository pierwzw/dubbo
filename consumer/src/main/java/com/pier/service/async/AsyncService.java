package com.pier.service.async;

import java.util.concurrent.CompletableFuture;

/**
 * @auther zhongweiwu
 * @date 2019/3/22 15:56
 */
public interface AsyncService {

    String sayHello(String name);

    CompletableFuture<String> asyncSayHello(String name);
}
