package com.zhonghuilv.shouyin.exception;


import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.zhonghuilv.shouyin.result.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
@Component
public class DefaultExceptionHandler {


    public ApiResult unknownException(SQLException e) {
        return ApiResult.error(50301L, "未知的错误");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class})
    public ApiResult sqlException(SQLException e) {

        if (e instanceof MySQLIntegrityConstraintViolationException) {
            MySQLIntegrityConstraintViolationException cast = (MySQLIntegrityConstraintViolationException) e;
            return ApiResult.error(5002300L, e.getMessage());


        }
        return ApiResult.error(50301L, "SQL异常：" + e.getMessage());
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({IndexOutOfBoundsException.class})
    public ApiResult indexOutOfBoundsException(IndexOutOfBoundsException e) {

        if (e instanceof IndexOutOfBoundsException) {
            IndexOutOfBoundsException cast = (IndexOutOfBoundsException) e;
            return ApiResult.error(5002300L, e.getMessage());

        }
        return ApiResult.error(50301L, "数组越界异常：" + e.getMessage());
    }

}
