package com.example.nCentrala.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.PaymentMethod;
import com.example.nCentrala.repository.PaymentMethodRepository;
import com.example.nCentrala.service.PaymentMethodService;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
	
	@Autowired
	private PaymentMethodRepository repository;

	@Override
	public List<PaymentMethod> getAllMethods() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public PaymentMethod getByName(String name) {
		// TODO Auto-generated method stub
		return repository.findByName(name);
	}

}
