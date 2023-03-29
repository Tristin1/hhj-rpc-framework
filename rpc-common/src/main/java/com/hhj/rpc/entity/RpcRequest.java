package com.hhj.rpc.entity;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * @author yourname <huanghaijin@kuaishou.com>
 * Created on 2023-03-25
 */

@Data
@Builder
public class RpcRequest implements Serializable {

    /**
     * 待调用接口名称
     */
    private String interfaceName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 方法参数
     */
    private Object[] parameters;

    /**
     * 调用方法的参数类型
     */
    private Class<?>[] paramTypes;
}
