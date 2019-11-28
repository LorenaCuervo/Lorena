package com.telefonica.jee.util;

public class AppConstants {

	public static final String SESSION_USER = "user";

	public static final String PREFIX = "/views/";

	public static final String HOME_PAGE = PREFIX + "todo-list.jsp";

	public static final String LOGIN_PAGE = PREFIX + "login.jsp";

	public static final String CREATE_TODO_PAGE = PREFIX + "todo-create.jsp";

	public static final String SEARCH_PAGE = PREFIX + "/todo/search.jsp";

	public static final String UPDATE_TODO_PAGE = PREFIX + "todo-create.jsp";

	public static final String ERROR_PAGE = PREFIX + "/error.jsp";

	public static final String REDIRECT_HOME_PAGE = "/todos";
	
	public static final String LOGIN_ERROR = "Bad credentials";

	public static final String ACTION_CREATE = "c";
	
	public static final String ACTION_RETRIEVE = "r";
	
	public static final String ACTION_UPDATE = "u";

	public static final String ACTION_DELETE = "d";

	
}

