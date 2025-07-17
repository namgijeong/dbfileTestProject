package com.example.test2.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResultDTO {

    /*성공인지 실패인지 여부*/
    private boolean successFlag;

    /*성공했다면 몇번째 줄인지*/
    private int successLine;

    /*실패했다면 몇번째 줄인지*/
    private int failLine;

    /*실패했다면 그 줄 내용이 무엇인지*/
    private String failText;

    /*exception 메세지*/
    private String exceptionMessage;

    /*로그인 필드 에러가 어디에서 일어났는지 */
    private LoginField loginField;

    /*로그인 성공시 user정보를 가져오기 위해 */
    private UserDTO userDTO;

    public UserResultDTO() {}

    private UserResultDTO(Builder builder) {
        this.successFlag = builder.successFlag;
        this.successLine = builder.successLine;
        this.failLine = builder.failLine;
        this.failText = builder.failText;
        this.exceptionMessage = builder.exceptionMessage;
        this.loginField = builder.loginField;
        this.userDTO = builder.userDTO;
    }

    public static class Builder {

        private boolean successFlag;

        private int successLine;

        private int failLine;

        private String failText;

        private String exceptionMessage;

        private LoginField loginField;

        private UserDTO userDTO;

        public Builder successFlag(boolean successFlag) {
            this.successFlag = successFlag;
            return this;
        }

        public Builder successLine(int successLine) {
            this.successLine = successLine;
            return this;
        }

        public Builder failLine(int failLine) {
            this.failLine = failLine;
            return this;
        }

        public Builder failText(String failText) {
            this.failText = failText;
            return this;
        }

        public Builder exceptionMessage(String exceptionMessage) {
            this.exceptionMessage = exceptionMessage;
            return this;
        }

        public Builder loginField(LoginField loginField) {
            this.loginField = loginField;
            return this;
        }

        public Builder userDTO(UserDTO userDTO) {
            this.userDTO = userDTO;
            return this;
        }

        public UserResultDTO build() {
            return new UserResultDTO(this);
        }
    }
}
