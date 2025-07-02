package com.example.test2.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.test2.data.dto.UserDTO;
import com.example.test2.data.entity.User;

public class Utility {
    /*
        문자열 각 부분이 공백으로 이루어진 빈 문자열일때 null로 처리
        컬럼이 not null인데 정상 db insert되는 것을 방지
        내용이 있는경우 공백만 제거한다.
     */
    public static String[] makeEmptyStringNull(String[] parts){
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
    public static LocalDateTime makeStringToLocalDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);

        return localDateTime;
    }

    /*DTO를 entity로 변환*/
    public static User makeUserDTOToUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setPwd(userDTO.getPwd());
        user.setName(userDTO.getName());
        user.setLevel(userDTO.getLevel());
        user.setDesc(userDTO.getDesc());
        user.setRegDate(userDTO.getRegDate());
        return user;
    }

    /*Entity를 DTO로 변환 */
    public static UserDTO makeUserToUserDTO(User user){
        UserDTO userDTO = new UserDTO.Builder()
                            .id(user.getId())
                            .pwd(user.getPwd())
                            .name(user.getName())
                            .level(user.getLevel())
                            .desc(user.getDesc())
                            .regDate(user.getRegDate())
                            .build();

        return userDTO;
    }

}
