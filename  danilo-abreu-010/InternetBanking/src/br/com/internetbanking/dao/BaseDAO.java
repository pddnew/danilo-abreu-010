package br.com.internetbanking.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.internetbanking.locator.JdbcServiceLocator;


public abstract class BaseDAO<T> {
    
    protected BaseDAO() {    }
    
    public Connection getConnection() throws SQLException {
        return JdbcServiceLocator.getInstance().getConnection();
    }
    
    public void closeResources(Connection conn, Statement st) {
        closeResources(conn, st, null);
    }
    
    public void closeResources(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            System.out.println("Erro on Close ResultSet. " + e.getMessage());
        }
        try {
            if (st != null)
                st.close();
        } catch (SQLException e) {
            System.out.println("Erro on Close Statement. " + e.getMessage());
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("Erro on Close Connection. " + e.getMessage());
        }
    }

    /**
     * Catch code(PK) from the Last register inserted
     * */
    public long getLastCode(PreparedStatement pst) throws SQLException {
        ResultSet rs = null;
        try {
            rs = pst.getGeneratedKeys();
            long code = 0;
            while (rs.next()) {
            	code = rs.getLong(1);
            }
            return code;
        } catch (Exception e) {
            throw new SQLException("[BaseDAO - LastCode] : Handled Exception  ", e);
        }
    }
    
    protected abstract List<T> getManyByCriteria(String sql) throws SQLException;
    
    public abstract List<T> getAll() throws SQLException;
    
    public abstract T getByPrimaryKey(Integer id) throws SQLException;
    
    public abstract void delete(T instance) throws SQLException;
    
    public abstract void save(T instance) throws SQLException; 
}
