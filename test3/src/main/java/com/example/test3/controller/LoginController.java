package com.example.test3.controller;

import com.example.test3.data.dto.ProcessResultDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.test3.data.dto.LoginUserDTO;
import com.example.test3.data.dto.ProcessResultDTO;
import com.example.test3.service.UserService;
import com.example.test3.utility.Utility;

@Controller
@RequestMapping("/login/*")
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final UserService userService;

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

    /**
     * id, password가 맞으면 유저리스트 화면으로 이동, 틀리면 다시 로그인 페이지로 이동
     * @param id
     * @param pwd
     * @param session
     * @param redirectAttributes
     * @return redirect 할 주소
     */
//    @PostMapping("/loginCheck")
//    //form 이나 url 파라미터로 넘어온 값은 @RequestBody가 아니다.
//    public String checkLogin(@RequestParam String id, @RequestParam String pwd, HttpSession session, RedirectAttributes redirectAttributes) {
//        log.info("넘어온 값 : "+id+ ", "+pwd);
//        boolean userLoginOk = userService.userLogin(id, pwd);
//        if (userLoginOk) {
//            session.setAttribute("loginId", id);
//
//            //ajax시 url이 pageNumber = 1 로 계속 보여져서 일부러 숨겼다.
//            redirectAttributes.addFlashAttribute("pageNumber", 1);
//            return "redirect:/user/userList/page";
//
//        }
//        redirectAttributes.addFlashAttribute("loginAgain", true);
//        return "redirect:/login/login";
//    }

    /**
     * id, password가 맞으면 유저리스트 화면으로 이동, 틀리면 다시 로그인 페이지로 이동
     *
     * @param "LoginUserDTO"
     * @param session
     * @return processResultDTO를 담은 ResponseBase
     */
    @PostMapping("/loginCheck")
    public ResponseEntity<?> checkLogin(@RequestBody @Valid LoginUserDTO loginUserDTO, HttpSession session) {
        String id = loginUserDTO.getId();
        String pwd = loginUserDTO.getPwd();
        log.info("넘어온 값 : "+id+ ", "+pwd);

        ProcessResultDTO userLoginOk = userService.userLogin(id, pwd);

        //boolean은 getter 메소드가 is로 시작
        boolean isSuccess = userLoginOk.isSuccessFlag();
        if (isSuccess) {
            session.setAttribute("loginUser", userLoginOk.getUserDTO());
        }

        log.info("userLoginOk : "+userLoginOk);
        //성공했으면 성공한대로, 실패했으면 실패한대로 값이 담긴다.
        return Utility.makeResponseEntity(isSuccess, userLoginOk);

    }
}
