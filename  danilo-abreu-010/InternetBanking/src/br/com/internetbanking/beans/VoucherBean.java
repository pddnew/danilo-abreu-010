package br.com.internetbanking.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.internetbanking.model.Account;
import br.com.internetbanking.model.Transaction;


@ManagedBean(name="voucher")
@SessionScoped
public class VoucherBean {
	
	private Transaction transaction;
	
	private Account account;
	
	private String key;

	private String clientFullName;
	
	public VoucherBean(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)fc.getExternalContext()
											.getSession(false);
	
		account = (Account)session.getAttribute("account");
	}
	
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getClientFullName() {
		StringBuilder sb = new StringBuilder(account.getClient().getFirstName());
		sb.append(" ");
		sb.append(account.getClient().getLastName());
		this.clientFullName = sb.toString().toUpperCase();
		
		return clientFullName;
	}

	
	public boolean isPayment(){
		
		if(transaction != null)
			if(transaction.getTransactionType().getDescription().equals("PAYMENT"))
				return true;
		
		return false;
	}

	public String getKey() {
		key = "A" + transaction.hashCode() + ":" + transaction.getAmount();
		
		return "A" + key.hashCode() + "H"; 
	}
}
