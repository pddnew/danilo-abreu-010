package br.com.internetbanking.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.internetbanking.dao.StatementDAO;
import br.com.internetbanking.exception.ServiceException;
import br.com.internetbanking.model.Account;
import br.com.internetbanking.model.Statement;

public class ServiceStatement {
	
	private StatementDAO dao; 
	
	private ServiceStatement(){
		dao = new StatementDAO();		
	}
	
	public static ServiceStatement getInstance(){
		return new ServiceStatement();
	}
	
	public List<Statement> getAllByDate(Account a, Date since, Date to) throws ServiceException{
		
		try {
			return dao.getAllByDate(a, since, to);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
}
