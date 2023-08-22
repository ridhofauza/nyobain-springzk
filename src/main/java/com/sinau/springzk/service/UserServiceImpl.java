package com.sinau.springzk.service;

import java.util.ArrayList;

import com.sinau.springzk.dao.User;
import com.sinau.springzk.repository.UserRepositoryImpl;

public class UserServiceImpl implements UserService {
	
	private UserRepositoryImpl userRepository;
	
	public ArrayList<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Integer id) {
		return userRepository.findById(id);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User update(Integer id, User user) { 
		return userRepository.update(id, user);
	}

	public User delete(Integer id) {
		return userRepository.delete(id);
	}
	
	public void setUserRepository(UserRepositoryImpl userRepository) {
		this.userRepository = userRepository;
	}

}
