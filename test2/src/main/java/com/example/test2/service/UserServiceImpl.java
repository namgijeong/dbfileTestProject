package com.example.test2.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import com.example.test2.exception.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.test2.data.dao.UserDAO;
import com.example.test2.data.dto.UserDTO;
import com.example.test2.data.dto.UserResultDTO;
import com.example.test2.data.entity.User;
import com.example.test2.data.dto.UserTotalResultDTO;
import com.example.test2.utility.Utility;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    /*아이디를 가지고 user 레코드를 찾는다.*/
    @Override
    public UserDTO findUserById(String id){
        User user = userDAO.select(id);
        return new UserDTO(user);
    }

    /*업로드 된 파일을 가지고 db table에 저장한다.*/
    @Override
    public UserTotalResultDTO userInsert(MultipartFile file) throws FailFileOpen, WrongFileExtension{
        String originalFileName = file.getOriginalFilename();
        System.out.println(originalFileName);

        if (originalFileName != null && originalFileName.matches("^.+\\.(dbfile)$")){
            log.info("유효한 파일");

            try (BufferedReader reader = new BufferedReader(
                         new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))){
                int count = 0;
                int successCount = 0;
                String line;
                List<UserResultDTO> userResultDTOList = new ArrayList<>();
                UserTotalResultDTO userTotalResultDTO = null;

                /*
                    파일 한줄씩 읽어라.
                    각 한줄마다 / 기준으로 분리하라.
                    형식을 바꾸고 DTO에 집어넣은 후, 엔티티 컬럼에 맞게 변화해라.
                 */
                while ((line = reader.readLine()) != null){
                    log.info("읽은 줄:" +line);

                    UserResultDTO userResultDTO = null;
                    count++;

                    try{
                        //슬래시 단위로 나누기
                        String[] parts = line.split("/");

                        //각 문자열 파트 개수가 맞는지 검사
                        boolean isStringLengthValid = Utility.checkStringCount(parts);
                        if(!isStringLengthValid){
                            throw new StringTokenException("/로 구분한 칼럼 개수가 잘못되었습니다.");
                        }

                        //토큰이 공백이면 null로 만들어준다.
                        String[] clearParts = Utility.makeEmptyStringNull(parts);
                        //이미 여기서 생성할때 멤버변수에 집어넣는 값이 잘못되면 예외처리가 생김
                        UserDTO userDTO =  new UserDTO(clearParts);

                        User user = UserDTO.makeUserDTOToUser(userDTO);
                        userDAO.insert(user);

                        userResultDTO = new UserResultDTO.Builder()
                                .successLine(count)
                                .successFlag(1)
                                .build();

                        successCount++;

                    } catch(Exception e){ // 개별 라인에서 오류 발생해도 다음 줄로
                        log.warn("exception 종류 : "+e.getClass().getName());
                        log.warn(e.getMessage());
                        log.warn("라인 오류: " + line);


                        userResultDTO = new UserResultDTO.Builder()
                                            .failLine(count)
                                            .failText(line)
                                            .successFlag(0)
//                                            .exceptionMessage(e.getMessage())
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

                log.warn("파일 열기 실패");
                throw new FailFileOpen("파일 열기 실패");
            }

        } else{
            log.warn("잘못된 파일 확장자");
            throw new WrongFileExtension("잘못된 파일 확장자");

        }
    }

    /*db table에 있는 user 레코드를 모두 조회한다.*/
    @Override
    public List<UserDTO> findAll(){
        List<User> userList = userDAO.selectAll();

        /*
        //List<UserDTO> userDTOList = new ArrayList<>();

        if(userList.isEmpty()){
            throw new EmptyUserTable("현재 아무데이터도 테이블에 있지 않습니다.");
        }
        else{
            for (User user : userList){
                UserDTO userDTO = new UserDTO(user);
                userDTOList.add(userDTO);
            }
            return userDTOList;
        }
         */

        List<UserDTO> userDTOList = userList.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
        return userDTOList;

    }

    @Override
    public void deleteAll() {
        userDAO.deleteAll();
    }


}
