package com.app.dao;
import com.app.models.Role;
import com.app.models.User;
//import com.app.models.users.UserLoginDTO;
import com.app.dao.util.mySqlConnector;
import com.app.utils.Hashing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class UserDao {
	private static final RoleDao roledoa = RoleDao.getInstance();
	private static UserDao ud = new UserDao();
	private UserDao() {}
	public static UserDao getInstance() {
		return ud;
	}
	
	public UserDao(int x) {
		
	}
	public User insert2(User user) {

		try {
			user.setPassword(Hashing.getHash(user.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		try(Connection connector = mySqlConnector.getConnection()){
			String sql = "INSERT INTO users (user_id,username,pass_word,first_name,last_name,email,role_id_users) "+ "VALUES (?,?,?,?,?,?,?);";

			
			PreparedStatement statement = connector.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1,22);
			statement.setString(2,user.getUsername().trim());
			statement.setString(3,user.getPassword());
			statement.setString(4,user.getFirstName().trim());
			statement.setString(5,user.getLastName().trim());
			statement.setString(6,user.getEmail().trim().toLowerCase());
			statement.setInt(7,user.getRole().getRoleId());

			int succ = statement.executeUpdate();
			//System.out.println(succ);
			
			if(succ > 0) {
				ResultSet rs = statement.getGeneratedKeys();
				
				while(rs.next()) {		
					
					User u2 = new User();
					u2 = findById(rs.getInt(1));
					u2.setRole(user.getRole());
					return u2;
				}
			}else {
				return null;
			}
			
			
		}catch(SQLException e) {
			System.out.println(e);			
		}
		return null;
	}
	
	public User insert(User user) {

		try {
			user.setPassword(Hashing.getHash(user.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		try(Connection connector = mySqlConnector.getConnection()){
			String sql = "INSERT INTO users (username,pass_word,first_name,last_name,email,role_id_users) "+ "VALUES (?,?,?,?,?,?);";

			
			PreparedStatement statement = connector.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1,user.getUsername().trim());
			statement.setString(2,user.getPassword());
			statement.setString(3,user.getFirstName().trim());
			statement.setString(4,user.getLastName().trim());
			statement.setString(5,user.getEmail().trim().toLowerCase());
			statement.setInt(6,user.getRole().getRoleId());

			int succ = statement.executeUpdate();
			//System.out.println(succ);
			
			if(succ > 0) {
				ResultSet rs = statement.getGeneratedKeys();
				
				while(rs.next()) {		
					
					User u2 = new User();
					u2 = findById(rs.getInt(1));
					u2.setRole(user.getRole());
					return u2;
				}
			}else {
				return null;
			}
			
			
		}catch(SQLException e) {
			System.out.println(e);			
		}
		return null;
	}
	public boolean update(User user) {
		if(user.getUpdatePassword()!= null) {
			try {
				user.setPassword(Hashing.getHash(user.getUpdatePassword()));
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		
		
		try(Connection c = mySqlConnector.getConnection()){			
			String sql = "UPDATE users SET username = ?, pass_word = ?, first_name = ?,last_name = ?, email = ?, role_id_users = ? WHERE user_id = ?;";
		
		
			PreparedStatement statement = c.prepareStatement(sql);
			statement.setString(1,user.getUsername().trim());
			statement.setString(2,user.getPassword());
			statement.setString(3,user.getFirstName().trim());
			statement.setString(4,user.getLastName().trim());
			statement.setString(5,user.getEmail().trim().toLowerCase());
			statement.setInt(6,user.getRole().getRoleId());
			statement.setInt(7,user.getUserId()); //manual input fornow
			
			statement.executeUpdate();		
			
			return true;
						
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		return false;
	}
	
	public boolean delete(User u) {

		try(Connection connector = mySqlConnector.getConnection();){
		
			String sql ="DELETE "+ "FROM users "+"WHERE email = ?;";	
			PreparedStatement statement = connector.prepareStatement(sql);
			statement = connector.prepareStatement(sql);
			statement.setString(1,u.getEmail());
			
			statement.executeUpdate();
			return true;
							
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public User findById(int id) {
		
		try(Connection connector = mySqlConnector.getConnection()){
			String sql = "SELECT * FROM users WHERE user_id = ?";
			PreparedStatement statement = connector.prepareStatement(sql);
			statement.setInt(1,id);
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				User u = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("pass_word"),
						rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"),roledoa.findRoleById(rs.getInt("role_id_users")));
				return u;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}


}