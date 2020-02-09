package com.example.nCentrala.delegate;

import javax.servlet.http.HttpServletRequest;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.jwt.JwtProvider;

@Service
public class InitiatorListener implements ExecutionListener {

	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		execution.setVariable("initiator", this.getUsernameFromToken());
	}

	public String getUsernameFromToken() {
		
		String header = request.getHeader("authorization");
		
		if(header != null)
		{
			String parts[] = header.split(" "); 
			
			String username = jwtProvider.getUserNameFromJwtToken(parts[1]);
			
			return username;
		}else
		{
			return null;
		}
	}
}
