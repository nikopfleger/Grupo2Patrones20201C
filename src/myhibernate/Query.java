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
	   System.out.println(pValue instanceof String);
	   System.out.println(pValue instanceof Number);
	   System.out.println(pValue.getClass());
       System.out.println(java.lang.Character.class);

       hql = hql.replace(":" + pName, pValue.toString());
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
