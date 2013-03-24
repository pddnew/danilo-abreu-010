package br.com.internetbanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.internetbanking.model.Account;
import br.com.internetbanking.model.AdvantagedAccount;

public class AdvantagedDAO extends BaseDAO<AdvantagedAccount> {
	
	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String INSERT_ADV = "INSERT INTO `internetbanking`.`advantage_account`" 
							+ " ( `id_account`, `advantage_account_number`, `description`) " +
							" VALUES ( ?, ?, ? )";
	
	private static final String SELECT_ALL = "SELECT * FROM `internetbanking`.`advantage_account` ";
	
	private static final String SELECT_ISREGISTER =  SELECT_ALL + " where id_account = ? and advantage_account_number = ? ";
	
	private static final String SELECT_BY_ACCOUNT =  SELECT_ALL + " where id_account = ? ";
	
	private static final String DELETE_BY_ID = "DELETE FROM `internetbanking`.`advantage_account` where idadvantage_account = ? ";
	
	private static final String UPDATE_BY_NUMBER = "UPDATE `internetbanking`.`advantage_account` SET `description` = ? where `advantage_account_number` = ?";
	
	public boolean isRegistered(AdvantagedAccount advantaged) throws SQLException{
		
		ResultSet rs = null;
		
		conn = getConnection();
		
		pstmt = conn.prepareStatement(SELECT_ISREGISTER);		
		pstmt.setInt(1, advantaged.getAccount().getIdAccount());
		pstmt.setString(2, advantaged.getAdvantagedAccountNumer());		
		
		rs = pstmt.executeQuery();
		
		if(rs.next())
			return true;
		else
			return false;
	}
		
 	public List<AdvantagedAccount> getByAccount(Account account) throws SQLException{		

		List<AdvantagedAccount> ls = new ArrayList<AdvantagedAccount>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = getConnection();
		
		pstmt = conn.prepareStatement(SELECT_BY_ACCOUNT);
		pstmt.setInt(1, account.getIdAccount());
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			AdvantagedAccount var = new AdvantagedAccount();
			
			var.setIdAdvantagedAccount(rs.getInt("idadvantage_account"));
			var.setAdvantagedAccountNumer(rs.getString("advantage_account_number"));
			var.setDescription(rs.getString("description"));
			
			ls.add(var);
		}
		
		closeResources(conn, pstmt, rs);
		
		
		return ls;
	}
	
	@Override
	protected List<AdvantagedAccount> getManyByCriteria(String sql)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdvantagedAccount> getAll() throws SQLException {
		return null;
	}

	@Override
	public AdvantagedAccount getByPrimaryKey(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(AdvantagedAccount instance) throws SQLException {
		conn = getConnection();		
		
		pstmt = conn.prepareStatement(DELETE_BY_ID);		
		pstmt.setInt(1, instance.getIdAdvantagedAccount());		
		pstmt.execute();
	}

	@Override
	public void save(AdvantagedAccount instance) throws SQLException {
		
		boolean registered = isRegistered(instance);
		
		conn = getConnection();
		
		if(!registered){
			pstmt = conn.prepareStatement(INSERT_ADV);
			pstmt.setInt(1, instance.getAccount().getIdAccount());
			pstmt.setString(2, instance.getAdvantagedAccountNumer());
			pstmt.setString(3, instance.getDescription());
		}else{
			pstmt = conn.prepareStatement(UPDATE_BY_NUMBER);
			pstmt.setString(1, instance.getDescription());
			pstmt.setString(2, instance.getAdvantagedAccountNumer());
		}
		
		pstmt.execute();		
	}
	
	@Override
	protected void finalize() throws Throwable {
		closeResources(conn, pstmt);
		super.finalize();
	}
}
