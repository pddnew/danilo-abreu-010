package br.com.internetbanking.model;

import java.io.Serializable;
import java.util.Date;

public class BankSlips implements Serializable{

	private static final long serialVersionUID = -8677775810095959341L;
	
	private int idBankSlips;
	private Transaction transaction;
	private String barCode;
	private Date expirationDate;
	public int getIdBankSlips() {
		return idBankSlips;
	}
	public void setIdBankSlips(int idBankSlips) {
		this.idBankSlips = idBankSlips;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idBankSlips;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankSlips other = (BankSlips) obj;
		if (idBankSlips != other.idBankSlips)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BankSlips [idBankSlips=" + idBankSlips + ", transaction="
				+ transaction + ", barCode=" + barCode + ", expirationDate="
				+ expirationDate + "]";
	}
}
