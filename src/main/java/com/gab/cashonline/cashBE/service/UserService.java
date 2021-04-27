package com.gab.cashonline.cashBE.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gab.cashonline.cashBE.domain.User;
import com.gab.cashonline.cashBE.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User findById(long id) {
		return userRepository.findById(id).get();
	}

	public User create(User user) {
		return userRepository.saveAndFlush(user);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public void delete(long id) {
		User user = this.findById(id);
		userRepository.delete(user);
	}
	
}
