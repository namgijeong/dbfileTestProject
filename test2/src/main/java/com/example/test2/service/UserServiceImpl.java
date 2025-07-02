package com.example.test2.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import com.example.test2.data.dao.UserDAO;
import com.example.test2.data.dto.UserDTO;
import com.example.test2.data.dto.UserResultDTO;
import com.example.test2.data.entity.User;
import com.example.test2.data.dto.UserTotalResultDTO;
import com.example.test2.utility.Utility;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserDAO userDAO;

    /*아이디를 가지고 user 레코드를 찾는다.*/
    @Override
    public UserDTO findUserById(String id) {
        User user =userDAO.select(id);
        UserDTO userDTO = Utility.makeUserToUserDTO(user);
        return userDTO;
    }

    /*업로드 된 파일을 가지고 db table에 저장한다.*/
    @Override
    public UserTotalResultDTO userInsert(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        System.out.println(originalFileName);

        if (originalFileName != null && originalFileName.matches("^.+\\.(dbfile)$")){
            System.out.println("유효한 파일");

            int count = 0;
            int successCount = 0;
            String line;
            List<UserResultDTO> userResultDTOList = new ArrayList<>();
            UserTotalResultDTO userTotalResultDTO = null;

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))){

                /*
                    파일 한줄씩 읽어라.
                    각 한줄마다 / 기준으로 분리하라.
                    형식을 바꾸고 DTO에 집어넣은 후, 엔티티 컬럼에 맞게 변화해라.
                 */

                while ((line = reader.readLine()) != null) {
                    System.out.println("읽은 줄:" +line);
                    UserResultDTO userResultDTO = null;
                    count++;

                    try{
                        //슬래시 단위로 나누기
                        String[] parts = line.split("/");

                        //공백이면 null로 만들기
                        String[] clearParts = Utility.makeEmptyStringNull(parts);
                        LocalDateTime localDateTime = Utility.makeStringToLocalDateTime(clearParts[5]);

                        //vo로 할당하기
                        UserDTO userDTO = new UserDTO.Builder()
                                            .id(clearParts[0])
                                            .pwd(clearParts[1])
                                            .name(clearParts[2])
                                            .level(clearParts[3])
                                            .desc(clearParts[4])
                                            .regDate(localDateTime)
                                            .build();

                        User user = Utility.makeUserDTOToUser(userDTO);

                        userDAO.insert(user);

                        userResultDTO = new UserResultDTO.Builder()
                                .successLine(count)
                                .successFlag(1)
                                .build();

                        successCount++;

                    } catch(Exception e){ // 개별 라인에서 오류 발생해도 다음 줄로 계속
                        System.err.println("라인 오류: " + line);

                        userResultDTO = new UserResultDTO.Builder()
                                            .failLine(count)
                                            .failText(line)
                                            .successFlag(0)
                                            .build();
                    } finally{
                        userResultDTOList.add(userResultDTO);

                    }
                }

                userTotalResultDTO = new UserTotalResultDTO.Builder()
                                        .userResultDTOList(userResultDTOList)
                                        .totalCount(count)
                                        .successCount(successCount)
                                        .build();

                return userTotalResultDTO;

            } catch (IOException e2){
                e2.printStackTrace();
                System.out.println("파일이 안열림");
                return null;
            }

        } else{
            System.out.println("잘못된 파일");
            return null;
        }
    }

    /*db table에 있는 user 레코드를 모두 조회한다.*/
    @Override
    public List<UserDTO> findAll() {
        List<User> userList = userDAO.selectAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userList){
            UserDTO userDTO = Utility.makeUserToUserDTO(user);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public void deleteAll() {
        userDAO.deleteAll();
    }


}
