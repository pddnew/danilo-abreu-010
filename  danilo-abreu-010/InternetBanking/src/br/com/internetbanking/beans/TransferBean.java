package br.com.internetbanking.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.internetbanking.exception.ServiceException;
import br.com.internetbanking.model.Account;
import br.com.internetbanking.model.AdvantagedAccount;
import br.com.internetbanking.model.Transaction;
import br.com.internetbanking.model.Transfer;
import br.com.internetbanking.service.ServiceTransfer;

@ManagedBean (name= "transfer") 
@SessionScoped
public class TransferBean implements Serializable {

	private static final long serialVersionUID = -6408460876450230200L;

	private String typedCardPassword;
	
	private Account account;	
	private AdvantagedAccount advantaged;
	private Transaction transaction;
	private Transfer transfer;

	private boolean isToday  = true;
	
	public TransferBean(){ 
		super();
		
		init();
	}
	

	private void init(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)fc.getExternalContext()
												.getSession(false);
		
		account = (Account)session.getAttribute("account");
		advantaged = new AdvantagedAccount();
		transaction = new Transaction();
		transaction.setTransactionType(ServiceTransfer.getInstance().getTranferType());
		transaction.setAccount(account);
		transfer = new Transfer();
		transfer.setAdvantageAccount(advantaged);
		transfer.setTransaction(transaction);
	}
	
	public String doTransfer(){
		if(!account.getCardPassword().equals(getTypedCardPassword()))
			return "failed";
		else{
			ServiceTransfer service = ServiceTransfer.getInstance();
			
			try {
				service.save(transfer);
				
				if(isToday)
					account.setBalance(account.getBalance()-transfer.getTransaction().getAmount());
			} catch (ServiceException e) {
				e.printStackTrace();
				return "failed";
			}
		
			
			return "voucher";
		}
	}
	
	public String transferPage(){
		init();
		
		return "transfer";
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public AdvantagedAccount getAdvantaged() {
		return advantaged;
	}

	public void setAdvantaged(AdvantagedAccount advantaged) {
		this.advantaged = advantaged;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Transfer getTransfer() {
		return transfer;
	}

	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}

	public String getTypedCardPassword() {
		return typedCardPassword;
	}

	public void setTypedCardPassword(String typedCardPassword) {
		this.typedCardPassword = typedCardPassword;
	}


	public boolean isToday() {
		return isToday;
	}


	public void setToday(boolean isToday) {
		this.isToday = isToday;
	}	
}
