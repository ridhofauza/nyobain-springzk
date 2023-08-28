package com.sinau.springzk.repository;

import java.util.ArrayList;
import com.sinau.springzk.dao.User;
import com.sinau.springzk.utils.GenderEnum;

public class UserRepositoryImpl implements UserRepository {

	private ArrayList<User> users = new ArrayList<User>();
	
	public UserRepositoryImpl() {
		buildUsers();
	}
	
	public void buildUsers() {		
		users.add(new User(1, "Ali", GenderEnum.MALE.getValue()));
		users.add(new User(2, "Bima", GenderEnum.MALE.getValue()));
		users.add(new User(3, "Cika", GenderEnum.FEMALE.getValue()));
		users.add(new User(4, "Dinda", GenderEnum.FEMALE.getValue()));
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
