package com.example.nCentrala.converter;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.nCentrala.model.ScienceArea;
import com.example.nCentrala.model.dto.FormSubmissionDTO;
import com.example.nCentrala.model.dto.UserDTO;
import com.example.nCentrala.service.ScienceAreaService;

@Component
public class RegistrationFiledsToUserConverter implements Converter<List<FormSubmissionDTO>, UserDTO> {
	
	@Autowired
	private ScienceAreaService areaService;

	@Override
	public UserDTO convert(List<FormSubmissionDTO> source) {
		// TODO Auto-generated method stub
		if (source == null)
		{
			return null;
		}else
		{
			UserDTO user = new UserDTO();
			for(FormSubmissionDTO field : source)
			{
				if(field.getFieldId().equals("name"))
				{
					user.setName(field.getFieldValue());
					
				}else if(field.getFieldId().equals("surname"))
				{
					user.setSurname(field.getFieldValue());
					
				}else if(field.getFieldId().equals("email"))
				{
					user.setEmail(field.getFieldValue());
					
				}else if(field.getFieldId().equals("password"))
				{
					user.setPassword(field.getFieldValue());
					
				}else if(field.getFieldId().equals("password2"))
				{
					user.setPassword2(field.getFieldValue());
					
				}else if(field.getFieldId().equals("state"))
				{
					user.setState(field.getFieldValue());
					
				}else if(field.getFieldId().equals("city"))
				{
					user.setCity(field.getFieldValue());
					
				}else if(field.getFieldId().equals("username"))
				{
					user.setUsername(field.getFieldValue());
				}else if(field.getFieldId().equals("areas"))
				{
					
					user.setAreas(this.getAreas(field.getAreas()));
				}
			}
			
			return user;
		}	
	}
	
	private Set<ScienceArea> getAreas(ArrayList<String> areas)
	{
		Set<ScienceArea> ret = new HashSet<>();
		
		for(String area : areas)
		{
			System.out.println("Stigla: " + area);
			ret.add(areaService.getByName(area));
		}
		
		System.out.println("Ret.size()= " + ret.size());
		return ret;
	}

}
