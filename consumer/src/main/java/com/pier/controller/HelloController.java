package com.pier.controller;

import com.alibaba.dubbo.remoting.exchange.ResponseCallback;
import com.alibaba.dubbo.remoting.exchange.ResponseFuture;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.protocol.dubbo.FutureAdapter;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.pier.bean.Person;
import com.pier.service.HelloService;
import com.pier.service.INotifyService;
import com.pier.service.async.AsyncService;
import com.pier.service.callback.CallbackListener;
import com.pier.service.callback.CallbackService;
import com.pier.service.impl.NotifyImpl;
import com.pier.service.notify.Notify;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @auther: zhongweiwu
 * @Description:
 * @Date: 2018/4/1
 */
@Controller
public class HelloController {

    private Logger log = Logger.getLogger(this.getClass());

    /**
     * 注入远程接口
     * 一定要用Resource 不然找不到
     */
    @Resource
    private HelloService helloService;

    /**
     * 调用远程接口，返回index.jsp页面
     * @param
     * @return
     */
    @RequestMapping("/hello/say")
    @ResponseBody
    public String hello(){
        String str = helloService.sayHello("ant");
        return str;
    }

    @Resource
    private GenericService demoService;

    /**
     * 下面使用的是泛化调用，消费者端没有api接口
     * 而泛化实现则是服务端没有api接口
     */
    @RequestMapping("generic/say")
    @ResponseBody
    public String generic(){
        Object result = demoService.$invoke("sayHello", new String[] { "java.lang.String" },
                new String[]{ "Hi, I am a generic invoker" });
        return (String)result;
    }

    /**
     * 回声测试
     */
    @RequestMapping("/echo/hello")
    @ResponseBody
    public String echoTest(){
        EchoService echoService = (EchoService) helloService;
        return (String)echoService.$echo("OK");
    }

    /**
     * 参数回调
     */
    @RequestMapping("/callback")
    @ResponseBody
    public String callBack(){
        callback();
        return "finish callback";
    }

    /**
     * 异步回调通知
     */
    @RequestMapping("/notify")
    @ResponseBody
    public String normalNotify() throws InterruptedException {
        notify1();
        return "finish notify";
    }

    @Resource
    private INotifyService notifyService;
    @Resource
    private NotifyImpl notify;

    public void notify1() throws InterruptedException {
        int age = 24;
        Person ret = notifyService.get(age);
        //for Test：只是用来说明callback正常被调用，业务具体实现自行决定.
        for (int i = 0; i < 10; i++) {
            if (!notify.ret.containsKey(age)) {
                Thread.sleep(200);
            } else {
                break;
            }
        }
    }

    @Resource
    private CallbackService callbackService;

    public void callback(){
        callbackService.addListener("foo.bar", new CallbackListener(){
            @Override
            public void changed(String msg) {
                log.info("callback1:" + msg);
            }
        });
    }

    public void testContext(){
        // 远程调用
        helloService.sayHello("ant");
        // 本端是否为消费端，这里会返回true
        boolean isConsumerSide = RpcContext.getContext().isConsumerSide();
        // 获取最后一次调用的提供方IP地址
        String serverIP = RpcContext.getContext().getRemoteHost();
        // 获取当前服务配置信息，所有配置信息都将转换为URL的参数
        String application = RpcContext.getContext().getUrl().getParameter("application");
        // 注意：每发起RPC调用，上下文状态会变化
        helloService.sayHello("ant");
    }

    /*@Resource
    private AsyncService asyncService;*/

    /*public String asyncCall1(){
        // 调用直接返回CompletableFuture
        CompletableFuture<String> future = asyncService.asyncSayHello("async call request1111");
        // 增加回调
        future.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("Response: " + v);
            }
        });
        // 早于结果输出
        return "Executed before response return.";
    }

    public String asyncCall2(){

        asyncService.sayHello("async call request2222");
        // 调用直接返回CompletableFuture
        ResponseFuture responseFuture = ((FutureAdapter)RpcContext.getContext().getFuture()).getFuture();
        responseFuture.setCallback(new ResponseCallback() {
            @Override
            public void done(Object response) {
                System.out.println("done");
            }

            @Override
            public void caught(Throwable exception) {
                System.out.println("caught");
            }
        });
        // 早于结果输出
        return "Executed before response return.";
    }*/

    /**
     * 异步调用远程服务
     */
    /*@RequestMapping("/async/{id}")
    @ResponseBody
    public String asyncInvoke(@PathVariable("id") String id){
        if (id.equals("1")){
            return asyncCall1();
        } else if (id.equals("2")){
            return asyncCall2();
        }
        return "id error";
    }*/
}
