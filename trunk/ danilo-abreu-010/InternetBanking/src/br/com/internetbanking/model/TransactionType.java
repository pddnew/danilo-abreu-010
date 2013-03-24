package br.com.internetbanking.model;

import java.io.Serializable;


public class TransactionType implements Serializable{
	
	private static final long serialVersionUID = -5984001331450977181L;
	private int idTransactionType;
	private String description;
	public int getIdTransactionType() {
		return idTransactionType;
	}
	public void setIdTransactionType(int idTransactionType) {
		this.idTransactionType = idTransactionType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTransactionType;
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
		TransactionType other = (TransactionType) obj;
		if (idTransactionType != other.idTransactionType)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TransactionType [idTransactionType=" + idTransactionType
				+ ", description=" + description + "]";
	}
}
