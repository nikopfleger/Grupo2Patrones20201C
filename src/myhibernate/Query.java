package myhibernate;

import java.util.Arrays;
import java.util.List;

public class Query
{
   private String hql;
   
   public Query(String q) {
	   hql = q;
   }
	
   public void setParameter(String pName,Object pValue)
   {
	  if ((pValue instanceof String) || (pValue instanceof Number))
		  hql = hql.replace(":" + pName, pValue.toString());
	  else
	  {
		
	  }
      // PROGRAMAR AQUI   
	   
   }

   public <T> List<T> getResultList()
   {
      // PROGRAMAR AQUI
      return null;
   }
   
   public String getHql() {
	   return hql;
   }
   
   public void setHql(String hql) {
	   this.hql = hql;
   }
}
