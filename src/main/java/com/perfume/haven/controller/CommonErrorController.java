package com.perfume.haven.controller;

import com.perfume.haven.constants.Pages;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CommonErrorController implements ErrorController {

    @RequestMapping
    public String handleError(HttpServletRequest request, Model model) {
        Object statusCodeObj = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Integer statusCode = statusCodeObj instanceof Integer ? (Integer) statusCodeObj : HttpStatus.INTERNAL_SERVER_ERROR.value();

        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            model.addAttribute("errorMessage", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
            return Pages.ERROR_404;
        } 
        return Pages.ERROR_500;
    }
}

