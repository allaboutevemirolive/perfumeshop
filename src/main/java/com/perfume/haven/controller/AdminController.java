package com.perfume.haven.controller;

import com.perfume.haven.constants.Pages;
import com.perfume.haven.constants.PathConstants;
import com.perfume.haven.dto.request.PerfumeRequest;
import com.perfume.haven.dto.request.SearchRequest;
import com.perfume.haven.dto.response.UserInfoResponse;
import com.perfume.haven.service.AdminService;
import com.perfume.haven.utils.ControllerUtils;

import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping(PathConstants.ADMIN)
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final AdminService adminService;
    private final ControllerUtils controllerUtils;

    public AdminController(AdminService adminService, ControllerUtils controllerUtils) {
        this.adminService = adminService;
        this.controllerUtils = controllerUtils;
    }

    @GetMapping("/perfumes")
    public String getPerfumes(Pageable pageable, Model model) {
        controllerUtils.addPagination(model, adminService.getPerfumes(pageable));
        return Pages.ADMIN_PERFUMES;
    }

    @GetMapping("/perfumes/search")
    public String searchPerfumes(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, adminService.searchPerfumes(request, pageable));
        return Pages.ADMIN_PERFUMES;
    }

    @GetMapping("/users")
    public String getUsers(Pageable pageable, Model model) {
        controllerUtils.addPagination(model, adminService.getUsers(pageable));
        return Pages.ADMIN_ALL_USERS;
    }

    @GetMapping("/users/search")
    public String searchUsers(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, adminService.searchUsers(request, pageable));
        return Pages.ADMIN_ALL_USERS;
    }

    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", adminService.getOrder(orderId));
        return Pages.ORDER;
    }

    @GetMapping("/orders")
    public String getOrders(Pageable pageable, Model model) {
        controllerUtils.addPagination(model, adminService.getOrders(pageable));
        return Pages.ORDERS;
    }

    @GetMapping("/orders/search")
    public String searchOrders(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, adminService.searchOrders(request, pageable));
        return Pages.ORDERS;
    }

    @GetMapping("/perfume/{perfumeId}")
    public String getPerfume(@PathVariable Long perfumeId, Model model) {
        model.addAttribute("perfume", adminService.getPerfumeById(perfumeId));
        return Pages.ADMIN_EDIT_PERFUME;
    }

    @PostMapping("/edit/perfume")
    public String editPerfume(@Valid PerfumeRequest perfume, BindingResult bindingResult, Model model,
            @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        if (controllerUtils.validateInputFields(bindingResult, model, "perfume", perfume)) {
            return Pages.ADMIN_EDIT_PERFUME;
        }
        return controllerUtils.setAlertFlashMessage(attributes, "/admin/perfumes",
                adminService.editPerfume(perfume, file));
    }

    @GetMapping("/add/perfume")
    public String addPerfume() {
        return Pages.ADMIN_ADD_PERFUME;
    }

    @PostMapping("/add/perfume")
    public String addPerfume(@Valid PerfumeRequest perfume, BindingResult bindingResult, Model model,
            @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        if (controllerUtils.validateInputFields(bindingResult, model, "perfume", perfume)) {
            return Pages.ADMIN_ADD_PERFUME;
        }
        return controllerUtils.setAlertFlashMessage(attributes, "/admin/perfumes",
                adminService.addPerfume(perfume, file));
    }

    @GetMapping("/user/{userId}")
    public String getUserById(@PathVariable Long userId, Model model, Pageable pageable) {
        UserInfoResponse userResponse = adminService.getUserById(userId, pageable);
        model.addAttribute("user", userResponse.getUser());
        controllerUtils.addPagination(model, userResponse.getOrders());
        return Pages.ADMIN_USER_DETAIL;
    }
}
