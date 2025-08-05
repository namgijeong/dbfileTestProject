package com.example.test3.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class ProcessResultDTO {
    /*성공인지 실패인지 여부*/
    @Setter
    private boolean successFlag = false;

    /*성공했다면 몇번째 줄인지*/
    @Setter
    private int successLine;

    /*실패했다면 몇번째 줄인지*/
    @Setter
    private int failLine;

    /*실패했다면 그 줄 내용이 무엇인지*/
    @Setter
    private String failText;

    //로그인이 어디서 잘못되었는지
    @Setter
    private String errorMessage;

    /*로그인 필드 에러에 관하여 메시지와 LonginField 포함 */
//    private ErrorField errorField;

    /*로그인 성공시 user정보를 가져오기 위해 */
    @Setter
    private UserDTO userDTO;

    public ProcessResultDTO() {}

    public ProcessResultDTO (boolean successFlag, int successLine, int failLine, String failText, String errorMessage, UserDTO userDTO) {
        this.successFlag = successFlag;
        this.successLine = successLine;
        this.failLine = failLine;
        this.failText = failText;
        this.errorMessage = errorMessage;
        this.userDTO = userDTO;
    }

//    public void setErrorField(String exceptionMessage, LoginField loginField) {
//        ErrorField errorField = new ErrorField();
//        errorField.setExceptionMessage(exceptionMessage);
//        errorField.setLoginField(loginField);
//        this.errorField = errorField;
//    }


}
