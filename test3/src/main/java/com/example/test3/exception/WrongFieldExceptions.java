package com.example.test3.exception;

import java.util.List;

public class WrongFieldExceptions extends Exception {
    public WrongFieldExceptions(List<String> messages) {
        super(String.join(", ",messages));
    }
}
