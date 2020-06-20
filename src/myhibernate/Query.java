package myhibernate;

import java.util.List;

public class Query
{
   private String hql;
   
   public Query(String q) {
	   hql = q;
   }
	
   public void setParameter(String pName,Object pValue)
   {
	  if ((pValue instanceof String) || (pValue instanceof Number) || (pValue.getClass().equals(char.class)))
		  hql = hql.replace(":" + pName, pValue.toString());
	  else
	  {
		//PREGUNTAR SI PUEDE RECIBIR UN OBJETO
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
