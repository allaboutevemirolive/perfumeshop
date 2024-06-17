package com.perfume.haven.service;

import com.perfume.haven.domain.Perfume;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface CartService {

    List<Perfume> getPerfumesInCart();

    ResponseEntity<String> addPerfumeToCart(Long perfumeId);

    ResponseEntity<String> removePerfumeFromCart(Long perfumeId);
}
