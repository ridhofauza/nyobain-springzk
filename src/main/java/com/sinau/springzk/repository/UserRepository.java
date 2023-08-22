package com.sinau.springzk.repository;

import java.util.ArrayList;

import com.sinau.springzk.dao.User;

public interface UserRepository {
	public ArrayList<User> findAll();
	public User findById(Integer id);
	public User save(User user);
	public User update(Integer id, User user);
	public User delete(Integer id);
}
