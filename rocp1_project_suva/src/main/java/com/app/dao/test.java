package com.app.dao;
import java.security.NoSuchAlgorithmException;
import com.app.models.*;
import com.app.utils.Hashing;
public class test {

	public static void main(String[] args) {
//		RoleDao r = new RoleDao(1);
////		System.out.print("hi");
//	Role r1 = r.findRoleById(2);
//	System.out.print(r1.getRole());
		Role r = new Role(1,"Admin");
		User u = new User("ss2", "s", "s", "s", "sss@gmail.com", r);
		UserDao ud = new UserDao(1);
		User u2 = ud.insert(u);
		User up = new User("s5", "s", "s", "s", "ss@gmail.com", r);
		//boolean t = ud.update(up);
	//System.out.println(u2.getEmail());
		//ud.delete(u);

	}

}
