package myhibernate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import builder.ClassBuilder;
import database.DBManager;

public class Query
{
   private String hql;
   private static DBManager db;
   
   public Query(String q) {
	   hql = q;
   }
	
   public void setParameter(String pName,Object pValue)
   {
       hql = hql.replace(":" + pName, pValue.toString());
   }

   public <T> List<T> getResultList()
   {
	   	ResultSet rs = null;
	    List<T> listReturned = new ArrayList<T>();
	    db = new DBManager();
	    db.Connect();
		   
		try
		{
			// Ejecucion Query
			rs = db.ExecuteQuery(hql);
			
			if(rs == null) System.out.println("Tabla Vacia");
			return null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		finally
		{
			try
			{
				if(db != null) db.Close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
   }
   
   public String getHql() {
	   return hql;
   }
   
   public void setHql(String hql) {
	   this.hql = hql;
   }
}
