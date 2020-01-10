package com.example.nCentrala.converter;


import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.nCentrala.model.dto.FormSubmissionDTO;
import com.example.nCentrala.model.dto.UserDTO;

@Component
public class RegistrationFiledsToUserConverter implements Converter<List<FormSubmissionDTO>, UserDTO> {

	@Override
	public UserDTO convert(List<FormSubmissionDTO> source) {
		// TODO Auto-generated method stub
		System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
		if (source == null)
		{
			System.out.println("Ovo je nuuuulll");
			return null;
		}else
		{
			UserDTO user = new UserDTO();
			System.out.println("Nije null");
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
				}
			}
			
			return user;
		}	
	}

}
