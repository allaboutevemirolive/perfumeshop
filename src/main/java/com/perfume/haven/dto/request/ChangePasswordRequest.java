package com.perfume.haven.dto.request;

import com.perfume.haven.constants.ErrorMessage;

import jakarta.validation.constraints.Size;

public class ChangePasswordRequest {

    @Size(min = 6, max = 16, message = ErrorMessage.PASSWORD_CHARACTER_LENGTH)
    private String password;

    @Size(min = 6, max = 16, message = ErrorMessage.PASSWORD2_CHARACTER_LENGTH)
    private String password2;

    public ChangePasswordRequest() {

    }

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public String toString() {
        return "ChangePasswordRequest{" +
                "password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                '}';
    }
}
