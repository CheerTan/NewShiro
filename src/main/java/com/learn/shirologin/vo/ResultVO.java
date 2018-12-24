package com.learn.shirologin.vo;

import lombok.Data;
import lombok.ToString;

/**
 * http请求返回的最外层对象
 */

@Data
@ToString
public class ResultVO<T> {
    /**
     * 状态码
     * */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
