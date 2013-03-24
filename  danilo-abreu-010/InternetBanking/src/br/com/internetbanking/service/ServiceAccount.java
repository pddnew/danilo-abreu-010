package br.com.internetbanking.service;

import java.sql.SQLException;

import br.com.internetbanking.dao.AccountDAO;
import br.com.internetbanking.exception.ServiceException;
import br.com.internetbanking.model.Account;

public class ServiceAccount {

	private AccountDAO dao;

	private ServiceAccount(){
		super();
		dao = new AccountDAO();
	}
	
	public static ServiceAccount getInstance(){
		return new ServiceAccount();
	}
	
	public Account getAccountByNumber(String number) throws ServiceException{
		try {
			return dao.getByNumber(number);
		} catch (SQLException e) {
			throw new ServiceException("");
		}
	}
}
