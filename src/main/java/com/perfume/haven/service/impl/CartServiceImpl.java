package com.perfume.haven.service.impl;

import com.perfume.haven.domain.Perfume;
import com.perfume.haven.domain.User;
import com.perfume.haven.repository.PerfumeRepository;
import com.perfume.haven.service.CartService;
import com.perfume.haven.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<String> addPerfumeToCart(Long perfumeId) {
        User user = userService.getAuthenticatedUser();
        Optional<Perfume> perfumeOptional = perfumeRepository.findById(perfumeId);

        if (perfumeOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Perfume not found with ID: " + perfumeId);
        }

        Perfume perfume = perfumeOptional.get();
        user.getPerfumeList().add(perfume);
        
        return ResponseEntity.ok("Perfume added to cart");
    }

    @Override
    @Transactional
    public ResponseEntity<String> removePerfumeFromCart(Long perfumeId) {
        User user = userService.getAuthenticatedUser();
        Optional<Perfume> perfumeOptional = perfumeRepository.findById(perfumeId);

        if (perfumeOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Perfume not found with ID: " + perfumeId);
        }

        Perfume perfume = perfumeOptional.get();
        user.getPerfumeList().remove(perfume);
        
        return ResponseEntity.ok("Perfume remove from cart");
    }
}
