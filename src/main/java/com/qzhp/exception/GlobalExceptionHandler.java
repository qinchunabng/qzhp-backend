package com.qzhp.exception;

import com.qzhp.common.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * spring全局异常处理
 *
 * @author qcb
 * @date 2022/05/28 13:30.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public CommonResult handleException(Exception e){
        logger.error(e.getMessage(), e);
        if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            // getFieldError获取的是第一个不合法的参数(P.S.如果有多个参数不合法的话)
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                String msg = fieldError.getDefaultMessage();
                return CommonResult.failed(msg);
            }
        } else if (e instanceof BindException) {
            // getFieldError获取的是第一个不合法的参数(P.S.如果有多个参数不合法的话)
            FieldError fieldError = ((BindException) e).getFieldError();
            if (fieldError != null) {
                String msg = fieldError.getDefaultMessage();
                return CommonResult.failed(msg);
            }
        }else if(e instanceof BusinessException){
            BusinessException businessException = (BusinessException) e;
            return CommonResult.failed(businessException.getCode(), businessException.getMsg());
        }
        return CommonResult.failed("未知异常");
    }
}
