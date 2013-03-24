package br.com.internetbanking.model;

import java.io.Serializable;


public class Transfer implements Serializable{
	
	private static final long serialVersionUID = 5239829555927373249L;
	
	private int idTransfer;
	private Transaction transaction;
	private AdvantagedAccount advantageAccount;
	public int getIdTransfer() {
		return idTransfer;
	}
	public void setIdTransfer(int idTransfer) {
		this.idTransfer = idTransfer;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public AdvantagedAccount getAdvantageAccount() {
		return advantageAccount;
	}
	public void setAdvantageAccount(AdvantagedAccount advantageAccount) {
		this.advantageAccount = advantageAccount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((advantageAccount == null) ? 0 : advantageAccount.hashCode());
		result = prime * result + idTransfer;
		result = prime * result
				+ ((transaction == null) ? 0 : transaction.hashCode());
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
		Transfer other = (Transfer) obj;
		if (advantageAccount == null) {
			if (other.advantageAccount != null)
				return false;
		} else if (!advantageAccount.equals(other.advantageAccount))
			return false;
		if (idTransfer != other.idTransfer)
			return false;
		if (transaction == null) {
			if (other.transaction != null)
				return false;
		} else if (!transaction.equals(other.transaction))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Transfer [idTransfer=" + idTransfer + ", transaction="
				+ transaction + ", advantageAccount=" + advantageAccount + "]";
	}
}
