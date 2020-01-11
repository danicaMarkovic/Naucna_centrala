package com.example.nCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nCentrala.model.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

	PaymentMethod findByName(String name);
}
