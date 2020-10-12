package com.app.services;

import java.util.List;

import com.app.dao.*;
import com.app.models.*;

public class AccountServices {

	private static final RoleDao rdao = RoleDao.getInstance();
	private static final UserDao udao = UserDao.getInstance();
	private static final AccountDao acdao = AccountDao.getInstance();
	private static final AccountStatusDao asdao = AccountStatusDao.getInstance();
	private static final AccountTypeDao atdao = AccountTypeDao.getInstance();
	
	public Account insertAccount(Account ac) {
		
		ac.setStatus(asdao.findById(ac.getStatusId()));
		ac.setType(atdao.findById(ac.getTypeId()));
		Account ac2 = acdao.insert(ac);
		if(ac2 != null) {
			ac2.setStatusId(ac.getStatusId());
			ac2.setTypeId(ac.getTypeId());
		}
		return ac2;
		
	}

	public Account updateAccount(Account ac,int roleId) {
		ac.setStatus(asdao.findById(ac.getStatusId()));
		ac.setType(atdao.findById(ac.getTypeId()));
		Account ac2 = acdao.update(ac,roleId);
		if(ac2 != null) {
			ac2.setStatusId(ac.getStatusId());
			ac2.setTypeId(ac.getTypeId());
		}
		return ac2;
		
	}

	public Account deleteUser(Account ac) {
		//System.out.println("in service" +ac.getAccountId());

		ac.setStatus(asdao.findById(ac.getStatusId()));
		ac.setType(atdao.findById(ac.getTypeId()));
		Account ac2 = acdao.delete(ac);
		if(ac2 != null) {
			ac2.setStatusId(ac.getStatusId());
			ac2.setTypeId(ac.getTypeId());
		}
		return ac2;
	}

}
