package com.perfume.haven.service;

import com.perfume.haven.dto.response.MessageResponse;
import com.perfume.haven.dto.request.UserRequest;

public interface RegistrationService {

    MessageResponse registration(String captchaResponse, UserRequest user);

    MessageResponse activateEmailCode(String code);
}
