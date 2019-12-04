package com.telefonica.jee.dao;

import java.util.List;

import com.telefonica.jee.model.User;

public interface UserDAO {
	
	/**
	 * It returns a list of all users
	 * @return
	 */
	List<User> get();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	User get(int id);
	
	/**
	 * It sves an User
	 * @param employee
	 * @return true if user is correctly saved or false if there is an error
	 */
	boolean save(User user);
	
	/**
	 * 
	 * @param id
	 * @return 
	 */
	boolean delete(int id);
	
	boolean update(User user);

	public boolean login(String email);

	boolean login(String email, String password);

	org.apache.tomcat.jni.User findByEmail(String email);



}
