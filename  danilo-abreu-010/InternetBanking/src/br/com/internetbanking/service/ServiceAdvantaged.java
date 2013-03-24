package br.com.internetbanking.service;

import java.sql.SQLException;
import java.util.List;

import br.com.internetbanking.dao.AdvantagedDAO;
import br.com.internetbanking.exception.ServiceException;
import br.com.internetbanking.model.Account;
import br.com.internetbanking.model.AdvantagedAccount;

public class ServiceAdvantaged {
	
	private AdvantagedDAO dao;
	
	private ServiceAdvantaged (){
		super();		
		dao = new AdvantagedDAO();
	}

	public static ServiceAdvantaged getInstance(){
		return new ServiceAdvantaged();
	} 
	
	public void save(AdvantagedAccount advAccount) throws ServiceException{
		try {
			dao.save(advAccount);
		} catch (SQLException e) {
			throw new ServiceException(e.getMessage());
		}		
	}
	
	public List<AdvantagedAccount> getAdvantagedList(Account account) throws ServiceException{
		try {
			return dao.getByAccount(account);
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}		
	}
	
	public void delete(AdvantagedAccount advAccount) throws ServiceException{
		try {
			dao.delete(advAccount);
		} catch (SQLException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
