package br.com.internetbanking.locator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JdbcServiceLocator {
    
    private String databaseURL = "jdbc:mysql://localhost:3306/internetbanking";
    
    private String databasePassword = "root";
    
    private String databaseUser = "root";
    
    private static JdbcServiceLocator instance = new JdbcServiceLocator();
    
    private JdbcServiceLocator() {
    }
    
    public static JdbcServiceLocator getInstance() {
        return instance;
    }
    
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(databaseURL, databaseUser,databasePassword);
        } catch (Exception e) {
            System.out.println("[JDBC Service Locator ] Fatal error getting Connection : " + e.getMessage());
            throw new SQLException("[JDBC Service Locator ] Fatal error getting Connection : " + e.getMessage(),e);
        }
        return conn;
    }
}