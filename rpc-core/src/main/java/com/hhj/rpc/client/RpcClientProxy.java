package com.hhj.rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.hhj.rpc.entity.RpcRequest;
import com.hhj.rpc.entity.RpcResponse;

/**
 * @author yourname <huanghaijin@kuaishou.com>
 * Created on 2023-03-25
 */
public class RpcClientProxy implements InvocationHandler {

    private String host;
    private int port;

    public RpcClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[] {clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameters(args)
                .paramTypes(method.getParameterTypes())
                .build();
        RpcClient rpcClient = new RpcClient();
        return ((RpcResponse) rpcClient.sendRequest(rpcRequest, host, port)).getData();
    }
}
