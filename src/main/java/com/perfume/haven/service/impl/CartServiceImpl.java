package com.perfume.haven.service.impl;

import com.perfume.haven.domain.Perfume;
import com.perfume.haven.domain.User;
import com.perfume.haven.repository.PerfumeRepository;
import com.perfume.haven.service.CartService;
import com.perfume.haven.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final UserService userService;
    private final PerfumeRepository perfumeRepository;

    public CartServiceImpl(UserService userService, PerfumeRepository perfumeRepository) {
        this.userService = userService;
        this.perfumeRepository = perfumeRepository;
    }

    @Override
    public List<Perfume> getPerfumesInCart() {
        User user = userService.getAuthenticatedUser();
        return user.getPerfumeList();
    }

    @Override
    @Transactional
    public void addPerfumeToCart(Long perfumeId) {
        User user = userService.getAuthenticatedUser();
        Perfume perfume = perfumeRepository.getOne(perfumeId);
        user.getPerfumeList().add(perfume);
    }

    @Override
    @Transactional
    public void removePerfumeFromCart(Long perfumeId) {
        User user = userService.getAuthenticatedUser();
        Perfume perfume = perfumeRepository.getOne(perfumeId);
        user.getPerfumeList().remove(perfume);
    }
}
