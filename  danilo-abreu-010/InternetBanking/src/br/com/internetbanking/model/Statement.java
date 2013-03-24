package br.com.internetbanking.model;

import java.io.Serializable;

public class Statement implements Serializable{

	private static final long serialVersionUID = -1644203340594727124L;
	
	private Transaction transaction;
	private String inOut;
	
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public String getInOut() {
		return inOut;
	}
	public void setInOut(String inOut) {
		this.inOut = inOut;
	}
}
