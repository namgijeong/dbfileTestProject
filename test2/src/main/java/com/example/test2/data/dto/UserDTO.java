package com.example.test2.data.dto;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.test2.data.entity.User;
import com.example.test2.exception.WrongFieldException;
import com.example.test2.exception.WrongFieldExceptions;
import com.example.test2.utility.Utility;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
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

    private UserDTO(Builder builder){
        this.id = builder.id;
        this.pwd = builder.pwd;
        this.name = builder.name;
        this.level = builder.level;
        this.desc = builder.desc;
        this.regDate = builder.regDate;
    }

    public UserDTO(UserDtoBase user) {
        this.id = user.getId();
        this.pwd = user.getPwd();
        this.name = user.getName();
        this.level = user.getLevel();
        this.desc = user.getDesc();
        this.regDate = user.getRegDate();
    }

    public UserDTO(String[] parts) throws WrongFieldException {
        //desc 칼럼 없을때
        if (parts.length == 5) {
            this.id = parts[0];
            this.pwd = parts[1];
            this.name = parts[2];
            this.level = parts[3];
            this.regDate = Utility.makeStringToLocalDateTime(parts[4]);

        //desc 칼럼 있을때
        }else{
            this.id = parts[0];
            this.pwd = parts[1];
            this.name = parts[2];
            this.level = parts[3];
            this.desc = parts[4];
            this.regDate = Utility.makeStringToLocalDateTime(parts[5]);
        }

    }

    public static class Builder{
        private String id;
        private String pwd;
        private String name;
        private String level;
        private String desc;
        private LocalDateTime regDate;

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder pwd(String pwd){
            this.pwd = pwd;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder level(String level){
            this.level = level;
            return this;
        }

        public Builder desc(String desc){
            this.desc = desc;
            return this;
        }

        public Builder regDate(LocalDateTime regDate){
            this.regDate = regDate;
            return this;
        }

        public UserDTO build(){
            return new UserDTO(this);
        }
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

    /*DTO 필드가 null인지 검사해서 잘못됬다면 어느 필드가 잘못됬는지 알려준다*/
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


        /*
        if(userDTO.getId() == null){
            WrongFieldList.add("id 필드를 사용자가 입력하지 않았습니다.");
        }
        if(userDTO.getPwd() == null){
            WrongFieldList.add("pwd 필드가 사용자가 입력하지 않았습니다.");
        }
        if(userDTO.getName() == null){
            WrongFieldList.add("name 필드가 사용자가 입력하지 않았습니다.");
        }
        if(userDTO.getLevel() == null){
            WrongFieldList.add("level 필드가 사용자가 입력하지 않았습니다.");
        }
        if(userDTO.getRegDate() == null){
            WrongFieldList.add("reg_date 필드가 사용자가 입력하지 않았습니다.");
        }
         */

    }

}
