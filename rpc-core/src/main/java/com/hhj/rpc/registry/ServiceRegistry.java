package com.hhj.rpc.registry;

/**
 * 注册服务表通用接口
 * @author yourname <huanghaijin@kuaishou.com>
 * Created on 2023-03-29
 */
public interface ServiceRegistry {

    <T> void register(T service);
    Object getService(String serviceName);
}
