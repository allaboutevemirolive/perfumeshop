package com.perfume.haven.service;

import com.perfume.haven.domain.Order;
import com.perfume.haven.domain.Perfume;
import com.perfume.haven.domain.User;
import com.perfume.haven.dto.request.OrderRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Order getOrder(Long orderId);

    List<Perfume> getOrdering();

    Page<Order> getUserOrdersList(Pageable pageable);

    Long postOrder(User user, OrderRequest orderRequest);
}
