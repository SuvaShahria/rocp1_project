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
import java.util.List;

public class AccountController {
	private static final AccountServices as = new AccountServices();

	public void insertAccount(HttpServletRequest request, HttpServletResponse response, int roleId)throws ServletException, IOException{
		
		Gson gson=new Gson();
		
		Account ac =gson.fromJson(request.getReader(), Account.class);
		//response.getWriter().println(gson.toJson(ac));
		HttpSession ses = request.getSession(false);
		if(ses != null) {
			if(roleId == 1 || roleId == 2 || ac.getUserId()== (int)ses.getAttribute("userid")) {
				//response.getWriter().println(gson.toJson(ac));
				Account ac2 = as.insertAccount(ac);
				if(ac2 != null) {
					response.getWriter().println(gson.toJson(ac2));
					response.setStatus(200);
				}else {
					String message = "{ \"message\": \"Failed to Update- Check ID\" }";
			    	JsonObject json2 = new Gson().fromJson(message, JsonObject.class);
			    	response.getWriter().println(json2);
				}
			}else {
				response.setStatus(401);		
				String message = "{ \"message\": \"The requested action is not permitted\" }";
		    	JsonObject json2 = new Gson().fromJson(message, JsonObject.class);
		    	response.getWriter().println(json2);
		    	return;
			}
		}
		
	}

	public void updateAccount(HttpServletRequest request, HttpServletResponse response, int roleId)throws ServletException, IOException {
Gson gson=new Gson();
		
		Account ac =gson.fromJson(request.getReader(), Account.class);
		//response.getWriter().println(gson.toJson(ac));
		HttpSession ses = request.getSession(false);
		System.out.println(ac.getUserId());
		System.out.println((int)ses.getAttribute("userid"));
		if(ses != null) {
			if(roleId == 1 || roleId == 2 || ac.getUserId()== (int)ses.getAttribute("userid")) {
				//response.getWriter().println(gson.toJson(ac));
				Account ac2 = as.updateAccount(ac,roleId);
				if(ac2 != null) {
					response.getWriter().println(gson.toJson(ac2));
					response.setStatus(200);
				}else {
					String message = "{ \"message\": \"Failed to Update- Check ID\" }";
			    	JsonObject json2 = new Gson().fromJson(message, JsonObject.class);
			    	response.getWriter().println(json2);
				}
			}else {
				response.setStatus(401);		
				String message = "{ \"message\": \"The requested action is not permitted\" }";
		    	JsonObject json2 = new Gson().fromJson(message, JsonObject.class);
		    	response.getWriter().println(json2);
		    	return;
			}
		}
		
	}

	public void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Gson gson=new Gson();
		Account act=gson.fromJson(request.getReader(), Account.class);
		//response.getWriter().println(gson.toJson(act));
		Account result = as.deleteUser(act);
		if(result != null) {
			response.setStatus(201);
			response.getWriter().println(gson.toJson(result));
		}else {
			response.setStatus(400);
			String message = "{ \"message\": \"Deletion Error - Check Input\" }";
	    	JsonObject json2 = new Gson().fromJson(message, JsonObject.class);
	    	response.getWriter().println(json2);
		}
		
	}

}
