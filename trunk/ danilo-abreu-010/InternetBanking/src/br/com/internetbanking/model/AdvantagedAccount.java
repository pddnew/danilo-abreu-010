package br.com.internetbanking.model;

import java.io.Serializable;


public class AdvantagedAccount implements Serializable {

	private static final long serialVersionUID = 9046444395936948011L;
	
	private int idAdvantagedAccount;
	private Account account;
	private String advantagedAccountNumer;
	private String description;
	public int getIdAdvantagedAccount() {
		return idAdvantagedAccount;
	}
	public void setIdAdvantagedAccount(int idAdvantagedAccount) {
		this.idAdvantagedAccount = idAdvantagedAccount;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getAdvantagedAccountNumer() {
		return advantagedAccountNumer;
	}
	public void setAdvantagedAccountNumer(String advantagedAccountNumer) {
		this.advantagedAccountNumer = advantagedAccountNumer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime
				* result
				+ ((advantagedAccountNumer == null) ? 0
						: advantagedAccountNumer.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdvantagedAccount other = (AdvantagedAccount) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (advantagedAccountNumer == null) {
			if (other.advantagedAccountNumer != null)
				return false;
		} else if (!advantagedAccountNumer.equals(other.advantagedAccountNumer))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AdvantagedAccount [idAdvantagedAccount=" + idAdvantagedAccount
				+ ", account=" + account + ", advantagedAccountNumer="
				+ advantagedAccountNumer + ", description=" + description + "]";
	}	
}
