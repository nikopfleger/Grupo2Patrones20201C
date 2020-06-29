package interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import myhibernate.MyHibernate;
import net.bytebuddy.implementation.bind.annotation.This;

public class MyInterceptor
{
	public static void intercept(Field field, @This Object self) {
        field.setAccessible(true);

        try {
            Object campo = field.get(self);
            Class type = field.getType();

            if (campo == null) {
            	Class clasePadre = self.getClass();
            	String methodName = armaGetter(field.getName());
            	Method mtd = clasePadre.getMethod(methodName);
            	int id = (int) mtd.invoke(self);
                field.set(self, MyHibernate.find(type,id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private static String armaGetter(String name){
		String camelCaseName = name.substring(0, 1).toUpperCase() + name.substring(1);
		return "get"+camelCaseName+"IdByteBuddy";
	}
}
