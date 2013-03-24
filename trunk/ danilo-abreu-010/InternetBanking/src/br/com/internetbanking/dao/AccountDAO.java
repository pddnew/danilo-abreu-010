package br.com.internetbanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.internetbanking.model.Account;
import br.com.internetbanking.model.AccountType;
import br.com.internetbanking.model.Client;

public class AccountDAO extends BaseDAO<Account> {
	
	private Connection conn;
	
	private static final String SELECT_ALL = "select a.idaccount, a.idclient, " +
			"a.idaccount_type, a.number, a.balance, a.epassword, a.cardpassword, "
						+ " c.`CPFCNPJ`, c.fist_name, c.last_name,"
						+ " t.code, t.description "
						+ " from account a "
						+ " inner join client c on a.idclient = c.idclient "
						+ " inner join account_type t on a.idaccount_type = t.idaccount_type";

	private static final String SELECT_BY_NUMBER = SELECT_ALL + " where a.number = ?";
	
	@Override
	protected List<Account> getManyByCriteria(String sql) throws SQLException {
		 conn = getConnection();
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 Client client = null;
		 AccountType accountType = null;
		 Account account = null;		 
		 List<Account> lsAccount = new ArrayList<Account>();
		 
		 
		 pstmt = conn.prepareStatement(sql);
		 
		 rs = pstmt.executeQuery();
		 
		 while(rs.next()){
			 account = new Account();
			 client = new Client();
			 accountType = new AccountType();			 
			 
			 client.setIdClient(rs.getInt("idclient"));
			 client.setCpfCnpj(rs.getString("CPFCNPJ"));
			 client.setFirstName(rs.getString("fist_name"));
			 client.setLastName(rs.getString("last_name"));
			 
			 account.setClient(client);
			 
			 accountType.setIdAccountType(rs.getInt("idaccount_type"));
			 accountType.setCode(rs.getString("code"));
			 accountType.setDescrption(rs.getString("description"));
			 
			 account.setAccountType(accountType);
			 
			 account.setIdAccount(rs.getInt("idaccount"));
			 account.setNumber(rs.getString("number"));
			 account.setBalance(rs.getFloat("balance"));
			 account.setEpassword(rs.getString("epassword"));
			 account.setCardPassword(rs.getString("cardpassword"));
			 
			 lsAccount.add(account);
			 
		 }
		
		return lsAccount;
	}

	public Account getByNumber(String number) throws SQLException{
		Client client = null;
		AccountType accountType = null;
		Account account = null;
		 
		ResultSet rs = null;
		PreparedStatement pstmt = getConnection()
										.prepareStatement(SELECT_BY_NUMBER);
		pstmt.setString(1, number);		
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			account = new Account();
			client = new Client();
			accountType = new AccountType();			 
			 
			client.setIdClient(rs.getInt("idclient"));
			client.setCpfCnpj(rs.getString("CPFCNPJ"));
			client.setFirstName(rs.getString("fist_name"));
			client.setLastName(rs.getString("last_name"));
			 
			account.setClient(client);
		 
			accountType.setIdAccountType(rs.getInt("idaccount_type"));
			accountType.setCode(rs.getString("code"));
			accountType.setDescrption(rs.getString("description"));
			 
			account.setAccountType(accountType);
			 
			 account.setIdAccount(rs.getInt("idaccount"));
			 account.setNumber(rs.getString("number"));
			 account.setBalance(rs.getFloat("balance"));
			 account.setEpassword(rs.getString("epassword"));
			 account.setCardPassword(rs.getString("cardpassword"));
			
			return account;
		}else{
			throw new SQLException("[AccountDAO] Handle Exception : No Results were found");
		}
	}

	@Override
	public List<Account> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getByPrimaryKey(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Account instance) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Account instance) throws SQLException {
		// TODO Auto-generated method stub
		
	}	
}
