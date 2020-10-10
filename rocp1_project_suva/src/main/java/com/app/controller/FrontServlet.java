package com.app.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FrontServlet
 */
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count;
	private static final LoginController login = new LoginController();
	private static final UserController userController = new UserController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("in doGet"); 
		entry(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		entry(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		entry(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		entry(request, response);
	}
	protected void entry(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(404);
		int roleId = 0;
		
		HttpSession ses = request.getSession(false);
		if(ses != null) {
			roleId = (int) ses.getAttribute("roleId");
			
		}
		String url = request.getRequestURI().replace("/rocp1_project/", "");
		String method = request.getMethod();
		String[] urlSplit = url.split("/");
//		System.out.println("url is: "+url);
//		System.out.println(method);				
//		System.out.println("Url "+Arrays.toString(urlSplit));
//		System.out.println("url length is "+urlSplit.length);
//		System.out.println("Url "+Arrays.toString(urlSplit));
//		for(int i = 0; i <urlSplit.length; i++) {
//			System.out.println(urlSplit[i]);
//		}
//		System.out.println("-------"+urlSplit[0]);
		switch(urlSplit[0]) {
		case "login":
			if (method.equals("POST")) {
				
				login.login(request, response);
			}else {
				response.getWriter().println("Not supported method");			
			}
			System.out.println("login");
			break;
		case "logout":
			System.out.println("logout");
			break;
		case "users":
			if (method.equals("POST")) {
				if(roleId == 1) {
					userController.register(request, response);
				}else {
					response.getWriter().println("Only admin can access");		
				}
			}
			
			System.out.println("users");
			break;
		default:
			System.out.println("default");
			break;
		}
		
		
		
		
	}

}