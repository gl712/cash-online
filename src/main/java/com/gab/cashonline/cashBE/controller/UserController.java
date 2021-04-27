package com.gab.cashonline.cashBE.controller;

import java.util.List;

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

import com.gab.cashonline.cashBE.domain.User;
import com.gab.cashonline.cashBE.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/{id}")
    public ResponseEntity<User> retrieveUser(@PathVariable long id) {
		
		try {
			User u = userService.findById(id);
			return ResponseEntity.ok().body(u);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
		
		try {
			userService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
    }
	
	@GetMapping("")
    public ResponseEntity<List<User>> retrieveAll() {
		
		try {
			List<User> users = userService.findAll();
			return ResponseEntity.ok().body(users);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
    }
	
	@PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody(required = true) User user) {
		
		if(user.getId()!=null || 
				StringUtils.isEmpty(user.getFirstName()) ||
				StringUtils.isEmpty(user.getLastName()) ||
				StringUtils.isEmpty(user.getEmail())				
				) {
			return ResponseEntity.badRequest().build();
		}
		
		try {
			User created = userService.create(user);
			return ResponseEntity.ok().body(created);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
    }
}
