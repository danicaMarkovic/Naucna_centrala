package com.example.nCentrala.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.nCentrala.model.dto.FormSubmissionDTO;
import com.example.nCentrala.service.PaymentMethodService;
import com.example.nCentrala.service.ScienceAreaService;
import com.example.nCentrala.model.Journal;
import com.example.nCentrala.model.PaymentMethod;
import com.example.nCentrala.model.ScienceArea;

@Component
public class JournalFormToJournalConverter implements Converter<List<FormSubmissionDTO>, Journal> {
	
	@Autowired
	private ScienceAreaService areaService;
	
	@Autowired
	private PaymentMethodService paymentService;
	
	@Override
	public Journal convert(List<FormSubmissionDTO> source) {
		// TODO Auto-generated method stub
		 if (source == null) {
			 
			 return null;
		 }else
		 {
			 Journal journal =  new Journal();
			 for(FormSubmissionDTO field : source)
			 {
				 if (field.getFieldId().equals("name"))
				 {
					 journal.setName(field.getFieldValue());
					 
				 }else if(field.getFieldId().equals("issn"))
				 {
					 journal.setIssn(field.getFieldValue());
					 
				 }else if(field.getFieldId().equals("openAccess"))
				 {
					 if(field.getFieldValue().equals("true"))
					 {
						 journal.setOpenAccess(true);
					 }else
					 {
						 journal.setOpenAccess(false);
					 }
					 
				 }else if(field.getFieldId().equals("scienceArea"))
				 {
					 journal.setScienceAreas(this.getAreas(field.getAreas()));
					 
				 }else if(field.getFieldId().equals("paymentMethod"))
				 {
					 
					 journal.setPaymentMethods(this.getMethods(field.getMethods()));
					 
				 }
				 
			 }
			 
			 return journal;
			 
		 }

			 
	}
	
	private List<ScienceArea> getAreas(ArrayList<String> areas)
	{
		List<ScienceArea> ret = new ArrayList<>();
		
		for(String area : areas)
		{
			ret.add(areaService.getByName(area));
		}
		
		return ret;
	}
	
	private List<PaymentMethod> getMethods(ArrayList<String> methods)
	{
		
		List<PaymentMethod> ret = new ArrayList<>();
		
		for(String method : methods)
		{
			ret.add(paymentService.getByName(method));
		}
		
		return ret;
		
	}

	

}
