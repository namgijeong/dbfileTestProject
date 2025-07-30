package com.example.test3.data.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.ToString;

import com.example.test3.data.entity.User;
import com.example.test3.utility.Utility;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.test3.exception.WrongFieldException;

@Getter
@ToString
public class UserDTO {
    private String id;

    private String pwd;

    private String name;

    private String level;

    private String desc;

    /*
        db에 timestamp로 넣기 위해
        String을 LocalDateTime으로 변환
    */
    @JsonProperty("reg_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;

    public UserDTO() {}

    /**
     * entity를 dto로 변환한다.
     * @param "UserDtoBase를 상속한 Entity" 객체
     */
    public UserDTO(UserDtoBase user) {
        this.id = user.getId();
        this.pwd = user.getPwd();
        this.name = user.getName();
        this.level = user.getLevel();
        this.desc = user.getDesc();
        this.regDate = user.getRegDate();
        //this.regDate = user.getRegDate().toLocalDateTime();
    }

    /**
     * 각 토큰을 검사해서 dto에 매핑한다.
     * 각 멤버변수마다 setter함수로 넣는다.
     * 직접적으로 멤버변수에 넣기전에 검사해서 exception 발생
     *
     * @param "토큰 형식의 문자열"
     * @throws WrongFieldException
     */
    public UserDTO(String[] parts) throws WrongFieldException {
        /*
            desc 칼럼 없을때
            만약 desc 칼럼 없고 regdate 있어서 토큰이 5개여도 각각 넣는 검사때 걸린다.
         */
        if (parts.length == 5) {
            setId(parts[0]);
            setPwd(parts[1]);
            setName(parts[2]);
            setLevel(parts[3]);
            setRegDate(parts[4]);

        } else {  //desc 칼럼 있을때
            setId(parts[0]);
            setPwd(parts[1]);
            setName(parts[2]);
            setLevel(parts[3]);
            setDesc(parts[4]);
            setRegDate(parts[5]);

        }

    }


    public void setId(String id) throws WrongFieldException {
        boolean isUpperString = false;
        try {
            isUpperString = Utility.isStringUpperCase(id);
        } catch (NullPointerException e) {
            throw new WrongFieldException("id 필드를 입력안했습니다.");
        }

        if (!isUpperString) {
            throw new WrongFieldException("id가 모두 대문자가 아닙니다.");
        }
        this.id = id;
    }

    public void setPwd(String pwd) throws WrongFieldException {
        boolean isNumberString = false;
//        try {
//            isNumberString = Utility.isStringNumber(pwd);
//        } catch (NullPointerException e) {
//            throw new WrongFieldException("pwd 필드를 입력안했습니다.");
//        }
//
//        if (!isNumberString) {
//            throw new WrongFieldException("pwd가 모두 숫자가 아닙니다.");
//        }
        if (pwd == null) {
            throw new WrongFieldException("pwd 필드를 입력안했습니다.");
        }
        this.pwd = pwd;
    }

    public void setName(String name) throws WrongFieldException {
        if (name == null) {
            throw new WrongFieldException("name 필드를 입력안했습니다.");
        }
        this.name = name;
    }

    public void setLevel(String level) throws WrongFieldException {
        boolean isCharUpperString = false;
        try {
            isCharUpperString = Utility.isStringUpperChar(level);
        } catch (NullPointerException e) {
            throw new WrongFieldException("level 필드를 입력안했습니다.");
        }

        if (!isCharUpperString) {
            throw new WrongFieldException("level은 글자 하나여야 하고 대문자여야 합니다.");
        }
        this.level = level;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setRegDate(String regDate) throws WrongFieldException {
        boolean isStringLocalDateTimeFormat = Utility.isStringLocalDateTimeFormat(regDate);
        if (!isStringLocalDateTimeFormat) {
            throw new WrongFieldException("날짜 문자열을 yyyy-MM-dd HH:mm:ss 형태로 입력해주세요.");
        }
        this.regDate = Utility.makeStringToLocalDateTime(regDate);
    }


    /**
     * UserDTO를 User entity로 반환한다.
     * @param "UserDTO"
     * @return User entity
     */
    public static User makeUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setPwd(userDTO.getPwd());
        user.setName(userDTO.getName());
        user.setLevel(userDTO.getLevel());
        user.setDesc(userDTO.getDesc());
        user.setRegDate(userDTO.getRegDate());
        //user.setRegDate(Timestamp.valueOf(userDTO.getRegDate()));
        return user;
    }

    /*DTO 필드가 null인지 검사해서 잘못됬다면 어느 필드가 잘못됬는지 알려준다*/
    /*
    public static void checkUserDTOField(UserDTO userDTO) throws WrongFieldExceptions{
        List<String> WrongFieldList = new ArrayList();

        for (Field field : userDTO.getClass().getDeclaredFields()){
            field.setAccessible(true);

            try{
                if (!field.getName().equals("desc") && field.get(userDTO) == null){
                    WrongFieldList.add(field.getName()+ "를 사용자가 입력하지 않았습니다." );
                } else{
                    //id는 모두 대문자여야 한다.
                    if (field.getName().equals("id")){
                        boolean isUpperString = Utility.isStringUpperCase((String)field.get(userDTO));
                        if (!isUpperString){
                            WrongFieldList.add("id가 모두 대문자가 아닙니다.");
                        }
                    }

                    //패스워드는 모두 숫자형태여야 한다.
                    if (field.getName().equals("pwd")){
                        boolean isNumberString = Utility.isStringNumber((String)field.get(userDTO));
                        if (!isNumberString){
                            WrongFieldList.add("pwd가 모두 숫자가 아닙니다.");
                        }
                    }

                    //레벨은 글자 하나이고 대문자여야한다.
                    if (field.getName().equals("level")){
                        boolean isCharUpperString = Utility.isStringUpperChar((String)field.get(userDTO));
                        if (!isCharUpperString){
                            WrongFieldList.add("level은 글자 하나여야 하고 대문자여야 합니다.");
                        }
                    }
                }
            }catch(IllegalAccessException e){
                WrongFieldList.add(field.getName()+ "필드를 접근할 수 없습니다." );

            }catch(NullPointerException e){
                WrongFieldList.add(field.getName()+ "null 객체 접근하였습니다." );
            }

        }

        if(!WrongFieldList.isEmpty()){
            throw new WrongFieldExceptions(WrongFieldList);
        }

    }
    */

}
