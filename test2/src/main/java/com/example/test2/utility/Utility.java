package com.example.test2.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.example.test2.data.dto.ButtonBlockDTO;
import lombok.extern.slf4j.Slf4j;

import com.example.test2.exception.StringTokenException;
import com.example.test2.exception.WrongFieldException;

@Slf4j
public class Utility {

    public static boolean checkStringCount(String[] parts) {
        log.info("/로 자른 토큰 개수 : "+parts.length);
        //desc를 제외해서 유효한 토큰 개수인 경우
        if (parts.length == 6 || parts.length == 5) {
            return true;
        }
        return false;
    }

    /*
        문자열 각 부분이 공백으로 이루어진 빈 문자열일때 null로 처리
        컬럼이 not null인데 정상 db insert되는 것을 방지
        내용이 있는경우 공백만 제거한다.
     */
    public static String[] makeEmptyStringNull(String[] parts) {
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
    public static String[] checkStringCount(String[] parts) throws StringTokenException {
        //desc를 제외해서 유효한 토큰 개수인 경우
        if (parts.length == 6 || parts.length == 5) {
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].trim().equals("")) {
                    parts[i] = null;
                    log.info("parts" + i + "null로 변했습니다.");
                } else {
                    parts[i] = parts[i].trim();
                    log.info("parts" + i + "공백을 제거하였습니다.");
                }
            }

            log.info("토큰 개수 : " + parts.length);
            return parts;
        } else {  //잘못된 토큰 개수인 경우
            log.info("토큰 개수 : "+parts.length);
            throw new StringTokenException("/로 구분한 칼럼 개수가 잘못되었습니다.");
        }
    }
     */

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
        //  / / / / / 이런 형태일때 날짜 형식 문자열이 아예 없을때 parse를 시도하면 null pointer exception이 뜬다.
        }catch(NullPointerException e){
            throw new WrongFieldException("날짜 문자열을 yyyy-MM-dd HH:mm:ss 형태로 입력해주세요.");
        }

    }


    public static boolean isStringUpperCase(String str) throws NullPointerException {
        try{
            char[] chars = str.toCharArray();
            for (char c : chars){
                if (!Character.isUpperCase(c)){
                    return false;
                }
            }

            return true;

        } catch(NullPointerException e){
            throw new NullPointerException();
        }

    }

    public static boolean isStringNumber(String str) throws NullPointerException {
        try{
            char[] chars = str.toCharArray();
            for (char c : chars){
                if (!Character.isDigit(c)){
                    return false;
                }
            }

            return true;

        } catch(NullPointerException e){
            throw new NullPointerException();
        }

    }

    public static boolean isStringUpperChar(String str) throws NullPointerException {
        try{
            char[] chars = str.toCharArray();
            if (chars.length == 1 && Character.isUpperCase(chars[0])){
                return true;
            }
            return false;

        } catch(NullPointerException e){
            throw new NullPointerException();
        }

    }

    public static boolean isStringLocalDateTimeFormat(String str) {
        if (str.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$")){
            return true;
        }
        return false;
    }

    /*하단 버튼 페이징 숫자 계산해서 ButtonBlockDTO 만들기*/
    public static ButtonBlockDTO makeButtonBlockDTO(long currentPageNumber, long totalCount) {
        log.info("currentPageNumber : "+currentPageNumber);
        log.info("totalCount : "+totalCount);
        //한 페이지당 게시글 10개
        long totalPageNumber = (long)Math.ceil((double)totalCount / 10);
        log.info("totalPageNumber : "+totalPageNumber);
        //한 블록당 버튼은 10개씩 보여주기
        long totalBlockNumber = (long)Math.ceil((double)totalPageNumber / 10);
        log.info("totalBlockNumber : "+totalBlockNumber);
        //10 페이지는 1블록에 속한다.
        long currentBlockNumber = (long)Math.ceil((double)currentPageNumber / 10);
        log.info("currentBlockNumber : "+currentBlockNumber);
        long currentBlockFirstNumber = ((currentBlockNumber - 1) * 10) + 1;
        log.info("currentBlockFirstNumber : "+currentBlockFirstNumber);
        long currentBlockLastNumber = ((currentBlockNumber - 1) * 10) + 10;
        log.info("currentBlockLastNumber : "+currentBlockLastNumber);

        //만약 총 게시글 페이지 보다 많게 계산되면 안된다.
        if (currentBlockLastNumber > totalPageNumber) {
            currentBlockLastNumber = totalPageNumber;
        }

        boolean previousBlock = false;
        boolean nextBlock = false;
        long previousBlockPageNumber = 0L;
        long nextBlockPageNumber = 0L;
        if (currentBlockNumber > 1) {
            previousBlock = true;
            previousBlockPageNumber = currentBlockFirstNumber - 1;

        } else {
            previousBlock = false;
        }

        if (currentBlockNumber < totalBlockNumber) {
            nextBlock = true;
            nextBlockPageNumber = currentBlockLastNumber + 1;
        } else {
            nextBlock = false;
        }

        ButtonBlockDTO buttonBlockDTO = ButtonBlockDTO.builder()
                .currentPageNumber(currentPageNumber)
                .currentBlockNumber(currentBlockNumber)
                .currentBlockFirstNumber(currentBlockFirstNumber)
                .currentBlockLastNumber(currentBlockLastNumber)
                .previousBlock(previousBlock)
                .previousBlockPageNumber(previousBlockPageNumber)
                .nextBlock(nextBlock)
                .nextBlockPageNumber(nextBlockPageNumber)
                .build();

        return buttonBlockDTO;
    }

}
