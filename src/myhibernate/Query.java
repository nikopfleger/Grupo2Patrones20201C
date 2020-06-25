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
       hql = hql.replace(":" + pName, pValue.toString());
   }

   public <T> List<T> getResultList()
   {
	   
	   return null;
   }
   
   public String getHql() {
	   return hql;
   }
   
   public void setHql(String hql) {
	   this.hql = hql;
   }
}
