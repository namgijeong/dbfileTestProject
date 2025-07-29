package com.example.test3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    //private final UserService userService;

    /*
        @SessionAttribute로 한번에 세션에있는값을 가져올 수 있다.
        쿠키에 있는 JSESSIONID를 가지고 세션을 검색하고, 그 세션안에 name에 넣는 키를 이용해서 값을 꺼내온다.
        로그인하지않은 사용자(JSESSIONID를 가지고있지않은 사용자)도 이용할 수 있으므로 required = false로 해둔다.
        required = true 세션에 해당 속성이 없으면 예외(HttpSessionRequiredException) 발생
        required = false일 경우 세션에 해당 속성이 없으면 null로 주입
     */

//    /**
//     * 로그인 성공시 유저리스트 관련한 페이지로 이동한다.
//     *
//     * @param pageNumber 유저리스트에서 볼 페이지 번호
//     * @param model 화면 객체
//     * @return user 목록 화면으로 이동
//     */
    @GetMapping("/userList/page")
    //public String goUserListPage(@SessionAttribute(name = "loginId", required = false) String loginId, @ModelAttribute("pageNumber") int pageNumber, Model model) {
    //public String goUserListPage(@RequestParam("pageNumber") int pageNumber, Model model) {
    public String goUserListPage() {
//        UserPagingResultDTO<UserDTO> userPagingResultDTO = userService.select10Users(pageNumber);
//        model.addAttribute("userPagingResultDTO", userPagingResultDTO);
//        model.addAttribute("pageNumber", pageNumber);
//        log.info("userPagingResultDTO :  "+userPagingResultDTO.toString());

        return "userlist";
    }
}
