package br.com.internetbanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.internetbanking.model.BankSlips;
import br.com.internetbanking.util.SQL;


public class PaymentDAO extends BaseDAO<BankSlips> {
		
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static final String UPDATE_ACCOUNT = "UPDATE `internetbanking`.`account` " +
			"SET `balance` = balance-? WHERE idaccount = ?";

	private static final String SAVE_TRANSACTION = "INSERT INTO `internetbanking`.`transactions` " 
			+ "( `idaccount`,`id_transaction_type`,`schedule_date`,`exec_date`,`amount`, `description`)" +
			"VALUES(?, ?, ?, ?, ?, ?)";
	
	private static final String SELECT_LAST_TRANSACTION = "select max(idtransactions) as max from transactions WHERE idaccount = ?";	
	
	private static final String SAVE_PAYMENT = "INSERT INTO `internetbanking`.`bank_slips` " +
			"	( `idtransaction`, `bar_code`,`expiration_date`) " +
			" VALUES (  ?, ?, ?  ) ";
	
	@Override
	protected List<BankSlips> getManyByCriteria(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankSlips> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankSlips getByPrimaryKey(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(BankSlips instance) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(BankSlips instance) throws SQLException {
		conn = getConnection();
		
		conn.setAutoCommit(false);
		
		try{
			pstmt = conn.prepareStatement(UPDATE_ACCOUNT);
			pstmt.setFloat(1, instance.getTransaction().getAmount());
			pstmt.setInt(2, instance.getTransaction().getAccount().getIdAccount());
			
			pstmt.execute();
			
			pstmt  = conn.prepareStatement(SAVE_TRANSACTION);
			pstmt.setInt(1, instance.getTransaction().getAccount().getIdAccount());
			pstmt.setInt(2, instance.getTransaction().getTransactionType().getIdTransactionType());
			pstmt.setTimestamp(3, SQL.toSQLTimeStamp(instance.getTransaction().getScheduleDate()));
			pstmt.setTimestamp(4, SQL.toSQLTimeStamp(instance.getTransaction().getExecuteDate()));
			pstmt.setFloat(5, instance.getTransaction().getAmount());
			pstmt.setString(6, instance.getTransaction().getDescription());
			pstmt.execute();
			
			conn.commit();
			
			pstmt = conn.prepareStatement(SELECT_LAST_TRANSACTION);
			pstmt.setInt(1, instance.getTransaction().getAccount().getIdAccount());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				int idtransaction = rs.getInt("max");
				
				pstmt = conn.prepareStatement(SAVE_PAYMENT);
				
				pstmt.setInt(1, idtransaction);				
				pstmt.setString(2, instance.getBarCode() );
				pstmt.setDate(3, SQL.toSQLDate(instance.getExpirationDate()));
				
				System.out.println(pstmt);
				
				pstmt.execute();
				
				conn.commit();
			}else{
				throw new SQLException("[...] No transaction was found.");
			}				
		}catch(Exception e){
			conn.rollback();
			throw new SQLException(e.getMessage());
		}		
	}
}
