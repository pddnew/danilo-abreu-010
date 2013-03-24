package br.com.internetbanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.internetbanking.model.Account;
import br.com.internetbanking.model.Transaction;
import br.com.internetbanking.model.TransactionType;

public class VoucherDAO extends BaseDAO<Transaction> {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static final String SELECT_ALL = "SELECT t.idtransactions, t.idaccount, " +
			" t.id_transaction_type, t.schedule_date, t.exec_date, t.amount, t.description as 'desc0', " +
			" tt.idtransaction_type, tt.description as 'desc1', " +
			" bs.bar_code, tf.idadvantage_account " +
			" from transactions t " +
			" inner JOIN transaction_type tt on t.id_transaction_type = tt.idtransaction_type " +
			" left join bank_slips bs on bs.idtransaction = t.idtransactions" +
			" left join transfer tf on tf.idtransaction = t.idtransactions " +
			" WHERE idaccount = ? order by idtransactions desc ";
	
	public List<Transaction> getAllById(Account instance) throws SQLException{
		
		List<Transaction> ls = new ArrayList<Transaction>();
		
		conn = getConnection();
		
		pstmt = conn.prepareStatement(SELECT_ALL);
		pstmt.setInt(1, instance.getIdAccount());
		
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			Transaction t = new Transaction();
			
			t.setAccount(instance);
			t.setIdTransaction(rs.getInt("idtransactions"));
			t.setExecuteDate(rs.getTimestamp("exec_date"));
			t.setScheduleDate(rs.getTimestamp("schedule_date"));
			t.setAmount(rs.getFloat("amount"));
			t.setDescription(rs.getString("desc0"));
			TransactionType tt = new TransactionType();
			tt.setIdTransactionType(rs.getInt("idtransaction_type"));
			tt.setDescription(rs.getString("desc1"));
			t.setTransactionType(tt);
			
			ls.add(t);
		}
		
		return ls;
	}
	
	@Override
	protected List<Transaction> getManyByCriteria(String sql)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction getByPrimaryKey(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Transaction instance) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Transaction instance) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
