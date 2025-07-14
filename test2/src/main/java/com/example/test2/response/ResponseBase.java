package com.example.test2.response;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public class ResponseBase<T> {

    private boolean success;

    private T content;


}

