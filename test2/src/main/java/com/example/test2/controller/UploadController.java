package com.example.test2.controller;

import java.util.List;

import com.example.test2.exception.EmptyUserTable;
import com.example.test2.exception.FailFileOpen;
import com.example.test2.exception.WrongFileExtension;
import com.example.test2.response.ExceptionCodeType;
import com.example.test2.response.ExceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.test2.data.dto.UserDTO;
import com.example.test2.data.dto.UserResultDTO;
import com.example.test2.service.UserService;
import com.example.test2.data.dto.UserTotalResultDTO;

@RestController
@RequestMapping("/upload/*")
@RequiredArgsConstructor
@Slf4j
public class UploadController {

    private final UserService userService;


    @PostMapping("/toTable")
    public ResponseEntity<?> toTable(MultipartFile file) {
        userService.deleteAll();

        try{
            UserTotalResultDTO userTotalResultDTO = userService.userInsert(file);

            return ResponseEntity.ok(userTotalResultDTO);

        }catch(FailFileOpen e1){
            return ResponseEntity.status(HttpStatus.OK).body(new ExceptionResponse(ExceptionCodeType.FAIL_FILE_OPEN,e1.getMessage()));
        }catch(WrongFileExtension e2){
            return ResponseEntity.status(HttpStatus.OK).body(new ExceptionResponse(ExceptionCodeType.WRONG_FILE_EXTENSION,e2.getMessage()));
        }

    }

    @PostMapping("/fullSelect")
    public ResponseEntity<?> fullSelect(){
        try{
            List<UserDTO> userDTOList = userService.findAll();
            return ResponseEntity.ok(userDTOList);
        }catch(EmptyUserTable e1){
            return ResponseEntity.status(HttpStatus.OK).body(new ExceptionResponse(ExceptionCodeType.EMPTY_USER_TABLE,e1.getMessage()));
        }

    }
}
