package com.example.test3.controller;

import com.example.test3.utility.Utility;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.test3.data.dto.UserDTO;
import com.example.test3.data.dto.UserPagingResultDTO;
import com.example.test3.service.UserService;

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
     * dhtmlx8 용
     * 로그인 성공시 유저리스트 관련한 페이지로 이동한다.
     * 페이지 진입시 전체 회원 정보들 조회 반환
     * @param model 화면 객체
     * @return user 목록 화면으로 이동
     */
    @GetMapping("/userList/page")
    public String goUserListPage( Model model) {
        UserPagingResultDTO<UserDTO> userPagingResultDTO = userService.selectAllUsers();
        model.addAttribute("userPagingResultDTO", userPagingResultDTO);

        log.info("userPagingResultDTO :  "+userPagingResultDTO.toString());

        return "userlist";
    }


    /**
     * 로그인 성공시 유저리스트 관련한 페이지로 이동한다.
     *
     * @param pageNumber 유저리스트에서 볼 페이지 번호
     * @param model 화면 객체
     * @return user 목록 화면으로 이동
     */
//    @GetMapping("/userList/page")
//    //public String goUserListPage(@SessionAttribute(name = "loginId", required = false) String loginId, @ModelAttribute("pageNumber") int pageNumber, Model model) {
//    public String goUserListPage(@RequestParam("pageNumber") int pageNumber, Model model) {
//        UserPagingResultDTO<UserDTO> userPagingResultDTO = userService.select10Users(pageNumber);
//        model.addAttribute("userPagingResultDTO", userPagingResultDTO);
//        model.addAttribute("pageNumber", pageNumber);
//        log.info("userPagingResultDTO :  "+userPagingResultDTO.toString());
//
//        return "userlist";
//    }


    /**
     * 버튼을 클릭했을시 ajax로 해당 페이지 내용 반환
     * @param pageNumber 유저리스트에서 볼 페이지 번호
     * @return user list를 담은 ResponseEntity
     */
//    @GetMapping("/userList/ajax")
//    //public ResponseEntity<?> ExchangeUserList(@SessionAttribute(name = "loginId", required = false) String loginId, @RequestParam int pageNumber) {
//    public ResponseEntity<?> ExchangeUserList(@RequestParam int pageNumber) {
//        UserPagingResultDTO<UserDTO> userPagingResultDTO = userService.select10Users(pageNumber);
//        log.info("userPagingResultDTO :  "+userPagingResultDTO.toString());
//
//        return Utility.makeResponseEntity(true, userPagingResultDTO);
//    }


    /**
     * 원래 처음에 검색 조회 버튼 클릭시 ajax로만 처리하였을때 사용
     * @param searchUserDTO 검색조건들 정보
     * @param
     * @return ResponseBase 응답
     */
//    @PostMapping("/search/userList")
//    public ResponseEntity<?> searchUserList(@RequestBody SearchUserDTO searchUserDTO) {
//        log.info("searchUserDTO :  "+searchUserDTO.toString());
//        UserPagingResultDTO<SearchUserDTOResponse> userPagingResultDTO = userService.selectUsersBySearchUserDTO(searchUserDTO);
//        return Utility.makeResponseEntity(true, userPagingResultDTO);
//    }


    /**
     * 검색 조회 버튼 클릭시 페이지 이동
     * @param searchUserDTO 검색조건들 정보
     * @param
     * @return 이동할 html 이름
     */
    //@PostMapping("/search/userList/page")
    //Ajax방식이 아닐시 @ModelAttribute는 객체로 데이터를 받을 수 있어, 여러 파라미터를 하나의 객체로 처리할 때 유용
    //이 방식은 Jackson을 거치지 않고, Spring이 내부적으로 직접 타입을 변환
    //즉, 파라미터 이름만 DTO 필드명과 정확히 일치하면, 자동으로 변환
    //이때 @JsonFormat, @JsonProperty는 무시
    //만약 아무것도 입력하지 않고 검색했다면 => SearchUserDTO(id=, pwd=null, name=, level=, desc=, pageNumber=1, regDate=null)
//    public String searchUserListAndGoPage(@ModelAttribute SearchUserDTO searchUserDTO, Model model) {
//        log.info("searchUserDTO :  "+searchUserDTO.toString());
//        UserPagingResultDTO<SearchUserDTOResponse> userPagingResultDTO = userService.selectUsersBySearchUserDTO(searchUserDTO);
//
//        model.addAttribute("userPagingResultDTO", userPagingResultDTO);
//        model.addAttribute("pageNumber", searchUserDTO.getPageNumber());
//        model.addAttribute("searchUserDTO", searchUserDTO);
//        return "searchuserlist";
//    }

    /**
     * 검색 조회 후, 하단 페이징버튼 클릭시 ajax로 처리
     * @param searchUserDTO 원하는 페이지 숫자 + 그전에 입력했던 검색 조건들 정보가 포함
     * @return ResponseBase 응답
     */
//    @PostMapping("/search/userList/ajax")
//    public ResponseEntity<?> searchUserListAndAjax(@RequestBody UserDTO searchUserDTO) {
//        log.info("searchUserDTO :  "+searchUserDTO.toString());
//        UserPagingResultDTO<UserDTO> userPagingResultDTO = userService.selectUsersBySearchUserDTO(searchUserDTO);
//        return Utility.makeResponseEntity(true, userPagingResultDTO);
//    }

    /**
     * dhtmlx8용
     * 검색 조회 후, 하단 페이징버튼 클릭시 ajax로 처리
     * @param searchUserDTO 원하는 페이지 숫자 + 그전에 입력했던 검색 조건들 정보가 포함
     * @return ResponseBase 응답
     */
    @PostMapping("/search/userList/ajax")
    public ResponseEntity<?> searchUserListAndAjax(@RequestBody UserDTO searchUserDTO) {
        log.info("searchUserDTO :  "+searchUserDTO.toString());
        UserPagingResultDTO<UserDTO> userPagingResultDTO = userService.selectUsersBySearchUserDTO(searchUserDTO);
        return Utility.makeResponseEntity(true, userPagingResultDTO);
    }

}
