package com.soowoowon.viewModel;

/**
 * 폼(form)의 유효성 검사가 실패했을 때, 실패한 필드 이름과 에러메세지를 나타내는 클래스.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see ValidationResponse
 * @see com.soowoowon.service.ValidationResponseService
 */
public class ErrorMessage {

    private String fieldName;
    private String message;

    public ErrorMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}