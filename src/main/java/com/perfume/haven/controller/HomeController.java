package com.perfume.haven.controller;

import com.perfume.haven.constants.Pages;
import com.perfume.haven.service.PerfumeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final PerfumeService perfumeService;

    public HomeController(PerfumeService perfumeService) {
        this.perfumeService = perfumeService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("perfumes", perfumeService.getPopularPerfumes());
        return Pages.HOME;
    }
}
