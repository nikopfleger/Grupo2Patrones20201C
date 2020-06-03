package principal;

import java.lang.reflect.Field;

import Entities.Orden;
import net.bytebuddy.implementation.bind.annotation.This;

public class MyInterceptor
{
	public static void intercept(Field field, @This Object self) {
		field.setAccessible(true);
		
		try {
			Object campo = field.get(self);
			Class type = field.getType();
			
			if (campo==null) {
			 	Orden o = new Orden();
			 	o.setIdOrden(42);
			 	field.set(self, o);
			 }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
