package com.app.services;

import java.util.List;

import com.app.dao.*;
import com.app.models.*;


public class UserService {
	

	private static final RoleDao rdao = RoleDao.getInstance();
	private static final UserDao udao = UserDao.getInstance();
	
	public User login(String username, String password) {
		
		return udao.login(username,password);		
		
	}

	public User InsertUser(User user) {
		Role role = rdao.findRoleById(user.getRoleID());
		user.setRole(role);				
		return udao.insert(user);
		
	}	
	
	public boolean deleteUser(User user) {
		
		
		
		
		return udao.delete(user);		
		
	}
	
	public User register(User user) {
		Role role = rdao.findRoleById(user.getRoleID());
		user.setRole(role);
		return udao.insert(user);
		
		
	}
		

	
	public User findById(int id) {
		
		return udao.findById(id);
		
	}
	public User findByEmail(String e) {
		return udao.findByEmail(e);
	}
	public User findByUsername(String u) {
		return udao.findByUsername(u);
	}
	
//	public User updateUser(UserDTO udto, int roleId, int authUserId) {
//				
//		User user = udao.findById(udto.userId);
//		boolean isChanging = false;
//		
//		// Update fields with new data if not null	
//		if(roleId == 1 || roleId == 2 || authUserId == udto.userId) {
//			if(udto.email != null && udto.email != "" && !user.getEmail().equals(udto.email)) {
//				user.setEmail(udto.email);
//				isChanging = true;
//			}
//			if(udto.newPassword != null && udto.newPassword.length() > 3) {
//				user.setPasswordNew(udto.newPassword);
//				isChanging = true;
//			}			
//		}
//		
//		// Update the following fields if Admin only		
//		if(roleId == 1) {
//			if(udto.firstName != null && udto.firstName != "" && !user.getFirstName().equals(udto.firstName)) {
//				user.setFirstName(udto.firstName);
//				isChanging = true;
//			}
//			if(udto.lastName != null && udto.lastName != "" && !user.getLastName().equals(udto.lastName)) {
//				user.setLastName(udto.lastName);	
//				isChanging = true;
//			}
//			if(udto.roleId > 0 && user.getRole().getRoleId() != udto.roleId) {
//				Role userRole = rdao.findById(udto.roleId);
//				user.setRole(userRole);
//				isChanging = true;
//			}		
//		}
//		
//		if(isChanging) {
//			if(udao.update(user)) {
//				return udao.findById(udto.userId);
//			}else {
//				return null;
//			}
//		}
//				
//		return null;				
//	}
	
}