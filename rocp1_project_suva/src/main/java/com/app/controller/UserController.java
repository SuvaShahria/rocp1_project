package com.app.controller;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.app.models.*;
import com.app.services.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;

public class UserController {
	private static final UserService userService = new UserService();
	public void register(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Gson gson=new Gson();
		User user=gson.fromJson(request.getReader(), User.class);
		//response.getWriter().println(gson.toJson(user));
		
		if(userService.findByEmail(user.getEmail())!=null ||  userService.findByUsername(user.getUsername())!=null ) {
			response.setStatus(400);
			
			String message = "{ \"message\": \"Invalid fields\" }";
	    	JsonObject json2 = new Gson().fromJson(message, JsonObject.class);
	    	response.getWriter().println(json2);
		}else {
			User u = userService.register(user);
			if(u != null) {
				response.setStatus(201);
				response.getWriter().println(gson.toJson(u));
			}
		}
		
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Gson gson=new Gson();
		User user=gson.fromJson(request.getReader(), User.class);
		
		boolean result = userService.deleteUser(user);
		if(result) {
			response.setStatus(201);
			response.getWriter().println(gson.toJson(user));
		}else {
			response.setStatus(400);
			String message = "{ \"message\": \"Deletion Error - Check Input\" }";
	    	JsonObject json2 = new Gson().fromJson(message, JsonObject.class);
	    	response.getWriter().println(json2);
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response, int roleId)throws ServletException, IOException {
		
		Gson gson=new Gson();
		User user=gson.fromJson(request.getReader(), User.class);
		HttpSession ses = request.getSession(false);
		if(ses != null) {
			if(roleId ==1 || user.getUserId() == (int)ses.getAttribute("userid")) {
				System.out.println("pass");
			}
		}
		
		
	}

}
