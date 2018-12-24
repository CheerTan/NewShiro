package com.learn.shirologin.exception;

import com.learn.shirologin.util.ResultEnum;
import lombok.Data;

@Data
public class CenterException extends RuntimeException {

    private Integer code;

    public CenterException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
