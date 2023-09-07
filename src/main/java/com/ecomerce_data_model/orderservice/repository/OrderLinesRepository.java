package com.ecomerce_data_model.orderservice.repository;

import com.ecomerce_data_model.orderservice.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLinesRepository extends JpaRepository<OrderLine, Long> {
}
