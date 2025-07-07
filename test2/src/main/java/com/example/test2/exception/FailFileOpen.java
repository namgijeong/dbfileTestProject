package com.example.test2.exception;

/*
    Unchecked Exception	RuntimeException 하위로 선언해야
    컨트롤러에서 try - catch 또 사용안하고
    @RestControllerAdvice 에서 처리할 수 있다.
 */
public class FailFileOpen extends RuntimeException{
    public FailFileOpen(String message){
        super(message);
    }
}
