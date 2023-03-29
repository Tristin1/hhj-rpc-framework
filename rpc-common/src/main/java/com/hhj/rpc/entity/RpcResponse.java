package com.hhj.rpc.entity;

import java.io.Serializable;

import com.hhj.rpc.enumeration.ResponseCode;

import lombok.Data;

/**
 * @author yourname <huanghaijin@kuaishou.com>
 * Created on 2023-03-25
 */

@Data
public class RpcResponse<T> implements Serializable {
    private Integer statusCode;
    private String message;
    private T data;

    public static <T> RpcResponse<T> success(T data) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(ResponseCode.SUCCESS.getCode());
        response.setData(data);
        return response;
    }

    public static <T> RpcResponse<T> fail(ResponseCode code) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }

}
