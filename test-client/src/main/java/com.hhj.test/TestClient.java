package com.hhj.test;

import com.hhj.rpc.api.HelloObject;
import com.hhj.rpc.api.HelloService;
import com.hhj.rpc.client.RpcClientProxy;

/**
 * @author yourname <huanghaijin@kuaishou.com>
 * Created on 2023-03-25
 */
public class TestClient {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1", 9000);
        // 获得代理实现类接口，准备调用这个接口中的方法
        HelloService helloService = proxy.getProxy(HelloService.class);
        // 客户类生成一个类准备传输
        HelloObject object = new HelloObject(12, "this is a message");
        //调用远程类的方法，实际上通过代理实现
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
