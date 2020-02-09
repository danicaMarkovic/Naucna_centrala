package com.example.nCentrala.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.nCentrala.jwt.JwtProvider;
import com.example.nCentrala.jwt.JwtResponse;
import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.Editor;
import com.example.nCentrala.model.Journal;
import com.example.nCentrala.model.Reviewer;
import com.example.nCentrala.model.Role;
import com.example.nCentrala.model.RoleName;
import com.example.nCentrala.model.ScienceArea;
import com.example.nCentrala.model.User;
import com.example.nCentrala.model.dto.FormFieldsDTO;
import com.example.nCentrala.model.dto.FormSubmissionDTO;
import com.example.nCentrala.model.dto.LoginInfoDTO;
import com.example.nCentrala.model.dto.UserDTO;
import com.example.nCentrala.model.dto.UserTaskFormDTO;
import com.example.nCentrala.repository.UserRepository;
import com.example.nCentrala.service.ArticleService;
import com.example.nCentrala.service.JournalService;
import com.example.nCentrala.service.RoleService;
import com.example.nCentrala.service.UserService;


@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:1337")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JournalService journalService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private FormService formService;	
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private UserRepository userRep;
	
	@Autowired
	private ArticleService articleService;

	
	@RequestMapping(
			value="login",
			method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginInfoDTO loginInfo) {

		Optional<User> user = userService.findUserByUsername(loginInfo.getUsername());
		
		if(user.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else if(!user.get().isActivated())
		{
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}else
		{
			
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                		loginInfo.getUsername(),
	                		loginInfo.getPassword()
	                )
	        );

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String jwt = jwtProvider.generateJwtToken(authentication);
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        
	        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(),userDetails.getAuthorities()));
	        
		}
        
    }
	
	@RequestMapping(
			value = "getUser/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getUserByUserName(@PathVariable("id") String username){
		
		Optional<User> user = userService.findUserByUsername(username);
		
		if(user == null)
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else
		{
			return new ResponseEntity<>(new UserDTO(user.get()), HttpStatus.OK);
		}
		
		
	}
	
	
	@RequestMapping(
			value = "getByRole/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<User>> getUserByRole(@PathVariable("id") String name) {
		
		List<User> list = new ArrayList<>();
		List<User> ret = new ArrayList<>();
		Journal lastAdded = journalService.getLastAddedJournal();
		
		if(name.equals("reviewer"))
		{
			list = userService.getUserByRole(RoleName.ROLE_REVIEWER);
			ret = this.getReviewersWithArea(list, lastAdded);
			
		}else if(name.equals("editor"))
		{
			list = userService.getUserByRole(RoleName.ROLE_EDITOR);
			ret = this.getEditorsWithAreaAndWithoutJournal(list, lastAdded);
			
		}else if(name.equals("autor"))
		{
			list = userService.getUserByRole(RoleName.ROLE_AUTHOR);
		}
		
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "getReviewers",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<Reviewer>> getByRoleAndArea() {
		
		Article article = articleService.getLastInsertedArticle();
		
		List<Reviewer> users = userRep.findAllByJournalReview_Id(article.getJournal().getId());
		

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	private List<User> getReviewersWithArea(List<User> users, Journal journal)
	{
		List<User> ret = new ArrayList<>();
		
		
		for(User u : users)
		{
			for(ScienceArea a : u.getAreasOfInterest())
			{
				for(ScienceArea areaJournal : journal.getScienceAreas())
				{
					if(a.getName().equals(areaJournal.getName()))
					{
						ret.add(u);
					}
				}
			}
		}
		
		return ret;
	}
	
	private List<User> getEditorsWithAreaAndWithoutJournal(List<User> users, Journal journal){
		
		List<User> ret = new ArrayList<>();
		
		String username = jwtProvider.getUsernameLoggedUser();
		
		for(User u : users)
		{
			Editor e = (Editor) u;
			
			if(e.getJournal() == null && !e.getUsername().equals(username)) 
			{
				for(ScienceArea a : u.getAreasOfInterest())
				{
					for(ScienceArea area : journal.getScienceAreas())
					{
						if(a.getName().equals(area.getName()))
						{
							ret.add(u);
						}
					}
				}
			}
			
			
		}
		
		return ret;
	}
	
	 
	
}
