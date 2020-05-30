package database;

import java.sql.*;
import MyHibernateProperties.HibernatePropertyValues;

public class DBManager
{
	private Connection conn = null;
	
	public void Connect() {
		try {
			HibernatePropertyValues properties = new HibernatePropertyValues();
			String db_url_prop = properties.getPropValues("db_url");

			conn = DriverManager.getConnection(db_url_prop);
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