package com.example.test3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/*")
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    /**
     * 로그인 화면으로 이동한다.
     * @return 로그인 화면을 반환한다.
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }


    @GetMapping("/test")
    public String showTest(){
        return "test";
    }
}
