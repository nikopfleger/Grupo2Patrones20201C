package myhibernate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import Entities.Producto;
import ann.Column;
import ann.Id;
import ann.JoinColumn;
import ann.ManyToOne;
import ann.Table;
import builder.ClassBuilder;
import database.DBManager;
import sun.security.jca.GetInstance;

public class MyHibernate
{
	private static DBManager db;
	private static HashMap<String,String> joinHm;
	private static Map<Class<?>, Class<?>> clasesMejoradas = new HashMap<>();
	

	public static <T> T find(Class<T> clazz, int id)
	{
		ResultSet rs = null;
	    T returnedObject = null;
	    db = new DBManager();
	    db.Connect();
		   
		try
		{
			// Armado de la query SQL
			String sqlQuery = SQLQueryWithId(clazz, id);
//			System.out.println(sqlQuery);

			// Ejecucion de la query
			rs = db.ExecuteQuery(sqlQuery);
			
			if(rs == null) System.out.println("Resultado NULO");
			if(rs.next())
			{
				// obtengo una instancia del DTO y le seteo los datos tomados del ResultSet
				returnedObject = GetInstance(clazz);
				if(clasesMejoradas.get(returnedObject.getClass()) == null){
					ClassBuilder.mejoraClase(returnedObject.getClass(), clasesMejoradas);
				}
				returnedObject = (T)clasesMejoradas.get(returnedObject.getClass()).newInstance();
				InvokeSetters(returnedObject,rs,clazz,returnedObject);
				
				// si hay otra fila entonces hay inconsistencia de datos...
				if(rs.next()) throw new RuntimeException("Mas de una fila...");
				
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
				if(db != null) db.Close();
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
		ResultSet rs = null;
	    List<T> listReturned = new ArrayList<T>();
	    T returnedObject = null;
	    db = new DBManager();
	    db.Connect();
		   
		try
		{
			// Armado de la query SQL
			String sqlQuery = SQLQuery(clazz);
//			System.out.println(sqlQuery);

			// Ejecucion de la query
			rs = db.ExecuteQuery(sqlQuery);
			
			if(rs == null) System.out.println("Tabla Vacia");
			do{
				returnedObject = null;
				if(rs.next())
				{
					returnedObject = GetInstance(clazz);
					if(clasesMejoradas.get(returnedObject.getClass()) == null)
						ClassBuilder.mejoraClase(returnedObject.getClass(), clasesMejoradas);
					returnedObject = (T)clasesMejoradas.get(returnedObject.getClass()).newInstance();
					InvokeSetters(returnedObject,rs,clazz,returnedObject);
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

	public static Query createQuery(String hql)
	{
		// PROGRAMAR AQUI
		return null;
	}
	
	public static <T> Set<Class<?>> test(Class<T> clazz)
	{
		Reflections reflections = new Reflections("Entities");
		Set<Class<? extends T>> subtypes = reflections.getSubTypesOf(clazz);
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(ManyToOne.class);
		
		return annotated;
	}
	
	private static <T> String SQLQuery(Class<T> clazz) 
	{
		// Armado de la query SQL
		String sqlQuery="";
		String alias = "a0";
		String select = GetClassFields(clazz,alias);
		sqlQuery += "SELECT " + select + "\n";
		sqlQuery += "FROM " + GetTableName(clazz) + " " + alias + "\n";
	
		return sqlQuery;
	}
	
	private static <T> String SQLQueryWithId(Class<T> clazz, int id) 
	{
		// Armado de la query SQL
		if (joinHm == null)
			joinHm = new HashMap<String,String>();
		String sqlQuery="";
		String alias = "a0";
		sqlQuery += SQLQuery(clazz);
		sqlQuery += "WHERE " + alias + "." + IDColumnName(clazz) + " = " + id + "\n";
	
		return sqlQuery;
	}

	private static <T> T GetInstance(Class<?> dtoClass)
	{
		try
		{
			return (T)dtoClass.newInstance();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	private static String GetClassFields(Class dto, String alias)
	{
		Field[] fields=dto.getDeclaredFields();
		String fieldsConcat="";
		String fieldName="";

		for(int i=0; i<fields.length; i++)
		{
			if(fields[i].isAnnotationPresent(Column.class))
			{
				if(fields[i].getDeclaredAnnotation(Column.class).name()!="")
					fieldName=fields[i].getDeclaredAnnotation(Column.class).name();
				else
					fieldName=fields[i].getName();
			}
			else if(fields[i].isAnnotationPresent(JoinColumn.class))
			{
				if(fields[i].getDeclaredAnnotation(JoinColumn.class).name()!="")
					fieldName=fields[i].getDeclaredAnnotation(JoinColumn.class).name();
				else
					fieldName=fields[i].getName();
			}
			
			fieldsConcat += alias + "." + fieldName + " as " + alias + fieldName + ((i<fields.length-1)?", ":"");
		}

		return fieldsConcat;
	}

	private static <T> String GetTableName(Class<T> dto)
	{
		String tableName=dto.getAnnotation(Table.class).name();

		return tableName;
	}

	private static String IDColumnName(Class dto)
	{
		Field[] fields=dto.getDeclaredFields();
		String idColumnName="";

		for(Field field:fields)
		{
			if(field.isAnnotationPresent(Id.class)) idColumnName=field.getAnnotation(Column.class).name();
		}

		return idColumnName;
	}

	private static <T> void InvokeSetters(Object dto, ResultSet rs, Class dtoClass, T returnedObject)
	{
		Field[] fields = dtoClass.getDeclaredFields();
		Object valueColumn = null;
		Class<?> columnType;
		String attName = "";

		try
		{
			for(Field field:fields)
			{
				// Utilizar los setters para poner los valores a las respectivas propiedades
				attName = field.getName();
				columnType = field.getType();
				
				if(field.getAnnotation(Column.class) != null)
				{

					valueColumn = rs.getObject("a0" + field.getAnnotation(Column.class).name());
					SettersPrimitiveTypes(dto, attName, valueColumn, columnType);
				}
				else
				{
					if(field.getDeclaredAnnotation(JoinColumn.class) != null)
					{ 
						valueColumn = rs.getObject("a0"+ field.getAnnotation(JoinColumn.class).name());
						SettersPrimitiveTypes(dto,attName+"IdByteBuddy",valueColumn,int.class);
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	private static void SettersPrimitiveTypes(Object dto, String attName, Object value, Class<?> columnType)
	{
		try
		{
		   // dado el attName obtengo el nombre del setter
		   String mtdName = getSetterName(attName);
		   Class[] argsType = new Class[1];
		   Method[] mtds = dto.getClass().getDeclaredMethods();
		   Method mtd = null ;
		   
		   	try
		   	{
			   	// intento obtener el metodo...
		   		if(value != null){
		   			argsType[0] = value.getClass();
			   		mtd = dto.getClass().getMethod(mtdName,argsType);
		   		}
		   	}
		   	catch(NoSuchMethodException ex)
		   	{
			   	// fallo... pruebo con el tipo primitivo
			   	argsType[0] =_wrapperToType(columnType);
			   	mtd = dto.getClass().getMethod(mtdName,argsType);
			   	
			   	if(columnType.equals(boolean.class))
			   	{
			   		if ((int)value == 0) 
				   		value = false;
				   	else
				   		value = true;	
			   	}		   		
		   	}
		   if(mtd != null){
			   mtd.invoke(dto,value);
		   }
	    }
	    catch(Exception ex)
		{
	    	ex.printStackTrace();
	    	throw new RuntimeException(ex);
	    }
	}

	private static Class _wrapperToType(Class clazz)
	{
		if(clazz.equals(Byte.class)) return Byte.TYPE;
		if(clazz.equals(Short.class)) return Short.TYPE;
		if(clazz.equals(Integer.class)) return Integer.TYPE;
		if(clazz.equals(Long.class)) return Long.TYPE;
		if(clazz.equals(Character.class)) return Character.TYPE;
		if(clazz.equals(Float.class)) return Float.TYPE;
		if(clazz.equals(Double.class)) return Double.TYPE;
		return clazz;
	}

	private static String getSetterName(String attName)
	{
		//System.out.println("atributo: " + attName);
		String attNameUpperLetter = attName.substring(0, 1).toUpperCase() + attName.substring(1);
		return "set" + attNameUpperLetter;		
	}

}
