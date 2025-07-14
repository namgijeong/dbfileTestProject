package com.example.test2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.test2.data.dto.UserDTO;
import com.example.test2.service.UserService;
import com.example.test2.data.dto.UserTotalResultDTO;
import com.example.test2.response.NormalResponse;
import com.example.test2.response.ResponseBase;

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
     * @return UserTotalResultDTO가 들어있는  ResponseEntity
     */
    @PostMapping("/insertTable")
    public ResponseEntity<?> insertToTable(MultipartFile file) {
        userService.deleteAll();

        UserTotalResultDTO userTotalResultDTO = userService.userInsert(file);

        ResponseBase<UserTotalResultDTO> response = ResponseBase.makeResponseBase(true, userTotalResultDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);

        //NormalResponse<UserTotalResultDTO> response = NormalResponse.makeNormalResponse(userTotalResultDTO);
        //return ResponseEntity.status(HttpStatus.OK).body(response);

        /*
        try {
            UserTotalResultDTO userTotalResultDTO = userService.userInsert(file);

            return ResponseEntity.ok(userTotalResultDTO);

        } catch(FailFileOpen e1) {
            return ResponseEntity.status(HttpStatus.OK).body(new ExceptionResponse(ExceptionCodeType.FAIL_FILE_OPEN,""));
        } catch(WrongFileExtension e2) {
            return ResponseEntity.status(HttpStatus.OK).body(new ExceptionResponse(ExceptionCodeType.WRONG_FILE_EXTENSION,""));
        }
        */

    }

    /**
     * db table에 있는 모든 user 레코드들을 가져온다.
     * @return UserDTO가 들어있는 ResponseEntity
     */
    @PostMapping("/selectFullUsers")
    public ResponseEntity<?> fullSelect() {
        List<UserDTO> userDTOList = userService.findAll();

        ResponseBase<List<UserDTO>> response = ResponseBase.makeResponseBase(true, userDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(response);

//        NormalResponse<List<UserDTO>> response = NormalResponse.makeNormalResponse(userDTOList);
//        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
