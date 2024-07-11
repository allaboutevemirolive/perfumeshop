package com.perfume.haven.service.impl;

import com.perfume.haven.constants.ErrorMessage;
import com.perfume.haven.constants.SuccessMessage;
import com.perfume.haven.domain.Order;
import com.perfume.haven.domain.Perfume;
import com.perfume.haven.domain.User;
import com.perfume.haven.dto.request.PerfumeRequest;
import com.perfume.haven.dto.request.SearchRequest;
import com.perfume.haven.dto.response.MessageResponse;
import com.perfume.haven.dto.response.UserInfoResponse;
import com.perfume.haven.repository.OrderRepository;
import com.perfume.haven.repository.PerfumeRepository;
import com.perfume.haven.repository.UserRepository;
import com.perfume.haven.service.AdminService;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("adminServiceImpl")
public class AdminServiceImpl implements AdminService {

    @Value("${upload.path}")
    private String uploadPath;

    private final UserRepository userRepository;
    private final PerfumeRepository perfumeRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public AdminServiceImpl(UserRepository userRepository, PerfumeRepository perfumeRepository,
            OrderRepository orderRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.perfumeRepository = perfumeRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<Perfume> getPerfumes(Pageable pageable) {
        return perfumeRepository.findAllByOrderByPriceAsc(pageable);
    }

    @Override
    public Page<Perfume> searchPerfumes(SearchRequest request, Pageable pageable) {
        return perfumeRepository.searchPerfumes(request.getSearchType(), request.getText(), pageable);
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> searchUsers(SearchRequest request, Pageable pageable) {
        return userRepository.searchUsers(request.getSearchType(), request.getText(), pageable);
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.ORDER_NOT_FOUND));
    }

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> searchOrders(SearchRequest request, Pageable pageable) {
        return orderRepository.searchOrders(request.getSearchType(), request.getText(), pageable);
    }

    @Override
    public Perfume getPerfumeById(Long perfumeId) {
        return perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.PERFUME_NOT_FOUND));
    }

    @Override
    @Transactional
    public MessageResponse editPerfume(PerfumeRequest perfumeRequest, MultipartFile file) {
        try {
            return savePerfume(perfumeRequest, file, SuccessMessage.PERFUME_EDITED);
        } catch (IOException e) {
            throw new RuntimeException("Error saving perfume", e);
        }
    }

    @Override
    @Transactional
    public MessageResponse addPerfume(PerfumeRequest perfumeRequest, MultipartFile file) {
        try {
            return savePerfume(perfumeRequest, file, SuccessMessage.PERFUME_ADDED);
        } catch (IOException e) {
            throw new RuntimeException("Error saving perfume", e);
        }
    }

    @Override
    public UserInfoResponse getUserById(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.USER_NOT_FOUND));
        Page<Order> orders = orderRepository.findOrderByUserId(userId, pageable);
        return new UserInfoResponse(user, orders);
    }

    private MessageResponse savePerfume(PerfumeRequest perfumeRequest, MultipartFile file, String message)
            throws IOException {
        Perfume perfume = modelMapper.map(perfumeRequest, Perfume.class);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            perfume.setFilename(resultFilename);
        }
        perfumeRepository.save(perfume);
        return new MessageResponse("alert-success", message);
    }
}
