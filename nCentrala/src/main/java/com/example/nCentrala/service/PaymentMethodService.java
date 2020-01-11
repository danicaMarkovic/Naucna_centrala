package com.example.nCentrala.service;

import java.util.List;

import com.example.nCentrala.model.PaymentMethod;

public interface PaymentMethodService {

	List<PaymentMethod> getAllMethods();
	
	PaymentMethod getByName(String name);
	
}
