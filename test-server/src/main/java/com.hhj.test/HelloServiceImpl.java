package com.hhj.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hhj.rpc.api.HelloObject;
import com.hhj.rpc.api.HelloService;

/**
 * @author yourname <huanghaijin@kuaishou.com>
 * Created on 2023-03-25
 */
public class HelloServiceImpl implements HelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);
    @Override
    public String hello(HelloObject object) {
        logger.info("j接收到：{}",object.getMessage());
        return "调用返回值 =" + object.getId();
    }
}
