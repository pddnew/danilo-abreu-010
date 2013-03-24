package br.com.internetbanking.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.internetbanking.exception.ServiceException;
import br.com.internetbanking.model.Account;
import br.com.internetbanking.model.BankSlips;
import br.com.internetbanking.model.Transaction;
import br.com.internetbanking.service.ServicePayment;

@ManagedBean(name= "payment")
@SessionScoped
public class PaymentBean implements Serializable{

	/** 
	 * serial Version UID
	 */
	private static final long serialVersionUID = 8483882637629871137L;
	
	private String typedCardPassword;
	
	private Account account;	
	private Transaction transaction;
	private BankSlips bankSlip;

	private boolean isToday  = true;
	
	public PaymentBean() {
		super();
		
		init();
	}
	
	private void init(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)fc.getExternalContext()
												.getSession(false);
		
		account = (Account)session.getAttribute("account");		
		transaction = new Transaction();
		transaction.setTransactionType(ServicePayment.getInstance().getPaymentType());
		transaction.setAccount(account);
		bankSlip = new BankSlips();
		bankSlip.setTransaction(transaction);						
	}
	
	public String doPayment(){
		if(account.getCardPassword().equals(this.typedCardPassword)){
			
			ServicePayment service = ServicePayment.getInstance();
			
			try {
				service.save(bankSlip);
				
				if(isToday)
					account.setBalance(account.getBalance()-bankSlip.getTransaction().getAmount());
				
				return "voucher";
			} catch (ServiceException e) {				
				e.printStackTrace();
			}
		}
		
		return "failed";
		
	}
	
	public String payment(){
			
		init();
		
		return "payment";
	}

	public String getTypedCardPassword() {
		return typedCardPassword;
	}

	public void setTypedCardPassword(String typedCardPassword) {
		this.typedCardPassword = typedCardPassword;
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

	public BankSlips getBankSlip() {
		return bankSlip;
	}

	public void setBankSlip(BankSlips bankSlip) {
		this.bankSlip = bankSlip;
	}

	public boolean isToday() {
		return isToday;
	}

	public void setToday(boolean isToday) {
		this.isToday = isToday;
	}
	
	public boolean isCurrent(){
		if(this.account.getAccountType().getCode().equals("500"))
			return false;
		else
			return true;
	}
}
