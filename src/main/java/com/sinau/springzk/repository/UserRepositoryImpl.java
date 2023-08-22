package com.sinau.springzk.repository;

import java.util.ArrayList;
import com.sinau.springzk.dao.User;

public class UserRepositoryImpl implements UserRepository {

	private ArrayList<User> users = new ArrayList<User>();
	
	public UserRepositoryImpl() {
		buildUsers();
	}
	
	public void buildUsers() {		
		users.add(new User(1, "Ali", "Male"));
		users.add(new User(2, "Bima", "Male"));
		users.add(new User(3, "Cika", "Female"));
		users.add(new User(4, "Dinda", "Female"));
	}
	
	public ArrayList<User> findAll() {
		return users;
	}

	public User findById(Integer id) {
		for(User checkUser : users) {
			if(checkUser.getId() == id) {
				return checkUser;
			}
		}
		return new User();
	}

	public User save(User user) {
		users.add(user);
		return user;
	}

	public User update(Integer id, User newUser) {
		User oldUser = findById(id);
		Integer index = users.indexOf(oldUser);
		users.set(index, newUser);
		return users.get(index);
	}

	public User delete(Integer id) {
		User user = findById(id);
		users.remove(user);
		return user;
	}

}
