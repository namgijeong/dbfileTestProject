package com.example.test2.data.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserTotalResultDTO {
    /*각 파일줄마다 성공인지,실패인지,몇번째 줄인지,내용에 대한 정보*/
    List<UserResultDTO> userResultDTOList;
    /*전체 파일 줄 개수 */
    int totalCount;
    /*전체 삽입 성공한 파일 줄 개수*/
    int successCount;

    private UserTotalResultDTO(Builder builder){
        this.userResultDTOList = builder.userResultDTOList;
        this.totalCount = builder.totalCount;
        this.successCount = builder.successCount;
    }

    public static class Builder{
        private List<UserResultDTO> userResultDTOList;
        private int totalCount;
        private int successCount;

        public Builder userResultDTOList(List<UserResultDTO>  userResultDTOList){
            this.userResultDTOList = userResultDTOList;
            return this;
        }

        public Builder totalCount(int totalCount){
            this.totalCount = totalCount;
            return this;
        }

        public Builder successCount(int successCount){
            this.successCount = successCount;
            return this;
        }

        public UserTotalResultDTO build(){
            return new UserTotalResultDTO(this);
        }
    }
}
