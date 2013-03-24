package br.com.internetbanking.service;

import java.sql.SQLException;
import java.util.Date;

import br.com.internetbanking.dao.TransferDAO;
import br.com.internetbanking.exception.ServiceException;
import br.com.internetbanking.model.TransactionType;
import br.com.internetbanking.model.Transfer;

public class ServiceTransfer {

	private TransferDAO dao;
	
	private final static TransactionType t;
	
	static{
		t = new TransactionType();
		t.setIdTransactionType(1);
		t.setDescription("TRANSFER");		
	}
	
	private ServiceTransfer (){
		dao = new TransferDAO();
	}
	
	public static ServiceTransfer getInstance(){
		return new ServiceTransfer ();
	}
	
	public TransactionType getTranferType(){		
		return t;
	}
	
	public void save(Transfer transfer) throws ServiceException{
		try {
			if(transfer.getTransaction().getScheduleDate() == null)
				transfer.getTransaction().setExecuteDate(new Date());
			
			dao.save(transfer);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
}
