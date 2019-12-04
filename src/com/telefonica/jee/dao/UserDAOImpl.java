package com.telefonica.jee.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.telefonica.jee.model.User;
import com.telefonica.jee.util.JPAUtil;

public class UserDAOImpl implements UserDAO {
	
	
	private static final String FINDBYEMAIL = "SELECT u from User u WHERE u.email = :email";
	public UserDAOImpl() {
	}

	EntityManager manager;

	@Override
	public List<User> get() {
		
		try {
			manager = JPAUtil.getEntityManager();
			TypedQuery<User> namedQuery = manager.createNamedQuery("User.findAll", User.class);
			List<User> results = namedQuery.getResultList();
			manager.close();
			return results;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<User>();
	}

	@Override
	public User get(int id) {
		User user = null;
		try {
			manager = JPAUtil.getEntityManager();
			user = manager.find(User.class, id);
			manager.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean save(User user) {
		boolean flag = false;
		try {
			manager = JPAUtil.getEntityManager();
			manager.getTransaction().begin();   
			manager.persist(user); 
			manager.getTransaction().commit(); 
			manager.close();
			flag = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delete(int id) {
		boolean flag = false;
		try {
			manager = JPAUtil.getEntityManager();
			manager.getTransaction().begin();   
			User user = manager.find(User.class, id);
			if (user != null) {
				manager.remove(user);
				manager.getTransaction().commit(); 
				flag = true;
			}
			manager.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean update(User user) {
		boolean flag = false;
		try {
			manager = JPAUtil.getEntityManager();
			manager.getTransaction().begin();   
			manager.merge(user); 
			manager.getTransaction().commit(); 
			manager.close();
			flag = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public boolean login(String email, String password) {
		
		TypedQuery<Long> query = manager.createQuery(FINDBYEMAIL, Long.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		Long numUsuario = query.getSingleResult();
		System.out.println("Numero de usuarios con email y password: " + numUsuario);
		if (numUsuario > 0) {
			return true;
		}
		
		return false;
		
		//return: numUsuario > 0;
	}

	@Override
	public boolean login(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public org.apache.tomcat.jni.User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
