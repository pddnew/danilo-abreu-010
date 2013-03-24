package br.com.internetbanking.service;

import java.sql.SQLException;
import java.util.List;

import br.com.internetbanking.dao.VoucherDAO;
import br.com.internetbanking.exception.ServiceException;
import br.com.internetbanking.model.Account;
import br.com.internetbanking.model.Transaction;

public class ServiceVoucher {

	private VoucherDAO dao;
	
	private ServiceVoucher(){
		dao = new VoucherDAO();
	}
	
	public static ServiceVoucher getInstance(){
		return new ServiceVoucher();
	}
	
	public List<Transaction> getTransaction(Account account) throws ServiceException{				
		try {
			return dao.getAllById(account);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
}
