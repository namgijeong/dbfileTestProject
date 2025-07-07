package com.example.test2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
@Slf4j
public class UserController {


    @GetMapping("/file")
    public String showForm(){
        return "form";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
