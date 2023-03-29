package com.hhj.rpc.server;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hhj.rpc.entity.RpcRequest;

/**
 * @author yourname <huanghaijin@kuaishou.com>
 * Created on 2023-03-29
 */
public class RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    public Object handle(RpcRequest rpcRequest, Object service) {
        Object result = null;
        try {
            result = invokeTargetMethod(rpcRequest, service);
            logger.info("服务{}成功调用方法{}", rpcRequest.getInterfaceName(), rpcRequest.getMethodName());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Object invokeTargetMethod(RpcRequest rpcRequest, Object service)
            throws InvocationTargetException, IllegalAccessException {
        Method method;
        try {
            method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return method.invoke(service, rpcRequest.getParameters());
    }
}
