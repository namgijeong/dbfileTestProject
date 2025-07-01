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

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserDAO userDAO;

    /*아이디를 가지고 user 레코드를 찾는다.*/
    @Override
    public UserDTO findUserById(String id) {
        User user =userDAO.select(id);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setPwd(user.getPwd());
        userDTO.setName(user.getName());
        userDTO.setLevel(user.getLevel());
        userDTO.setDesc(user.getDesc());
        userDTO.setRegDate(user.getRegDate());

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
            List<UserResultDTO> userResultDTOList = new ArrayList<>();
            UserTotalResultDTO userTotalResultDTO = new UserTotalResultDTO();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))){

                String line;

                /*
                    파일 한줄씩 읽어라.
                    각 한줄마다 / 기준으로 분리하라.
                    형식을 바꾸고 DTO에 집어넣은 후, 엔티티 컬럼에 맞게 변화해라.
                 */

                while ((line = reader.readLine()) != null) {
                    System.out.println("읽은 줄:" +line);
                    UserResultDTO userResultDTO =  new UserResultDTO();
                    count++;

                    try{
                        //슬래시 단위로 나누기
                        String[] parts = line.split("/");

                        //vo로 할당하기
                        UserDTO userDTO = new UserDTO();

                        //공백이면 null로 만들기
                        String[] clearParts = makeEmptyStringNull(parts);

                        userDTO.setId(clearParts[0]);
                        userDTO.setPwd(clearParts[1]);
                        userDTO.setName(clearParts[2]);
                        userDTO.setLevel(clearParts[3]);
                        userDTO.setDesc(clearParts[4]);

                        LocalDateTime localDateTime = makeStringToLocalDateTime(clearParts[5]);
                        userDTO.setRegDate(localDateTime);

                        User user = new User();
                        user.setId(userDTO.getId());
                        user.setPwd(userDTO.getPwd());
                        user.setName(userDTO.getName());
                        user.setLevel(userDTO.getLevel());
                        user.setDesc(userDTO.getDesc());
                        user.setRegDate(userDTO.getRegDate());

                        userDAO.insert(user);

                        userResultDTO.setSuccessLine(count);
                        userResultDTO.setSuccessFlag(1);
                        successCount++;

                    } catch(Exception e){ // 개별 라인에서 오류 발생해도 다음 줄로 계속
                        System.err.println("라인 오류: " + line);
                        userResultDTO.setFailLine(count);
                        userResultDTO.setFailText(line);
                        userResultDTO.setSuccessFlag(0);
                    } finally{
                        userResultDTOList.add(userResultDTO);

                    }
                }

                userTotalResultDTO.setUserResultDTOList(userResultDTOList);
                userTotalResultDTO.setTotalCount(count);
                userTotalResultDTO.setSuccessCount(successCount);

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
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setPwd(user.getPwd());
            userDTO.setName(user.getName());
            userDTO.setLevel(user.getLevel());
            userDTO.setDesc(user.getDesc());
            userDTO.setRegDate(user.getRegDate());

            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public void deleteAll() {
        userDAO.deleteAll();
    }

    /*
        문자열 각 부분이 공백으로 이루어진 빈 문자열일때 null로 처리
        컬럼이 not null인데 정상 db insert되는 것을 방지
        내용이 있는경우 공백만 제거한다.
     */
    public String[] makeEmptyStringNull(String[] parts){
        for (int i = 0; i < parts.length; i++){
            if(parts[i].trim().equals("")){
                parts[i] = null;
            }
            else{
                parts[i] = parts[i].trim();
            }
        }

        return parts;
    }

    /*
     * String을 LocalDateTime으로 바꿔야한다.
     * 이때 String이어도 형식이 맞아야해서
     * 사전에 공백을 제거했었다.
     * */
    public LocalDateTime makeStringToLocalDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);

        return localDateTime;
    }
}
