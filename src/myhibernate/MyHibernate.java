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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import Entities.Producto;
import ann.Column;
import ann.Entity;
import ann.Id;
import ann.JoinColumn;
import ann.ManyToOne;
import ann.Table;
import builder.ClassBuilder;
import database.DBManager;

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
		Reflections reflections = new Reflections("Entities"); 
		Set<Class<?>> entities = 
		    reflections.getTypesAnnotatedWith(ann.Entity.class);
		Map<String, Class<?>> aliases = new HashMap<>();
		
		String query = "";
		
		List<String> hqlDecomp = Arrays.asList(hql.split(" "));
		
		List<String> sqlKeyWords = Arrays.asList("from","where","join");
		
		List<Integer> indexKeyWords = new ArrayList<Integer>();
		
//		for (int i = 0; i < hqlDecomp.size(); i++) {
//	        if (sqlKeyWords.contains(hqlDecomp.get(i).toLowerCase())) {
//	            indexKeyWords.add(i);
//	        }
//	    }
		
		String queryFrom = buildQueryFrom(hqlDecomp, entities, aliases);
		String queryJoin = buildQueryJoin(hqlDecomp, entities, aliases);
		String queryWhere = buildQueryWhere(hqlDecomp, entities, aliases);
		
		System.out.println(queryFrom + queryJoin + queryWhere);
	
		return new Query(queryFrom + queryJoin + queryWhere);
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

	private static String buildQueryFrom(List<String> hqlDecomp, Set<Class<?>> entities, Map<String, Class<?>> aliases) {
		String queryFrom = "";
		int indexFrom = hqlDecomp.indexOf("FROM");
		String alias = hqlDecomp.get(indexFrom + 2);
		
		Class<?> clazz = EntityClassFromString(hqlDecomp, entities);
		queryFrom = "FROM " + GetTableName(clazz) + " ";
		queryFrom += alias;
		aliases.put(alias, clazz);
		
		return queryFrom;
	}
	
	private static String buildQueryJoin(List<String> hqlDecomp, Set<Class<?>> entities,
			Map<String, Class<?>> aliases) {
		String queryJoin = "";

		int indexJoin = hqlDecomp.indexOf("JOIN");
		int indexWhere = hqlDecomp.indexOf("WHERE");
		

		List<String> joinClause = hqlDecomp.subList(indexJoin, indexWhere);
		try {
			while (joinClause.indexOf("JOIN") != -1) {
				System.out.println(joinClause);
				List<String> toJoin = Arrays.asList(joinClause.get(1).split("\\."));
				String alias = toJoin.get(0);
				String atribute = toJoin.get(1);
				Class<?> clazz = aliases.get(alias);
				Field field = clazz.getDeclaredField(atribute);
				String campo = field.getAnnotation(ann.JoinColumn.class).name();
				Class<?> classToJoin = field.getType();
				String tableToJoin = classToJoin.getAnnotation(ann.Table.class).name();
				String idColumnToJoin = GetIdColumn(classToJoin);
				String aliasJoin = joinClause.get(3);
				aliases.put(aliasJoin, classToJoin);
				queryJoin += "LEFT JOIN " + tableToJoin+" AS " + aliasJoin + " ON " 
								+ alias + "." + campo + " = " + aliasJoin + "." + idColumnToJoin + " ";
				
//				WHERE c.descripcion = :desc
				
//				LEFT JOIN tabla as aliasJoin ON alias.campo = aliasJoin.idColumnToJoin
				if(joinClause.size() > 4){
					joinClause = joinClause.subList(4, joinClause.size());
				}else{
					joinClause = new ArrayList<String>();
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(queryJoin);
		return queryJoin;
	}
	
	private static String buildQueryWhere(List<String> hqlDecomp, Set<Class<?>> entities, Map<String, Class<?>> aliases) {
		String queryWhere = "WHERE ";
		Class<?> clazz = EntityClassFromString(hqlDecomp, entities);
		
		// Ver en caso de que si hay AND dividir por esta palabra y hacer un foreach por cada division del AND
		int indexWhere = hqlDecomp.indexOf("WHERE");
		List<String> whereClause = hqlDecomp.subList(indexWhere, hqlDecomp.size());
		String tableName = "";
		String fieldName = "";
		String idName = "";
		String joinFields = "";
		
		int counter = 1;
		while (whereClause.indexOf("WHERE") != -1 || whereClause.indexOf("AND") != -1)
		{
			String entityProp = whereClause.get(1);
			List<String> entityPropDecomp = Arrays.asList(entityProp.split("\\."));
			if (entityPropDecomp.size() > 2) {
				Field[] fields = clazz.getDeclaredFields();
				String alias = entityPropDecomp.get(1);

				
				for (int i=0; i<fields.length; i++)
		        {
					System.out.print(fields[i].getName() + "\n");
					if (fields[i].getName().equals(entityPropDecomp.get(1))) {
						if(fields[i].isAnnotationPresent(JoinColumn.class))
						{
							String columnIdFK = fields[i].getAnnotation(JoinColumn.class).name();
							tableName=fields[i].getType().getDeclaredAnnotation(Table.class).name();
							idName=GetIdColumn(fields[i].getType());
							// Crear JOIN y devolver JOIN
							joinFields += "LEFT JOIN " + tableName + " AS " + "a" + counter + " ON " + alias + "." + columnIdFK + " = " + "a" + counter + "." + idName + " ";
						} 
						else if(fields[i].isAnnotationPresent(Column.class)) {
							fieldName=fields[i].getName();
							// Devolver nombre del campo
						}
					}
		        }
				counter++;
				if(whereClause.size() > 4){
					whereClause = whereClause.subList(whereClause.indexOf("AND"), whereClause.size());
				}else{
					whereClause = new ArrayList<String>();
				}
				queryWhere += "a" + counter + "." + entityPropDecomp.get(2) + " " + whereClause.get(2) + " " + whereClause.get(3) + " " + "AND ";

			}
			else 
			{
				try {
					String alias = entityPropDecomp.get(0);
					Class<?> clase = aliases.get(alias);
					Field field = clase.getDeclaredField(entityPropDecomp.get(1));
					String columnName = field.getDeclaredAnnotation(Column.class).name();
					queryWhere += alias + "." + columnName + " " + whereClause.get(2) + " " + whereClause.get(3) + " " + "AND ";
					if(whereClause.size() > 4){
						whereClause = whereClause.subList(whereClause.indexOf("AND"), whereClause.size());
					}else{
						whereClause = new ArrayList<String>();
					}
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		};
		
		queryWhere = queryWhere.substring(0, queryWhere.length() - 4);
		
//		queryFrom = "WHERE " + " ";
		
		return joinFields + queryWhere;
	}
	
	private static Class EntityClassFromString(List<String> hqlDecomp, Set<Class<?>> entities)
	{
		int indexFrom = hqlDecomp.indexOf("FROM");
		String entity = hqlDecomp.get(indexFrom + 1);
		Class<?> clazz = entities.stream().filter(c -> c.getSimpleName().equals(entity)).findFirst().orElse(null);
		
		return clazz;
	}
	
	private static String GetIdColumn(Class<?> clazz){
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			if(field.isAnnotationPresent(ann.Id.class)){
				return field.getDeclaredAnnotation(ann.Column.class).name();
			}
		}
		return "";
	}
}
