package com.yanszero.ytdemo.common.exception;

import com.yanszero.ytdemo.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // 針對哪一類異常進行處理
    @ResponseBody // 這樣才能做輸出
    public Result error(Exception e) {
        e.printStackTrace();
        return  Result.fail();
    }
}
