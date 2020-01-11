package com.example.nCentrala.service;

import java.util.List;

import com.example.nCentrala.model.ScienceArea;

public interface ScienceAreaService {

	List<ScienceArea>  getAllAreas();
	
	ScienceArea getByName(String name);
}
