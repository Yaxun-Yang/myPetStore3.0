package org.csu.mypetstore.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.csu.mypetstore.template.ResponseTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {


        return ResponseTemplate
                .builder()
                .status(500)
                .statusText("服务器出错");
    }
}
