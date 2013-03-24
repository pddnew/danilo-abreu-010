package br.com.internetbanking.service;

import java.sql.SQLException;
import java.util.Date;

import br.com.internetbanking.dao.PaymentDAO;
import br.com.internetbanking.exception.ServiceException;
import br.com.internetbanking.model.BankSlips;
import br.com.internetbanking.model.TransactionType;

public class ServicePayment {

	private PaymentDAO dao; 
	private final static TransactionType t;
	
	static {
		t = new TransactionType ();
		t.setIdTransactionType(2);
		t.setDescription("PAYMENT");
	}
	
	private ServicePayment(){
		dao = new PaymentDAO();				
	}
	
	public static ServicePayment getInstance(){
		return new ServicePayment();
	}
	
	public TransactionType getPaymentType(){
		return t;
	}
	
	public void save(BankSlips p) throws ServiceException{		
		try {
			if(p.getTransaction().getScheduleDate() == null)
				p.getTransaction().setExecuteDate(new Date());
				
			dao.save(p);
			
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}
}
