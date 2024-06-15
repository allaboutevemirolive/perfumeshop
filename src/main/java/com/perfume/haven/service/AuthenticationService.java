package com.perfume.haven.service;

import com.perfume.haven.dto.request.PasswordResetRequest;
import com.perfume.haven.dto.response.MessageResponse;

public interface AuthenticationService {

    MessageResponse sendPasswordResetCode(String email);

    String getEmailByPasswordResetCode(String code);

    MessageResponse resetPassword(PasswordResetRequest request);
}
