package myhibernate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import builder.ClassBuilder;
import database.DBManager;

public class Query
{
   private String hql;
   private Class<?> clazz;
   private static DBManager db;
   
   public Query(String hql, Class<?> clazz) {
	   this.hql = hql;
	   this.clazz = clazz;
   }
	
   public void setParameter(String pName,Object pValue)
   {
	   if(pValue instanceof String) 
		   hql = hql.replace(":" + pName, "'" + pValue.toString() + "'");
	   else
		   hql = hql.replace(":" + pName, pValue.toString());
   }

   public <T> List<T> getResultList()
   {
	   	ResultSet rs = null;
	   	List<T> listReturned = new ArrayList<T>();
	   	T returnedObject = null;
	    db = new DBManager();
	    db.Connect();

		try
		{
			// Ejecucion de la query
		    rs = db.ExecuteQuery(hql);
		    
		    if(rs == null) System.out.println("Tabla Vacia");
		    do{
				returnedObject = null;
				if(rs.next())
				{
					returnedObject = MyHibernate.GetInstance(clazz);
					if(MyHibernate.clasesMejoradas.get(returnedObject.getClass()) == null)
						ClassBuilder.mejoraClase(returnedObject.getClass(), MyHibernate.clasesMejoradas);
					returnedObject = (T)MyHibernate.clasesMejoradas.get(returnedObject.getClass()).newInstance();
					MyHibernate.InvokeSetters(returnedObject,rs,clazz,returnedObject);
					if (returnedObject != null) listReturned.add(returnedObject);
				}
	        } while (returnedObject != null);
		    
		    return listReturned;
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
