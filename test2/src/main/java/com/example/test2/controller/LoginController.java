package com.example.test2.controller;

import com.example.test2.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    /**
     * id, password가 맞으면 유저리스트 화면으로 이동, 틀리면 다시 로그인 페이지로 이동
     * @param id
     * @param pwd
     * @param session
     * @param redirectAttributes
     * @return redirect 할 주소
     */
    @PostMapping("/loginCheck")
    //form 이나 url 파라미터로 넘어온 값은 @RequestBody가 아니다.
    public String checkLogin(@RequestParam String id, @RequestParam String pwd, HttpSession session, RedirectAttributes redirectAttributes) {
        log.info("넘어온 값 : "+id+ ", "+pwd);
        boolean userLoginOk = userService.userLogin(id, pwd);
        if (userLoginOk) {
            session.setAttribute("loginId", id);

            //ajax시 url이 pageNumber = 1 로 계속 보여져서 일부러 숨겼다.
            redirectAttributes.addFlashAttribute("pageNumber", 1);
            return "redirect:/user/userList/page";

        }
        redirectAttributes.addFlashAttribute("loginAgain", true);
        return "redirect:/login/login";
    }
}
