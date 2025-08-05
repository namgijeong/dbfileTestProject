package com.example.test3.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.test3.data.dto.UserDTO;
import com.example.test3.data.dto.ProcessTotalResultDTO;
import com.example.test3.service.UserService;
import com.example.test3.utility.Utility;
import com.example.test3.data.dto.ProcessTotalResultDTO;

@Controller
@RequestMapping("/upload/*")
@RequiredArgsConstructor
@Slf4j
public class UploadController {

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
     * 파일을 업로드하여 db table에 자동으로 레코드를 추가
     *
     * @param  "파일형태" ".dbfile" 확장자 파일
     * @return processTotalResultDTO가 들어있는  ResponseEntity
     */
    @PostMapping("/insert_table")
    public ResponseEntity<?> insertToTable(MultipartFile file) {
        userService.deleteAll();

        ProcessTotalResultDTO processTotalResultDTO = userService.userInsert(file);

        return Utility.makeResponseEntity(true, processTotalResultDTO);

    }

    /**
     * db table에 있는 모든 user 레코드들을 가져온다.
     * @return UserDTO가 들어있는 ResponseEntity
     */
    @PostMapping("/select_full_users")
    public ResponseEntity<?> fullSelect() {
        List<UserDTO> userDTOList = userService.findAll();

        return Utility.makeResponseEntity(true, userDTOList);

    }
}
