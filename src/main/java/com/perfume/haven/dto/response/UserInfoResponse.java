package com.perfume.haven.dto.response;

import com.perfume.haven.domain.Order;
import com.perfume.haven.domain.User;
import org.springframework.data.domain.Page;

public class UserInfoResponse {
    private User user;
    private Page<Order> orders;

    public UserInfoResponse(User user, Page<Order> orders) {
        this.user = user;
        this.orders = orders;
    }

    public User getUser() {
        return user;
    }

    public Page<Order> getOrders() {
        return orders;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrders(Page<Order> orders) {
        this.orders = orders;
    }
}
