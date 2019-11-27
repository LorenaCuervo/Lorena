package com.telefonica.jee.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.telefonica.jee.dao.UserDAO;
import com.telefonica.jee.dao.UserDAOImpl;
import com.telefonica.jee.model.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	RequestDispatcher dispatcher = null;
	
	UserDAO userDAO = new UserDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getParameter("action");
		
		if(action == null) {
			action = "LIST";
		}
		
		switch(action) {
			
			case "LIST":
				listUser(request, response);
				break;
				
			case "EDIT":
				getSingleUser(request, response);
				break;
				
			case "DELETE":
				deleteUser(request, response);
				break;
				
			default:
				listUser(request, response);
				break;
				
		}
		
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
	
		if(userDAO.delete(Integer.parseInt(id))) {
			request.setAttribute("NOTIFICATION", "User Deleted Successfully!");
		}
		
		listUser(request, response);
	}
	
private void getSingleUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String id = request.getParameter("id");
		
		User theUser = userDAO.get(Integer.parseInt(id));
		
		request.setAttribute("user", theUser);
		
		dispatcher = request.getRequestDispatcher("/views/user-form.jsp");
		
		dispatcher.forward(request, response);
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> theList = userDAO.get();
		
		request.setAttribute("list", theList);
		
		dispatcher = request.getRequestDispatcher("/views/user-list.jsp");
		
		dispatcher.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setSurname(request.getParameter("surname"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setAge(request.getParameter("age"));
		
		if(id.isEmpty() || id == null) {
			
			if(userDAO.save(user)) {
				request.setAttribute("NOTIFICATION", "User Saved Successfully!");
			}
		
		}else {
			
			user.setId(Integer.parseInt(id));
			if(userDAO.update(user)) {
				request.setAttribute("NOTIFICATION", "User Updated Successfully!");
			}
			
		}
		
		listUser(request, response);
	}

}
