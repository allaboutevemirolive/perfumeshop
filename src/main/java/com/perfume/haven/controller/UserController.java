package com.perfume.haven.controller;

import com.perfume.haven.constants.Pages;
import com.perfume.haven.constants.PathConstants;
import com.perfume.haven.dto.request.ChangePasswordRequest;
import com.perfume.haven.dto.request.EditUserRequest;
import com.perfume.haven.dto.request.SearchRequest;
import com.perfume.haven.dto.response.MessageResponse;
import com.perfume.haven.service.UserService;
import com.perfume.haven.utils.ControllerUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@Controller
@RequestMapping(PathConstants.USER)
public class UserController {

    private final UserService userService;
    private final ControllerUtils controllerUtils;

    public UserController(UserService userService, ControllerUtils controllerUtils) {
        this.userService = userService;
        this.controllerUtils = controllerUtils;
    }

    @GetMapping("/contacts")
    public String contacts() {
        return Pages.CONTACTS;
    }

    @GetMapping("/reset")
    public String passwordReset() {
        return Pages.USER_PASSWORD_RESET;
    }

    @GetMapping("/account")
    public String userAccount(Model model) {
        model.addAttribute("user", userService.getAuthenticatedUser());
        return Pages.USER_ACCOUNT;
    }

    @GetMapping("/orders/search")
    public String searchUserOrders(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, userService.searchUserOrders(request, pageable));
        return Pages.ORDERS;
    }

    @GetMapping("/info")
    public String userInfo(Model model) {
        model.addAttribute("user", userService.getAuthenticatedUser());
        String requestUri = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
        model.addAttribute("requestUri", requestUri);
        return Pages.USER_INFO;
    }

    @GetMapping("/info/edit")
    public String editUserInfo(Model model) {
        model.addAttribute("user", userService.getAuthenticatedUser());
        return Pages.USER_INFO_EDIT;
    }

    @PostMapping("/info/edit")
    public String editUserInfo(@Valid EditUserRequest request, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) {
        if (controllerUtils.validateInputFields(bindingResult, model, "user", request)) {
            return Pages.USER_INFO_EDIT;
        }
        MessageResponse messageResponse = userService.editUserInfo(request);
        return controllerUtils.setAlertFlashMessage(redirectAttributes, "/user/info", messageResponse);
    }

    @PostMapping("/change/password")
    public String changePassword(@Valid ChangePasswordRequest request, BindingResult bindingResult, Model model) {
        if (controllerUtils.validateInputFields(bindingResult, model, "request", request)) {
            return Pages.USER_PASSWORD_RESET;
        }
        MessageResponse messageResponse = userService.changePassword(request);
        if (controllerUtils.validateInputField(model, messageResponse, "request", request)) {
            return Pages.USER_PASSWORD_RESET;
        }
        return controllerUtils.setAlertMessage(model, Pages.USER_PASSWORD_RESET, messageResponse);
    }
}
