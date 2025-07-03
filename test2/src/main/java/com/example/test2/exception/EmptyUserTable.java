package com.example.test2.exception;

public class EmptyUserTable extends Exception{
    public EmptyUserTable(String message){
        super(message);
    }
}
