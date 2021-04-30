package com.gab.cashonline.cashBE.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gab.cashonline.cashBE.domain.User;
import com.gab.cashonline.cashBE.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	UserService userService;
	
	@GetMapping("/{id}")
    public ResponseEntity<User> retrieveUser(@PathVariable long id) {
		
		try {
			User u = userService.findById(id);
			return ResponseEntity.ok().body(u);
		} catch (Exception e) {
			logger.error(String.format("Exception looking for user id: %d", id), e);
			return ResponseEntity.notFound().build();
		}
		
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
		
		try {
			userService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			logger.error(String.format("Exception trying to delete user id: %d", id), e);
			return ResponseEntity.notFound().build();
		}
		
    }
	
	@GetMapping("")
    public ResponseEntity<List<User>> retrieveAll() {
		
		try {
			List<User> users = userService.findAll();
			return ResponseEntity.ok().body(users);
		} catch (Exception e) {
			logger.error(String.format("Exception retrieving all users"), e);
			return ResponseEntity.noContent().build();
		}
		
    }
	
	@PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody(required = true) User user) {
		
		try {
		
			if(user.getId()!=null || 
					StringUtils.isEmpty(user.getFirstName()) ||
					StringUtils.isEmpty(user.getLastName()) ||
					StringUtils.isEmpty(user.getEmail())				
					) {
				logger.error(String.format("Bad Request trying to create user: %s", mapper.writeValueAsString(user)));
				return ResponseEntity.badRequest().build();
			}
			
			User created = userService.create(user);
			return ResponseEntity.ok().body(created);
		} catch (Exception e) {
			try {
				logger.error(String.format("Exception trying to create user: %s", mapper.writeValueAsString(user)), e);
			} catch (JsonProcessingException e1) {
				logger.error(String.format("Exception trying to deserialize user"), e1);
			}
			return ResponseEntity.badRequest().build();
		}
		
    }
}
