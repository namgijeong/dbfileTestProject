package com.example.test2.controller;

import java.net.URI;
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

    /**
     * 파일 올리기 화면으로 이동한다.
     * @return 파일 올리기 화면을 반환한다.
     */
    @GetMapping("/file")
    public String showForm() {
        return "form";
    }

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
        return "redirect:/user/login";
    }

    /*
        @SessionAttribute로 한번에 세션에있는값을 가져올 수 있다.
        쿠키에 있는 JSESSIONID를 가지고 세션을 검색하고, 그 세션안에 name에 넣는 키를 이용해서 값을 꺼내온다.
        로그인하지않은 사용자(JSESSIONID를 가지고있지않은 사용자)도 이용할 수 있으므로 required = false로 해둔다.
        required = true 세션에 해당 속성이 없으면 예외(HttpSessionRequiredException) 발생
        required = false일 경우 세션에 해당 속성이 없으면 null로 주입
     */

    /**
     *
     * @param loginId 세션에서 가져온 로그인 아이디
     * @param pageNumber 유저리스트에서 볼 페이지 번호
     * @param model 화면 객체
     * @return user 목록 화면으로 이동
     */
    @GetMapping("/userList/page")
    public String goUserListPage(@SessionAttribute(name = "loginId", required = false) String loginId, @ModelAttribute("pageNumber") int pageNumber, Model model) {
        if (loginId == null) {
            log.warn("비정상적인 접근!");
            return "redirect:/user/login";
        }
        //UserPagingResultDTO userPagingResultDTO = userService.select10Users(11L);
        UserPagingResultDTO userPagingResultDTO = userService.select10Users(pageNumber);
        model.addAttribute("userPagingResultDTO", userPagingResultDTO);
        model.addAttribute("pageNumber", pageNumber);
        log.info("userPagingResultDTO :  "+userPagingResultDTO.toString());

        return "loginOk";
    }

    /*
        버튼을 클릭했을시 ajax로 해당 페이지 내용 반환
     */

    /**
     *
     * @param loginId 세션에서 가져온 로그인 아이디
     * @param pageNumber 유저리스트에서 볼 페이지 번호
     * @return user list를 담은 ResponseEntity
     */
    @GetMapping("/userList/ajax")
    public ResponseEntity<?> ExchangeUserList(@SessionAttribute(name = "loginId", required = false) String loginId, @RequestParam int pageNumber) {
        if (loginId == null) {
            log.warn("비정상적인 접근!");
            //return "redirect:/user/login";
            String redirectUrl = "redirect:/user/login";
            log.warn("redirectUrl :  "+ redirectUrl);
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectUrl)).build();
        }
        //UserPagingResultDTO userPagingResultDTO = userService.select10Users(11L);
        UserPagingResultDTO userPagingResultDTO = userService.select10Users(pageNumber);
        log.info("userPagingResultDTO :  "+userPagingResultDTO.toString());

        NormalResponse<UserPagingResultDTO> response = NormalResponse.makeNormalResponse(userPagingResultDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
