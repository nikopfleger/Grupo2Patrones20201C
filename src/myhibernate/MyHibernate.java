package myhibernate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import Entities.Producto;
import ann.Column;
import ann.Id;
import ann.JoinColumn;
import ann.Table;
import database.DBManager;
import org.hsqldb.jdbc.JDBCDataSource;
import sun.security.jca.GetInstance;

public class MyHibernate
{
   public static <T> T find(Class<T> clazz, int id)
   {
	   Connection c = null;
	   ResultSet rs = null;
	   T returnedObject = null;
	   JDBCDataSource ds = new JDBCDataSource();
	   try
	   {
	   c = DriverManager.getConnection("jdbc:hsqldb:C:\\Users\\f_luc\\Documents\\java64\\hsqldb-2.3.4\\hsqldb\\testdb;hsqldb.lock_file=false","sa","");
	   //DBManager db = new DBManager("jdbc:hsqldb:C:\\Users\\f_luc\\Documents\\java64\\hsqldb-2.3.4\\hsqldb\\testdb","sa","");
	   Statement stmt = c.createStatement();
	   
		   // Armado de la query SQL
		   String sqlQuery= "";
		   sqlQuery += "SELECT " + GetClassFields(clazz)+" ";
		   sqlQuery += "FROM "+ GetTableName(clazz)+" ";
		   sqlQuery += "WHERE "+ IDColumnName(clazz)+" = " + id;
		   
		   System.out.println(sqlQuery);

		   // pstm.setObject(1, id);
		   
		   // Ejecucion de la query
		   //rs = db.ExecuteQuery(sqlQuery);
		   rs= stmt.executeQuery(sqlQuery);
		   if(rs== null){
			   System.out.println("NULO");
		   }
	   	   if( rs.next() )
	   	   {
	   		   System.out.println("llego");
	   	   // obtengo una instancia del DTO y le seteo los datos tomados del ResultSet
	   	   returnedObject = GetInstance(clazz);
	   	   InvokeSetters(returnedObject, rs, clazz);
		   // si hay otra fi la entonces hay inconsistencia de datos...
		      	if( rs.next() )
		      	{
		      		throw new RuntimeException("Mas de una fila...");
			   	}
		   		return returnedObject;
		   }
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
			   if( c!=null ) c.close();
		   }
		   catch(Exception ex)
		   {
			   ex.printStackTrace();
			   throw new RuntimeException(ex);
		   }
	   }
   }

   public static <T> List<T> findAll(Class<T> clazz)
   {
      // PROGRAMAR AQUI
      return null;
   }

   public static Query createQuery(String hql)
   {
      // PROGRAMAR AQUI
      return null;
   }
   
   private static <T> T GetInstance(Class<T> dtoClass)
   {
	   try
	   {
		   return dtoClass.newInstance();
	   }
	   catch(Exception ex)
	   {
		   ex.printStackTrace();
		   throw new RuntimeException(ex);
	   }
   }
   
   private static String GetClassFields(Class dto)
   {
	   
	   Field[] fields = dto.getDeclaredFields();
	   String fieldsConcat="";
	   String fieldName="";
	   
	   for( int i=0; i < fields.length; i++ )
	   {
		   if(fields[i].isAnnotationPresent(Column.class)){
			   if(fields[i].getDeclaredAnnotation(Column.class).name() != ""){
				   fieldName = fields[i].getDeclaredAnnotation(Column.class).name();
			   }else{
				   fieldName = fields[i].getName();
			   }
		   }
		   fieldsConcat += fieldName + ((i < fields.length-1)?", ":"");
	   }
	   
	   return fieldsConcat;
   }
   
   private static <T> String GetTableName(Class<T> dto)
   {
	   String tableName = dto.getAnnotation(Table.class).name();
	   	   
	   return tableName;
   }
   
   private static String IDColumnName(Class dto)
   {
	   Field[] fields = dto.getDeclaredFields();
	   String idColumnName = "";
	   
	   for (Field field : fields) {
		   if (field.isAnnotationPresent(Id.class))
			   idColumnName = field.getAnnotation(Column.class).name();
	   }
	   
	   return idColumnName;
   }
   
   private static void InvokeSetters(Object dto, ResultSet rs, Class dtoClass)
   {
	   Field[] fields = dtoClass.getDeclaredFields();
	   Object valueColumn;
	   String attName = "";

	   try
	   {
		   for (Field field : fields) {
			   attName = field.getAnnotation(Column.class).name(); // Esto esta MAL
			   valueColumn = rs.getObject(field.getAnnotation(Column.class).name());
			   
			   // Utilizar los setters para poner los valores a los respectivos campos
		   }
	   }
	   catch(Exception ex)
	   {
		   ex.printStackTrace();
		   throw new RuntimeException(ex);
	   }
   }
   
  
}
