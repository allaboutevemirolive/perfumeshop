package com.perfume.haven.service.impl;

import com.perfume.haven.constants.ErrorMessage;
import com.perfume.haven.constants.SuccessMessage;
import com.perfume.haven.domain.Order;
import com.perfume.haven.domain.User;
import com.perfume.haven.dto.request.ChangePasswordRequest;
import com.perfume.haven.dto.request.EditUserRequest;
import com.perfume.haven.dto.request.SearchRequest;
import com.perfume.haven.dto.response.MessageResponse;
import com.perfume.haven.repository.OrderRepository;
import com.perfume.haven.repository.UserRepository;
import com.perfume.haven.security.UserPrincipal;
import com.perfume.haven.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, OrderRepository orderRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getAuthenticatedUser() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(principal.getUsername());
    }

    @Override
    public Page<Order> searchUserOrders(SearchRequest request, Pageable pageable) {
        User user = getAuthenticatedUser();
        return orderRepository.searchUserOrders(user.getId(), request.getSearchType(), request.getText(), pageable);
    }

    @Override
    @Transactional
    public MessageResponse editUserInfo(EditUserRequest request) {
        User user = getAuthenticatedUser();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setCity(request.getCity());
        user.setAddress(request.getAddress());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPostIndex(request.getPostIndex());
        return new MessageResponse("alert-success", SuccessMessage.USER_UPDATED);
    }

    @Override
    @Transactional
    public MessageResponse changePassword(ChangePasswordRequest request) {
        if (request.getPassword() != null && !request.getPassword().equals(request.getPassword2())) {
            return new MessageResponse("passwordError", ErrorMessage.PASSWORDS_DO_NOT_MATCH);
        }
        User user = getAuthenticatedUser();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return new MessageResponse("alert-success", SuccessMessage.PASSWORD_CHANGED);
    }
}
