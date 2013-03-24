package br.com.internetbanking.model;

import java.io.Serializable;


public class AccountType implements Serializable{

	private static final long serialVersionUID = -7557297496162442646L;
	
	private int idAccountType;
	private String code;
	private String descrption;
	public int getIdAccountType() {
		return idAccountType;
	}
	public void setIdAccountType(int idAccountType) {
		this.idAccountType = idAccountType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescrption() {
		return descrption;
	}
	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code.hashCode();
		result = prime * result + idAccountType;
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
		AccountType other = (AccountType) obj;
		if (code != other.code)
			return false;
		if (idAccountType != other.idAccountType)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AccountType [idAccountType=" + idAccountType + ", code=" + code
				+ ", descrption=" + descrption + "]";
	}	
}
