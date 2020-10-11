package com.app.dao;
import com.app.models.*;
import com.app.dao.util.mySqlConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class AccountDao {
	
	private static final AccountDao adao = new AccountDao();
	private static final AccountTypeDao atdao = AccountTypeDao.getInstance();
	private static final AccountStatusDao asdao = AccountStatusDao.getInstance();
	private static final UserDao udao = UserDao.getInstance();
	
	private AccountDao() {}
	public static AccountDao getInstance() {
		return adao;
	}

}
