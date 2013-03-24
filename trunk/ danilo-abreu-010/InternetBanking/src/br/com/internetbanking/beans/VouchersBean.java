package br.com.internetbanking.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.internetbanking.exception.ServiceException;
import br.com.internetbanking.model.Account;
import br.com.internetbanking.model.Transaction;
import br.com.internetbanking.service.ServiceVoucher;

@ManagedBean(name="vouchers")
@SessionScoped
public class VouchersBean {

	private Account account;	
	
	private Transaction transaction;
	private String clientFullName;

	private List<Transaction> lsTransactions;
	
	public VouchersBean(){
		super();
	
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)fc.getExternalContext()
											.getSession(false);
	
		account = (Account)session.getAttribute("account");
	}
	
	
	public String vouchers(){
		
		ServiceVoucher service = ServiceVoucher.getInstance();
		
		try {
			lsTransactions = service.getTransaction(account);
		} catch (ServiceException e) {
			e.printStackTrace();
			return "failed";
		}
		
		return "vouchers";
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public String getClientFullName() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(account.getClient().getFirstName());
		sb.append(" ");
		sb.append(account.getClient().getLastName());
		
		this.clientFullName = sb.toString().toUpperCase();
		
		return this.clientFullName;
	}


	public List<Transaction> getLsTransactions() {
		return lsTransactions;
	}


	public void setLsTransactions(List<Transaction> lsTransactions) {
		this.lsTransactions = lsTransactions;
	}
}
