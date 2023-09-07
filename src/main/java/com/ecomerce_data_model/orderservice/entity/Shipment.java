package com.ecomerce_data_model.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Account account;
    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    private List<OrderLine> orderLineItems;
    @ManyToOne
    private Address shippingAddress;
    private Date shippedDate;
    private Date deliveryDate;



}
