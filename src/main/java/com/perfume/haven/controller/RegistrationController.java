package com.perfume.haven.controller;

import com.perfume.haven.constants.Pages;
import com.perfume.haven.constants.PathConstants;
import com.perfume.haven.dto.request.UserRequest;
import com.perfume.haven.dto.response.MessageResponse;
import com.perfume.haven.service.RegistrationService;
import com.perfume.haven.utils.ControllerUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping(PathConstants.REGISTRATION)
public class RegistrationController {

    private final RegistrationService registrationService;
    private final ControllerUtils controllerUtils;

    public RegistrationController(RegistrationService registrationService, ControllerUtils controllerUtils) {
        this.registrationService = registrationService;
        this.controllerUtils = controllerUtils;
    }

    @GetMapping
    public String registration() {
        return Pages.REGISTRATION;
    }

    @PostMapping
    public String registration(@RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid UserRequest user,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {
        if (controllerUtils.validateInputFields(bindingResult, model, "user", user)) {
            return Pages.REGISTRATION;
        }
        MessageResponse messageResponse = registrationService.registration(captchaResponse, user);
        if (controllerUtils.validateInputField(model, messageResponse, "user", user)) {
            return Pages.REGISTRATION;
        }
        return controllerUtils.setAlertFlashMessage(redirectAttributes, PathConstants.LOGIN, messageResponse);
    }

    @GetMapping("/activate/{code}")
    public String activateEmailCode(@PathVariable String code, Model model) {
        return controllerUtils.setAlertMessage(model, Pages.LOGIN, registrationService.activateEmailCode(code));
    }
}
