package com.hhj.rpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hhj.rpc.entity.RpcRequest;
import com.hhj.rpc.entity.RpcResponse;

/**
 * @author yourname <huanghaijin@kuaishou.com>
 * Created on 2023-03-25
 */
public class WorkerThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(WorkerThread.class);

    private Socket socket;
    private Object service;

    public WorkerThread(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            //通过反射调用，制定方法名称以及参数类型，获得方法,过反射调用方法，参数为对象和方法参数
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            Object returnObject = method.invoke(service, rpcRequest.getParameters());
            objectOutputStream.writeObject(RpcResponse.success(returnObject));
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}