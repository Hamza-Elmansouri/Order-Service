package com.ecomerce_data_model.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private Long accountId;
    private Long orderNumber;
    @ManyToOne
    private Account account;
    private LocalDate orderDate;
    @OneToOne(cascade = CascadeType.ALL)
    private Address shippingAddress;
    @ManyToOne
    private OrderLine orderLineItems;
    private Long totalPrice;
    @OneToMany
    private List<Shipment> shipments;

}
