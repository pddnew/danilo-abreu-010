package br.com.internetbanking.model;

import java.io.Serializable;


public class Client implements Serializable {

	private static final long serialVersionUID = 1839431928754745747L;
	private int idClient;
	private String cpfCnpj;
	private String firstName;
	private String lastName;
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idClient;
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
		Client other = (Client) obj;
		if (idClient != other.idClient)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", cpfCnpj=" + cpfCnpj
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
