package com.example.test2.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.test2.service.UserService;
import com.example.test2.data.dto.UserDTO;
import com.example.test2.data.dto.UserPagingResultDTO;
import com.example.test2.response.NormalResponse;

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
    public String loginCheck(@RequestParam String idInput, @RequestParam String pwdInput, HttpSession session, RedirectAttributes redirectAttributes) {
        log.info("넘어온 값 : "+idInput+ ", "+pwdInput);
        boolean userLoginOk = userService.userLogin(idInput, pwdInput);
        if (userLoginOk){
            session.setAttribute("loginId", idInput);

            //ajax시 url이 pageNumber = 1 로 계속 보여져서 일부러 숨겼다.
            redirectAttributes.addFlashAttribute("pageNumber", 1);
            return "redirect:/user/loginOk/page";

            //return "redirect:/user/loginOk/page?pageNumber=1";
        }
        redirectAttributes.addFlashAttribute("loginAgain", true);
        return "redirect:/user/login";
    }

    /*
        @SessionAttribute로 한번에 세션에있는값을 가져올 수 있다.
        쿠키에 있는 JSESSIONID를 가지고 세션을 검색하고, 그 세션안에 name에 넣는 키를 이용해서 값을 꺼내온다.
        로그인하지않은 사용자(JSESSIONID를 가지고있지않은 사용자)도 이용할 수 있으므로 required = false로 해둔다.
     */
    @GetMapping("/loginOk/page")
    public String loginOkPage(@SessionAttribute(name = "loginId", required = false) String loginId, @ModelAttribute("pageNumber") int pageNumber, Model model) {
        if(loginId == null){
            log.warn("비정상적인 접근!");
            return "redirect:/user/login";
        }
        //UserPagingResultDTO userPagingResultDTO = userService.select10Users(11L);
        UserPagingResultDTO userPagingResultDTO = userService.select10Users(pageNumber);
        model.addAttribute("userPagingResultDTO", userPagingResultDTO);
        log.info("userPagingResultDTO :  "+userPagingResultDTO.toString());

        return "loginOk";
    }

    /*
        버튼을 클릭했을시 ajax로 해당 페이지 내용 반환
     */
    @GetMapping("/loginOk/ajax")
    public ResponseEntity<?> loginOkAjax(@SessionAttribute(name = "loginId", required = false) String loginId, @RequestParam int pageNumber, Model model) {
        if(loginId == null){
            log.warn("비정상적인 접근!");
            //return "redirect:/user/login";
        }
        //UserPagingResultDTO userPagingResultDTO = userService.select10Users(11L);
        UserPagingResultDTO userPagingResultDTO = userService.select10Users(pageNumber);
        log.info("userPagingResultDTO :  "+userPagingResultDTO.toString());

        NormalResponse<UserPagingResultDTO> response = NormalResponse.makeNormalResponse(userPagingResultDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
