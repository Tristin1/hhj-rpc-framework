package com.hhj.test;

import com.hhj.rpc.api.HelloService;
import com.hhj.rpc.registry.DefaultServiceRegistry;
import com.hhj.rpc.registry.ServiceRegistry;
import com.hhj.rpc.server.RpcServer;

/**
 * @author yourname <huanghaijin@kuaishou.com>
 * Created on 2023-03-25
 */
public class TestServer {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.start(9000);
    }
}
