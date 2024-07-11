package com.perfume.haven.service.impl;

import com.perfume.haven.constants.ErrorMessage;
import com.perfume.haven.domain.Perfume;
import com.perfume.haven.dto.request.SearchRequest;
import com.perfume.haven.repository.PerfumeRepository;
import com.perfume.haven.service.PerfumeService;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
public class PerfumeServiceImpl implements PerfumeService {

    private final PerfumeRepository perfumeRepository;
    private final ModelMapper modelMapper; // TODO: Remove unsused import

    public PerfumeServiceImpl(PerfumeRepository perfumeRepository, ModelMapper modelMapper) {
        this.perfumeRepository = perfumeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Perfume getPerfumeById(Long perfumeId) {
        return perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.PERFUME_NOT_FOUND));
    }

    @Override
    public List<Perfume> getPopularPerfumes() {
        List<Long> perfumeIds = Arrays.asList(26L, 43L, 46L, 106L, 34L, 76L, 82L, 85L, 27L, 39L, 79L, 86L);
        return perfumeRepository.findByIdIn(perfumeIds);
    }

    @Override
    public Page<Perfume> getPerfumesByFilterParams(SearchRequest request, Pageable pageable) {
        Integer startingPrice = request.getPrice();
        Integer endingPrice = startingPrice + (startingPrice == 0 ? 500 : 50);
        return perfumeRepository.getPerfumesByFilterParams(
                request.getPerfumers(),
                request.getGenders(),
                startingPrice,
                endingPrice,
                pageable);
    }

    @Override
    public Page<Perfume> searchPerfumes(SearchRequest request, Pageable pageable) {
        return perfumeRepository.searchPerfumes(request.getSearchType(), request.getText(), pageable);
    }
}
