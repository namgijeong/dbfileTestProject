package com.example.test2.controller;

import java.util.List;

import com.example.test2.exception.FailFileOpen;
import com.example.test2.exception.WrongFileExtension;
import com.example.test2.response.ExceptionCodeType;
import com.example.test2.response.ExceptionResponse;
import com.example.test2.response.NormalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.test2.data.dto.UserDTO;
import com.example.test2.service.UserService;
import com.example.test2.data.dto.UserTotalResultDTO;

@RestController
@RequestMapping("/upload/*")
@RequiredArgsConstructor
@Slf4j
public class UploadController {

    private final UserService userService;


    @PostMapping("/insertTable")
    public ResponseEntity<?> toTable(MultipartFile file){
        userService.deleteAll();

        UserTotalResultDTO userTotalResultDTO = userService.userInsert(file);

        NormalResponse<UserTotalResultDTO> response = NormalResponse.makeNormalResponse(userTotalResultDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);

        /*
        try{
            UserTotalResultDTO userTotalResultDTO = userService.userInsert(file);

            return ResponseEntity.ok(userTotalResultDTO);

        } catch(FailFileOpen e1) {
            return ResponseEntity.status(HttpStatus.OK).body(new ExceptionResponse(ExceptionCodeType.FAIL_FILE_OPEN,""));
        } catch(WrongFileExtension e2) {
            return ResponseEntity.status(HttpStatus.OK).body(new ExceptionResponse(ExceptionCodeType.WRONG_FILE_EXTENSION,""));
        }
        */

    }

    @PostMapping("/selectFullUsers")
    public ResponseEntity<?> fullSelect(){

        List<UserDTO> userDTOList = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(userDTOList);

    }
}
