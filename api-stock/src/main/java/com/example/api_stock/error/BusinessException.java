package com.example.api_stock.error;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}
