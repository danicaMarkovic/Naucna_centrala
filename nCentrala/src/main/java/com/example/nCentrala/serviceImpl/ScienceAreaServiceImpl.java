package com.example.nCentrala.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.ScienceArea;
import com.example.nCentrala.repository.ScienceAreaRepository;
import com.example.nCentrala.service.ScienceAreaService;

@Service
public class ScienceAreaServiceImpl implements ScienceAreaService {
	
	@Autowired
	private ScienceAreaRepository repository;

	@Override
	public List<ScienceArea> getAllAreas() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public ScienceArea getByName(String name) {
		// TODO Auto-generated method stub
		return repository.findByName(name);
	}

}
