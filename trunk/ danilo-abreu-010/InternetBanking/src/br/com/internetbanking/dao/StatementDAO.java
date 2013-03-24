package br.com.internetbanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.internetbanking.model.Account;
import br.com.internetbanking.model.Statement;
import br.com.internetbanking.model.Transaction;
import br.com.internetbanking.util.SQL;

public class StatementDAO extends BaseDAO<Transaction> {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static final String SELECT_ALL = " select ts.exec_date, ts.amount, ts.description," +
			" '-' as 'in-out'  from transactions ts where ts.idaccount = ? and ts.exec_date " +
			" between ? and adddate(?, interval 1 day) union all " +
			" SELECT ts.exec_date, ts.amount, aa.description, '+' as 'in-out' from transfer tf " +
			" inner join transactions ts on tf.idtransaction = ts.idtransactions " +
			" inner join advantage_account aa on aa.idadvantage_account = tf.idadvantage_account" +
			" where advantage_account_number = ? and exec_date is not null and ts.exec_date between ? and adddate( ? , interval 1 day)" +
			" order by exec_date desc ";

	public List<Statement> getAllByDate(Account a, Date since, Date to) throws SQLException{
		 List<Statement> ls = new ArrayList<Statement>();
		conn = getConnection();
		
		pstmt = conn.prepareStatement(SELECT_ALL);
		pstmt.setInt(1, a.getIdAccount());
		pstmt.setDate(2, SQL.toSQLDate(since));
		pstmt.setTimestamp(3, SQL.toSQLTimeStamp(to));
		pstmt.setString(4, a.getNumber());
		pstmt.setDate(5, SQL.toSQLDate(since));
		pstmt.setTimestamp(6, SQL.toSQLTimeStamp(to));
		
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			Transaction t = new Transaction();
			Statement st = new Statement();
			
			t.setExecuteDate(rs.getDate("exec_date"));
			t.setAmount(rs.getFloat("amount"));
			t.setDescription(rs.getString("description"));
			st.setTransaction(t);
			st.setInOut(rs.getString("in-out"));
			
			ls.add(st);
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
