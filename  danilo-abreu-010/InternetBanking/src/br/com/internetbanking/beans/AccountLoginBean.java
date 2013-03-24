package br.com.internetbanking.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.internetbanking.exception.ServiceException;
import br.com.internetbanking.model.Account;
import br.com.internetbanking.model.AdvantagedAccount;
import br.com.internetbanking.service.ServiceAccount;
import br.com.internetbanking.service.ServiceAdvantaged;

@ManagedBean( name = "AccountLoginBean")
@SessionScoped
public class AccountLoginBean implements Serializable{	

	private static final long serialVersionUID = 1851674336737900947L;
	
	private AdvantagedAccount advantaged;		
	private Account account;
	
	private ServiceAdvantaged serviceAdvantaged;
	private ServiceAccount serviceAccount;
	
	/**
	 * Load Password typed by user
	 * */
	private String typedPassword;
		
	
	/*
	 * #region Controller / Actions 
	 * */	
	public AccountLoginBean(){
		super();
		account = new Account();
		advantaged = new AdvantagedAccount();
	}
	 
	public String doCheckUserAccount(){
		
		serviceAccount = ServiceAccount.getInstance();
		
		try {
			account = serviceAccount.getAccountByNumber(account.getNumber());
		} catch (Exception e) {
			return "failed";
		}
		
		return "password";
	}

	public String doCheckPassword(){
		if(account.getEpassword().equals(typedPassword)){
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession)fc.getExternalContext()
													.getSession(false);
			
			session.setAttribute("account", account);
			
			return "success";
		}else
			return "failed";
	}
	
	public String doLogout(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)fc.getExternalContext()
												.getSession(false);
		if(session != null)
			session.invalidate();
		
		return "logout";
	}
	
	public String doSaveAdvantaged(){
		
		serviceAdvantaged = ServiceAdvantaged.getInstance();
		
		advantaged.setAccount(account);
		
		try {
			serviceAdvantaged.save(advantaged);
			account.setLsAdvantagedAccount(serviceAdvantaged.getAdvantagedList(this.account));
		} catch (ServiceException e) {
			e.printStackTrace();
			return "failed";			
		}
		return "advantaged";
	}
	
	public String doDeleteAdvantaged(){
		serviceAdvantaged = ServiceAdvantaged.getInstance();
		
		advantaged.setAccount(account);
		
		try {
			serviceAdvantaged.delete(advantaged);
			account.setLsAdvantagedAccount(serviceAdvantaged.getAdvantagedList(this.account));
		} catch (ServiceException e) {
			e.printStackTrace();
			return "failed";
		}
		return "advantaged";
	}
	
	public String doAdvantaged(){
		
		/* Clear fields */
		advantaged.setAdvantagedAccountNumer("");
		advantaged.setDescription("");
		
		serviceAdvantaged = ServiceAdvantaged.getInstance();		
		
		try {
			account.setLsAdvantagedAccount(serviceAdvantaged.getAdvantagedList(this.account));
		} catch (ServiceException e) {			
			e.printStackTrace();
		}
		
		return "advantaged";
	}
	/*	 
	 * * #endregion	 *
	 * */
	
	/*
	 * #region * Getters and Setters * * 
	 * 
	 * */
	
	public String getTypedPassword(){
		return this.typedPassword;
	}
	
	public void setTypedPassword(String typedPassword){
		this.typedPassword = typedPassword;
	}
	
	public Account getAccount(){
		return account;
	}
	
	public AdvantagedAccount getAdvantaged() {
		return advantaged;
	}	

	/* s
	 * #endregion
	 * */
}
