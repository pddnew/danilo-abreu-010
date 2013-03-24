package br.com.internetbanking.model;

import java.io.Serializable;
import java.util.List;



public class Account implements Serializable {

	private static final long serialVersionUID = 4479032578102489274L;
	private int idAccount;
	private Client client;
	private AccountType accountType;
	private String number;
	private float balance;
	private String epassword;
	private String cardPassword;
	
	private List<AdvantagedAccount> lsAdvantagedAccount;
	private List<Transaction> lsTransaction;
	
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getEpassword() {
		return epassword;
	}
	public void setEpassword(String epassword) {
		this.epassword = epassword;
	}
	public String getCardPassword() {
		return cardPassword;
	}
	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}
	/**
	 * @return the lsAdvantagedAccount
	 */
	public List<AdvantagedAccount> getLsAdvantagedAccount() {
		return lsAdvantagedAccount;
	}
	
	/**
	 * @param lsAdvantagedAccount the lsAdvantagedAccount to set
	 */
	public void setLsAdvantagedAccount(List<AdvantagedAccount> lsAdvantagedAccount) {
		this.lsAdvantagedAccount = lsAdvantagedAccount;
	}
	/**
	 * @return the lsTransaction
	 */
	public List<Transaction> getLsTransaction() {
		return lsTransaction;
	}
	/**
	 * @param lsTransaction the lsTransaction to set
	 */
	public void setLsTransaction(List<Transaction> lsTransaction) {
		this.lsTransaction = lsTransaction;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAccount;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		Account other = (Account) obj;
		if (idAccount != other.idAccount)
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [idAccount=" + idAccount + ", client=" + client
				+ ", accountType=" + accountType + ", number=" + number
				+ ", balance=" + balance + ", epassword=" + epassword
				+ ", cardPassword=" + cardPassword + "]";
	}	
}
