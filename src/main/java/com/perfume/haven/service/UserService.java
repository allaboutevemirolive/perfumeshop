package com.perfume.haven.service;

import com.perfume.haven.domain.Order;
import com.perfume.haven.domain.User;
import com.perfume.haven.dto.request.ChangePasswordRequest;
import com.perfume.haven.dto.request.EditUserRequest;
import com.perfume.haven.dto.request.SearchRequest;
import com.perfume.haven.dto.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User getAuthenticatedUser();

    Page<Order> searchUserOrders(SearchRequest request, Pageable pageable);

    MessageResponse editUserInfo(EditUserRequest request);

    MessageResponse changePassword(ChangePasswordRequest request);
}
