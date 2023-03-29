package com.hhj.rpc.exception;

import com.hhj.rpc.enumeration.RpcError;

/**
 * @author yourname <huanghaijin@kuaishou.com>
 * Created on 2023-03-29
 */
public class RpcException extends RuntimeException {
    public RpcException(RpcError error, String detail) {
        super(error.getMessage() + ": " + detail);
    }

    ;

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    ;

    public RpcException(RpcError error) {
        super(error.getMessage());
    }

    ;
}
