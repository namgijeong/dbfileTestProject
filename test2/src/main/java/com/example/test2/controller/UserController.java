package com.example.test2.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.test2.service.UserService;
import com.example.test2.data.dto.UserDTO;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/file")
    public String showForm(){
        return "form";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/loginCheck")
    //form 이나 url 파라미터로 넘어온 값은 @RequestBody가 아니다.
    public String loginCheck(@RequestParam String idInput, @RequestParam String pwdInput, HttpSession session, RedirectAttributes redirectAttributes){
        log.info("넘어온 값 : "+idInput+ ", "+pwdInput);
        boolean userLoginOk = userService.userLogin(idInput, pwdInput);
        if (userLoginOk){
            session.setAttribute("loginId", idInput);
            return "redirect:/user/loginOk";
        }
        redirectAttributes.addFlashAttribute("loginAgain", true);
        return "redirect:/user/login";
    }

    @GetMapping("/loginOk")
    public String loginOk(HttpSession session, Model model){
        List<UserDTO> userDTOList = userService.select10Users(1);
        model.addAttribute("userDTOList", userDTOList);
        for(UserDTO userDTO : userDTOList){
            log.info("회원 DTO : "+userDTO.toString());
        }
        return "loginOk";
    }
}
