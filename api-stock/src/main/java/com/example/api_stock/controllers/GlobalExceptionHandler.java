package com.example.api_stock.controllers;

import com.example.api_stock.error.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleBusinessException(BusinessException ex){
       Map<String, String> error = new HashMap<>();
       error.put("error", "Erro de negocio");
       error.put("message", ex.getMessage());

       return error;
    }
}
