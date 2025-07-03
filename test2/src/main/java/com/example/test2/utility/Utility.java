package com.example.test2.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.example.test2.data.dto.UserDTO;
import com.example.test2.data.entity.User;
import com.example.test2.exception.StringTokenException;
import com.example.test2.exception.WrongDateTimeStringFormat;
import com.example.test2.exception.WrongFieldException;

public class Utility {

    public static String[] checkStringCount(String[] parts) throws StringTokenException {
        //desc를 제외해서 유효한 토큰 개수인 경우
        if (parts.length == 6 || parts.length == 5){
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
        //잘못된 토큰 개수인 경우
        else{
            throw new StringTokenException("/로 구분한 칼럼 개수가 잘못되었습니다.");
        }
    }
    /*
        문자열 각 부분이 공백으로 이루어진 빈 문자열일때 null로 처리
        컬럼이 not null인데 정상 db insert되는 것을 방지
        내용이 있는경우 공백만 제거한다.
     */
//    public static String[] makeEmptyStringNull(String[] parts){
//        for (int i = 0; i < parts.length; i++){
//            if(parts[i].trim().equals("")){
//                parts[i] = null;
//            }
//            else{
//                parts[i] = parts[i].trim();
//            }
//        }
//
//        return parts;
//    }

    /*
     * String을 LocalDateTime으로 바꿔야한다.
     * 이때 String이어도 형식이 맞아야해서
     * 사전에 공백을 제거했었다.
     * 실패하면 이 자체로 DateTimeParseException e 날린다
     * */
    public static LocalDateTime makeStringToLocalDateTime(String date) throws WrongFieldException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try{
            LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
            return localDateTime;
        }catch(DateTimeParseException e){
            throw new WrongFieldException("날짜 문자열을 yyyy-MM-dd HH:mm:ss 형태로 입력해주세요.");
        }

    }

}
