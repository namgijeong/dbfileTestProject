package com.example.test3.controller;

import com.example.test3.data.dto.*;
import com.example.test3.data.entity.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.test3.service.UserService;
import com.example.test3.utility.Utility;

@Controller
@RequestMapping("/register/*")
@RequiredArgsConstructor
@Slf4j
public class RegisterController {
    private final UserService userService;

    //회원추가페이지로 이동
    @GetMapping("/insert_page")
    public String goInsertUserPage() {
        return "userinsert";
    }

    //회원가입시-아이디가 중복되었는지 체크
    @PostMapping("/check/duplicated_id")
    public ResponseEntity<?> checkDuplicatedId(@RequestBody SearchUserDTO searchUserDTO) {
        ProcessResultDTO processResultDTO = userService.isIDDuplicated(searchUserDTO.getId());
        return Utility.makeResponseEntity(true, processResultDTO);
    }

    /**
     * 회원가입이 성공하면 alert 나타나고 유저리스트 화면으로 이동,
     * 실패하면 다시 회원가입 페이지 상태 그대로
     *
     * @param "LoginUserDTO"
     * @return processResultDTO를 담은 ResponseBase
     */
    @PostMapping("/register_check")
    public ResponseEntity<?> checkRegister(@RequestBody @Valid RegisterUserDTO registerUserDTO) {

        String id = registerUserDTO.getId();
        String pwd = registerUserDTO.getPwd();
        String name = registerUserDTO.getName();
        String level = registerUserDTO.getLevel();
        String desc =  registerUserDTO.getDesc();

        log.info("넘어온 값 : "+id+ ", "+pwd+ ", "+name+ ", "+level+ ", "+desc);

        //등록날짜는 오늘날로 설정
        registerUserDTO.setRegDate(LocalDateTime.now());

        //테이블에 삽입
        userService.registerUser(registerUserDTO);

        return Utility.makeResponseEntity(true, "회원가입 성공");

    }


    //회원수정페이지로 이동
    @PostMapping("/update_page")
    public String goUpdateUserPage(@ModelAttribute SearchUserDTO searchUserDTO, Model model) {
        log.info("searchUserDTO :  "+searchUserDTO.toString());

        ProcessResultDTO processResultDTO= userService.findUser(searchUserDTO);
        if(!processResultDTO.isSuccessFlag()){
            return "userupdate";
        }

        log.info("processResultDTO :  "+processResultDTO.toString());
        model.addAttribute("processResultDTO", processResultDTO);

        return "userupdate";
    }

    /**
     * 회원가입이 성공하면 alert 나타나고 유저리스트 화면으로 이동,
     * 실패하면 다시 회원가입 페이지 상태 그대로
     *
     * @param "LoginUserDTO"
     * @return processResultDTO를 담은 ResponseBase
     */
    @PostMapping("/update_check")
    public ResponseEntity<?> checkUpdate(@RequestBody @Valid RegisterUserDTO registerUserDTO) {

        String id = registerUserDTO.getId();
        String pwd = registerUserDTO.getPwd();
        String name = registerUserDTO.getName();
        String level = registerUserDTO.getLevel();
        String desc =  registerUserDTO.getDesc();

        log.info("넘어온 값 : "+id+ ", "+pwd+ ", "+name+ ", "+level+ ", "+desc);

        //등록날짜는 오늘날로 설정
        //registerUserDTO.setRegDate(LocalDateTime.now());

        //테이블에 삽입


        return Utility.makeResponseEntity(true, "회원가입 성공");

    }
}
