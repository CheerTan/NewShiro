package com.learn.shirologin.util;


import com.learn.shirologin.vo.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object object){

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(200);
        resultVO.setMsg("success");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO error(int code,String msg){

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }


}
