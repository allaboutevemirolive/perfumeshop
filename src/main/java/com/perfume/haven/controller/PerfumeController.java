package com.perfume.haven.controller;

import com.perfume.haven.configuration.ServletUriComponentsBuilderWrapper;
import com.perfume.haven.constants.Pages;
import com.perfume.haven.constants.PathConstants;
import com.perfume.haven.dto.request.SearchRequest;
import com.perfume.haven.service.PerfumeService;
import com.perfume.haven.utils.ControllerUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(PathConstants.PERFUME)
public class PerfumeController {

    private final PerfumeService perfumeService;
    private final ControllerUtils controllerUtils;

    @Autowired
    private ServletUriComponentsBuilderWrapper servletUriComponentsBuilderWrapper;

    public PerfumeController(PerfumeService perfumeService, ControllerUtils controllerUtils) {
        this.perfumeService = perfumeService;
        this.controllerUtils = controllerUtils;
    }

    @GetMapping("/{perfumeId}")
    public String getPerfumeById(@PathVariable Long perfumeId, Model model) {
        model.addAttribute("perfume", perfumeService.getPerfumeById(perfumeId));
        return Pages.PERFUME;
    }

    @GetMapping
    public String getPerfumesByFilterParams(SearchRequest request, Model model, Pageable pageable) {
        controllerUtils.addPagination(request, model, perfumeService.getPerfumesByFilterParams(request, pageable));
        model.addAttribute("servletUriComponentsBuilderWrapper", servletUriComponentsBuilderWrapper);
        return Pages.PERFUMES;
    }

    @GetMapping("/search")
    public String searchPerfumes(SearchRequest request, Model model, Pageable pageable) {
        controllerUtils.addPagination(request, model, perfumeService.searchPerfumes(request, pageable));
        model.addAttribute("servletUriComponentsBuilderWrapper", servletUriComponentsBuilderWrapper);
        return Pages.PERFUMES;
    }

}
