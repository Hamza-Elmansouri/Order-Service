package com.ecomerce_data_model.orderservice.repository;

import com.ecomerce_data_model.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByAccountIdOrderByOrderDate(Long accountId);
}
