package br.com.internetbanking.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.internetbanking.exception.ServiceException;
import br.com.internetbanking.model.Account;
import br.com.internetbanking.model.Statement;
import br.com.internetbanking.service.ServiceStatement;

@ManagedBean(name="statement")
@SessionScoped
public class StatementBean {
	
	private Date sinceDate;
	private Date toDate;
	private Account account;
	
	private List<Statement> lsStatement;
	
	public StatementBean(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)fc.getExternalContext()
												.getSession(false);
		
		account = (Account)session.getAttribute("account");	
		
		init();
	}
	
	
	private void init(){
		
		Calendar c = Calendar.getInstance();
		toDate = c.getTime();

		c.add(Calendar.DATE, -3); 
		sinceDate = c.getTime();

		listAll();
	}
	

	public String listAll(){
		
		ServiceStatement service = ServiceStatement.getInstance();
		
		try {
			lsStatement = service.getAllByDate(account, sinceDate, toDate);
		} catch (ServiceException e) {
			e.printStackTrace();
			return "failed";
		}
		return "statement";
	}


	public Date getSinceDate() {
		return sinceDate;
	}


	public void setSinceDate(Date sinceDate) {
		this.sinceDate = sinceDate;
	}


	public Date getToDate() {
		return toDate;
	}


	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}


	public List<Statement> getLsStatement() {
		return lsStatement;
	}


	public void setLsStatement(List<Statement> lsStatement) {
		this.lsStatement = lsStatement;
	}
}
