package com.perfume.haven.controller;

import com.perfume.haven.constants.Pages;
import com.perfume.haven.constants.PathConstants;
import com.perfume.haven.service.CartService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(PathConstants.CART)
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String getCart(Model model) {
        model.addAttribute("perfumes", cartService.getPerfumesInCart());
        return Pages.CART;
    }

    @PostMapping("/add")
    public String addPerfumeToCart(@RequestParam("perfumeId") Long perfumeId) {
        cartService.addPerfumeToCart(perfumeId);
        return "redirect:" + PathConstants.CART;
    }

    @PostMapping("/remove")
    public String removePerfumeFromCart(@RequestParam("perfumeId") Long perfumeId) {
        cartService.removePerfumeFromCart(perfumeId);
        return "redirect:" + PathConstants.CART;
    }
}
