package builder;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import ann.ManyToOne;
import interceptor.MyInterceptor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.matcher.ElementMatchers;
import principal.Demo;

public class ClassBuilder
{
	public static void mejoraClase(Class<?> claseBase, Map<Class<?>, Class<?>> clasesMejoradas){
	        DynamicType.Builder<?> builder = new ByteBuddy().subclass(claseBase);

	        try {

	            Class<?> claseMejorada = clasesMejoradas.get(claseBase);

	            if (claseMejorada == null) {
	                for (Field field : claseBase.getDeclaredFields()) {
	                    if (field.isAnnotationPresent(ManyToOne.class)) {
	                        String name = field.getName();
	                        String camelCaseName = name.substring(0, 1).toUpperCase() + name.substring(1);

	                        String getterName = "get" + camelCaseName;

	                        builder = builder.method(ElementMatchers.named(getterName))
	                                .intercept(
	                                        MethodCall.invoke(MyInterceptor.class.getMethod("intercept", Field.class, Object.class))
	                                                .with(field).withThis()
	                                                .andThen(SuperMethodCall.INSTANCE)
	                                );
	                        String nameIdByteBuddy = name + "IdByteBuddy";
	                        builder = builder.defineProperty(nameIdByteBuddy, int.class);	                        
	                    }
	                }

	                claseMejorada = builder.make().load(Demo.class.getClassLoader()).getLoaded();

	                clasesMejoradas.put(claseBase, claseMejorada);
	            }


//	            DetalleOrden detalleOrden = (DetalleOrden) claseMejorada.getConstructors()[0].newInstance();

//	            detalleOrden.getOrden();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
