package com.example.test2.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResultDTO {
    /*1이면 성공, 0이면 실패*/
    private int successFlag;
    /*성공했다면 몇번째 줄인지*/
    private int successLine;
    /*실패했다면 몇번째 줄인지*/
    private int failLine;
    /*실패했다면 그 줄 내용이 무엇인지*/
    private String failText;

    private UserResultDTO(Builder builder){
        this.successFlag = builder.successFlag;
        this.successLine = builder.successLine;
        this.failLine = builder.failLine;
        this.failText = builder.failText;
    }
    public static class Builder{
        private int successFlag;
        private int successLine;
        private int failLine;
        private String failText;

        public Builder successFlag(int successFlag){
            this.successFlag = successFlag;
            return this;
        }

        public Builder successLine(int successLine){
            this.successLine = successLine;
            return this;
        }

        public Builder failLine(int failLine){
            this.failLine = failLine;
            return this;
        }

        public Builder failText(String failText){
            this.failText = failText;
            return this;
        }

        public UserResultDTO build(){
            return new UserResultDTO(this);
        }
    }
}
