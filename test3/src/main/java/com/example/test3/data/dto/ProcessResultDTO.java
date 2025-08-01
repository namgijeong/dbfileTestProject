package com.example.test3.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class ProcessResultDTO {
    /*성공인지 실패인지 여부*/
    @Setter
    private boolean successFlag;

    /*성공했다면 몇번째 줄인지*/
    @Setter
    private int successLine;

    /*실패했다면 몇번째 줄인지*/
    @Setter
    private int failLine;

    /*실패했다면 그 줄 내용이 무엇인지*/
    @Setter
    private String failText;

    /*exception 메세지*/
    @Setter
    private String exceptionMessage;

    /*로그인 필드 에러가 어디에서 일어났는지 */
    @Setter
    private LoginField loginField;

    /*로그인 필드 에러에 관하여 메시지와 LonginField 포함 */
    private ErrorField  errorField;

    /*로그인 성공시 user정보를 가져오기 위해 */
    @Setter
    private UserDTO userDTO;

    public ProcessResultDTO() {}

    public void setErrorField(String exceptionMessage, LoginField loginField) {
        ErrorField errorField = new ErrorField();
        errorField.setExceptionMessage(exceptionMessage);
        errorField.setLoginField(loginField);
        this.errorField = errorField;
    }

//
//
//    @AllArgsConstructor
//    public static class ErrorField {
//
//        String message;
//        LoginField loginField;
//
//    }

    private ProcessResultDTO(Builder builder) {
        this.successFlag = builder.successFlag;
        this.successLine = builder.successLine;
        this.failLine = builder.failLine;
        this.failText = builder.failText;
        this.exceptionMessage = builder.exceptionMessage;
        this.loginField = builder.loginField;
        this.errorField = builder.errorField;
        this.userDTO = builder.userDTO;
    }

    public static class Builder {

        private boolean successFlag;

        private int successLine;

        private int failLine;

        private String failText;

        private String exceptionMessage;

        private LoginField loginField;

        private ErrorField errorField;

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

        public Builder errorField(String exceptionMessage, LoginField loginField) {
            this.errorField.setExceptionMessage(exceptionMessage);
            this.errorField.setLoginField(loginField);
            return this;
        }

        public Builder userDTO(UserDTO userDTO) {
            this.userDTO = userDTO;
            return this;
        }

        public ProcessResultDTO build() {
            return new ProcessResultDTO(this);
        }
    }
}
