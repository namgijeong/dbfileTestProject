package com.example.test2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private final UserService userService;


    @GetMapping("/")
    public String showForm(){
        return "form";
    }

    @PostMapping("/toTable")
    public UserTotalResultDTO toTable(MultipartFile file) {
        userService.deleteAll();

        UserTotalResultDTO userTotalResultDTO = userService.userInsert(file);
        if (userTotalResultDTO != null){
            return userTotalResultDTO;
        } else{
            return null;
        }

    }

    @PostMapping("/fullSelect")
    public List<UserDTO> fullSelect(){
        List<UserDTO> userDTOList = userService.findAll();

        if (userDTOList != null && userDTOList.size()>0){
            return userDTOList;
        } else{
            return null;
        }

    }
}
