package com.hhj.rpc.api;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yourname <huanghaijin@kuaishou.com>
 * Created on 2023-03-25
 */
@Data
@AllArgsConstructor
public class HelloObject implements Serializable {
    private Integer id;
    private String message;
}
