package database;

import java.sql.*;

public class DBManager
{
	private Connection conn = null;
	
	public void Connect() {
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:C:\\java64\\hsqldb-2.3.4\\hsqldb\\testdb;hsqldb.lock_file=false");
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