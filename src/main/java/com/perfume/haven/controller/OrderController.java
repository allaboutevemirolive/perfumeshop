package com.perfume.haven.controller;

import com.perfume.haven.constants.Pages;
import com.perfume.haven.constants.PathConstants;
import com.perfume.haven.domain.Order;
import com.perfume.haven.domain.User;
import com.perfume.haven.dto.request.OrderRequest;
import com.perfume.haven.service.OrderService;
import com.perfume.haven.service.UserService;
import com.perfume.haven.utils.ControllerUtils;
import com.perfume.haven.utils.PrintForTest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping(PathConstants.ORDER)
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ControllerUtils controllerUtils;

    public OrderController(OrderService orderService, UserService userService, ControllerUtils controllerUtils) {
        this.orderService = orderService;
        this.userService = userService;
        this.controllerUtils = controllerUtils;
    }

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", orderService.getOrder(orderId));
        return Pages.ORDER;
    }

    @GetMapping
    public String getOrdering(Model model) {
        model.addAttribute("perfumes", orderService.getOrdering());
        return Pages.ORDERING;
    }

    @GetMapping("/user/orders")
    public String getUserOrdersList(Model model, Pageable pageable, HttpServletRequest request) {
        Page<Order> orders = orderService.getUserOrdersList(pageable);
        controllerUtils.addPagination(model, orders);

        String baseUri = ServletUriComponentsBuilder.fromRequestUri(request)
                .replaceQueryParam("page")
                .replaceQueryParam("size")
                .toUriString();

        model.addAttribute("baseUri", baseUri);

        return Pages.ORDERS;
    }

    @PostMapping
    public String postOrder(@Valid OrderRequest orderRequest, BindingResult bindingResult, Model model) {
        User user = userService.getAuthenticatedUser();
        if (controllerUtils.validateInputFields(bindingResult, model, "perfumes", user.getPerfumeList())) {
            return Pages.ORDERING;
        }
        model.addAttribute("orderId", orderService.postOrder(user, orderRequest));
        return Pages.ORDER_FINALIZE;
    }
}
