package com.example.test2.controller;

import com.example.test2.data.dto.SearchUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.test2.service.UserService;
import com.example.test2.data.dto.UserPagingResultDTO;
import com.example.test2.utility.Utility;
import com.example.test2.data.dto.UserDTO;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    /*
        @SessionAttribute로 한번에 세션에있는값을 가져올 수 있다.
        쿠키에 있는 JSESSIONID를 가지고 세션을 검색하고, 그 세션안에 name에 넣는 키를 이용해서 값을 꺼내온다.
        로그인하지않은 사용자(JSESSIONID를 가지고있지않은 사용자)도 이용할 수 있으므로 required = false로 해둔다.
        required = true 세션에 해당 속성이 없으면 예외(HttpSessionRequiredException) 발생
        required = false일 경우 세션에 해당 속성이 없으면 null로 주입
     */

    /**
     * 로그인 성공시 유저리스트 관련한 페이지로 이동한다.
     *
     * @param pageNumber 유저리스트에서 볼 페이지 번호
     * @param model 화면 객체
     * @return user 목록 화면으로 이동
     */
    @GetMapping("/userList/page")
    //public String goUserListPage(@SessionAttribute(name = "loginId", required = false) String loginId, @ModelAttribute("pageNumber") int pageNumber, Model model) {
    public String goUserListPage(@RequestParam("pageNumber") int pageNumber, Model model) {
        UserPagingResultDTO userPagingResultDTO = userService.select10Users(pageNumber);
        model.addAttribute("userPagingResultDTO", userPagingResultDTO);
        model.addAttribute("pageNumber", pageNumber);
        log.info("userPagingResultDTO :  "+userPagingResultDTO.toString());

        return "userlist";
    }


    /**
     * 버튼을 클릭했을시 ajax로 해당 페이지 내용 반환
     * @param pageNumber 유저리스트에서 볼 페이지 번호
     * @return user list를 담은 ResponseEntity
     */
    @GetMapping("/userList/ajax")
    //public ResponseEntity<?> ExchangeUserList(@SessionAttribute(name = "loginId", required = false) String loginId, @RequestParam int pageNumber) {
    public ResponseEntity<?> ExchangeUserList(@RequestParam int pageNumber) {
        UserPagingResultDTO userPagingResultDTO = userService.select10Users(pageNumber);
        log.info("userPagingResultDTO :  "+userPagingResultDTO.toString());

        return Utility.makeResponseEntity(true, userPagingResultDTO);
    }

    @PostMapping("/search/userList")
    public ResponseEntity<?> searchUserList(@RequestBody SearchUserDTO searchUserDTO) {
        log.info("searchUserDTO :  "+searchUserDTO.toString());
        userService.selectUsersBySearchUserDTO(searchUserDTO);
        return Utility.makeResponseEntity(true, "");
    }
}
