package com.example.test2.exception;

public class WrongFileExtension extends Exception{
    public WrongFileExtension(String message){
        super(message);
    }
}
