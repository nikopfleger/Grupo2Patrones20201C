package database;

import java.sql.*;

public class DBManager
{
	private String DB_URL;
	private String User;
	private String Password;
	private Connection conn = null;
	
	public DBManager(String db_url, String user, String password) {
		DB_URL = db_url;
		User = user;
		Password = password;
		
		try {
			// org.hsqldb.jdbcDriver - capitulo 15 - Java a Fondo
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Connect() {
		try {
			conn = DriverManager.getConnection(DB_URL, User, Password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Close() {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet ExecuteQuery(String query) {
		ResultSet rs = null;
        Statement st = null;

        try {
        	if (conn != null) {
        		st = conn.createStatement();
                rs = st.executeQuery(query);
        	}            
        } catch (SQLException ex) {
        	ex.printStackTrace();
        } finally {
           	if (st != null) {
	            try{  
	                st.close();
	            } catch (SQLException ex) {
	            	ex.printStackTrace();
	            }
           	}
        }
        
        return rs;
	}
}