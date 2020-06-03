package principal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import com.sun.javafx.collections.MappingChange.Map;

import Entities.*;
import ann.ManyToOne;
import ann.Table;
import myhibernate.MyHibernate;
import myhibernate.Query;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.implementation.bind.annotation.SuperMethod;
import net.bytebuddy.matcher.ElementMatchers;

public class Demo
{
   public static void main(String[] args)
   {
//      // primer caso: busqueda por id
//      Producto p = MyHibernate.find(Producto.class,1);
//      System.out.println(p.getDescripcion()+", "+p.getProveedor().getEmpresa());
//
//      // segundo caso: recuperar todas las filas
//      List<Producto> lst = MyHibernate.findAll(Producto.class);
//      for(Producto px:lst)
//      {
//         System.out.println(px.getDescripcion()+", "+px.getProveedor().getEmpresa());         
//      }
//      
//      // tercer caso: HQL
//      String hql="";
//      hql+="FROM Producto p ";
//      hql+="WHERE p.proveedor.empresa=:emp ";
//      Query q = MyHibernate.createQuery(hql);
//      q.setParameter("emp","Sony");
//      List<Producto> lst2 = q.getResultList();
//      for(Producto px:lst2)
//      {
//         System.out.println(px.getDescripcion()+", "+px.getProveedor().getEmpresa());         
//      }
	   
	   // Ejemplo Clase 20-05-2020
	   Class<?> claseBase = DetalleOrden.class;
	   DynamicType.Builder<?> builder = new ByteBuddy().subclass(claseBase);
	   int id = 1;
	   
	   HashMap<Class<?>, Class<?>> clasesMejoradas = new HashMap<>();
	   
	   try {
		   Class<?> claseMejorada = clasesMejoradas.get(claseBase);
		   
		   if (claseMejorada == null) {
			   for(Field field: claseBase.getDeclaredFields()) {
				   if (field.isAnnotationPresent(ManyToOne.class)) {
					   String name = field.getName();
					   String camelCaseName = name.substring(0, 1).toUpperCase() + name.substring(1);
					   
					   String getterName = "get" + camelCaseName;
					   
					   builder = builder.method(ElementMatchers.named(getterName))
							   .intercept(
									   MethodCall.invoke(MyInterceptor.class.getMethod("intercept", Field.class, int.class, Object.class))
									   .with(field, id)
									   .withThis()
									   .andThen(SuperMethodCall.INSTANCE)
								   );
				   }
			   }
			   claseMejorada = builder.make().load(Demo.class.getClassLoader()).getLoaded();
			   clasesMejoradas.put(claseBase, claseMejorada);
		   }

		   DetalleOrden detalleOrden = (DetalleOrden)claseMejorada.getConstructors()[0].newInstance();
		   
		   detalleOrden.getOrden();
	   } catch (Exception ex) {
		   ex.printStackTrace();
	   }
	   
	   PruebaFindEntidades();
	   
	   PruebaFindAllEntidades();
   }
   
   private static void PruebaFindAllEntidades()
   {
	   // Categoria
//	   List<Categoria> lstCat = MyHibernate.findAll(Categoria.class);
//       for(Categoria cx:lstCat)
//       {
//          System.out.println(cx.getIdCategoria()+ ", " + cx.getDescripcion());         
//       }
       
       //Cliente
//       List<Cliente> lstCli = MyHibernate.findAll(Cliente.class);
//       for(Cliente clx:lstCli)
//       {
//          System.out.println(clx.getNombre() + ", " + clx.getDireccion());
//          System.out.println(clx.getTipoCliente() + ", " + clx.getTipoCliente().getDescripcion());
//       }
       
	   // DetalleOrden
//	   List<DetalleOrden> lstDetOrd = MyHibernate.findAll(DetalleOrden.class);
//       for(DetalleOrden dox:lstDetOrd)
//       {
//          System.out.println(dox.getIdDetalleOrden() + ", " + dox.getCantidad());
//          System.out.println(dox.getOrden() + ", " + dox.getOrden().getIdOrden());
//          System.out.println(dox.getProducto() + ", " + dox.getProducto().getDescripcion());
//       }
       
	   // Empleado
//	   List<Empleado> lstEmp = MyHibernate.findAll(Empleado.class);
//	   for(Empleado ex:lstEmp)
//       {
//		   System.out.println(ex.getIdEmpleado() + ", " + ex.getNombre());
//       }
	   
	   // Orden
//	   List<Orden> lstOrd = MyHibernate.findAll(Orden.class);
//	   for(Orden ox:lstOrd)
//       {
//		   System.out.println(ox.getIdOrden() + ", " + ox.getFechaEntregada() + ", " + ox.getFechaGenerada());
//		   System.out.println(ox.getCliente() + ", " + ox.getCliente().getNombre());
//		   System.out.println(ox.getEmpleado() + ", " + ox.getEmpleado().getNombre());
//       }
	   
	   // Producto
//	   List<Producto> lstProd = MyHibernate.findAll(Producto.class);
//       for(Producto px:lstProd)
//       {
//          System.out.println(px.getDescripcion() + ", " + px.getProveedor().getEmpresa());         
//       }
	   
	// Promocion
//	   List<Promocion> lstProm = MyHibernate.findAll(Promocion.class);
//       for(Promocion prx:lstProm)
//       {
//	        System.out.println(prx.getIdPromocion() + ", " + prx.getDescripcion());         
//       }
	   
	   // PromocionProducto - ERROR
//	   List<PromocionProducto> lstPromProd = MyHibernate.findAll(PromocionProducto.class);
//       for(PromocionProducto prmpx:lstPromProd)
//       {
//	        System.out.println(prmpx.getIdPromocionProducto() + ", " + prmpx.getDescuento());
//	        System.out.println(prmpx.getProducto() + ", " + prmpx.getProducto().getDescripcion());
//	        System.out.println(prmpx.getPromocionVigencia() + ", " + prmpx.getPromocionVigencia().getIdPromocionVigencia());
//       }
	   
	   // PromocionVigencia
//	   List<PromocionVigencia> lstPromVig = MyHibernate.findAll(PromocionVigencia.class);
//       for(PromocionVigencia prmvig:lstPromVig)
//       {
//	        System.out.println(prmvig.getIdPromocionVigencia() + ", " + prmvig.getFechaInicio() + ", " + prmvig.getFechaFin());
//	        System.out.println(prmvig.getPromocion() + ", " + prmvig.getPromocion().getDescripcion());
//       }
	   
	   // Proveedor
//	   List<Proveedor> lstProv = MyHibernate.findAll(Proveedor.class);
//       for(Proveedor provx:lstProv)
//       {
//	        System.out.println(provx.getIdProveedor() + ", " + provx.getContacto() + ", " + provx.getDireccion() + ", " + provx.getEmpresa());
//       }
	   
	   // ProveedorCategoria
//	   List<ProveedorCategoria> lstProvCat = MyHibernate.findAll(ProveedorCategoria.class);
//       for(ProveedorCategoria provcatx:lstProvCat)
//       {
//	        System.out.println(provcatx.getIdProveedorCategoria() + ", " + provcatx.getCategoria().getDescripcion() + ", " + provcatx.getProveedor().getContacto());
//       }
	   
	   // TipoCliente
//	   List<TipoCliente> lstTipoCli = MyHibernate.findAll(TipoCliente.class);
//       for(TipoCliente tipoclix:lstTipoCli)
//       {
//	        System.out.println(tipoclix.getIdTipoCliente() + ", " + tipoclix.getDescripcion());
//       }
   }

   private static void PruebaFindEntidades() 
   {
	   // Categoria
//	   Categoria categoria = new Categoria();
//	   categoria = MyHibernate.find(Categoria.class,1);
//	   System.out.println(categoria.getIdCategoria());
//	   System.out.println(categoria.getDescripcion());
	   
	   // Cliente
//	   Cliente cliente = new Cliente();
//	   cliente = MyHibernate.find(Cliente.class,1);
//	   System.out.println(cliente.getIdCliente());
//	   System.out.println(cliente.getNombre());
//	   System.out.println(cliente.getDireccion());	   
//	   System.out.println(cliente.getTipoCliente());
//	   System.out.println(cliente.getTipoCliente().getDescripcion());

	   // DetalleOrden - ERROR
//	   DetalleOrden detalleOrden = new DetalleOrden();
//	   detalleOrden = MyHibernate.find(DetalleOrden.class,1);
//	   System.out.println(detalleOrden.getIdDetalleOrden());
//	   System.out.println(detalleOrden.getCantidad());
//	   System.out.println(detalleOrden.getProducto());
//	   System.out.println(detalleOrden.getProducto().getDescripcion());
//	   System.out.println(detalleOrden.getOrden().getCliente());
	   
	   // Empleado
//	   Empleado empleado = new Empleado();
//	   empleado = MyHibernate.find(Empleado.class,5);
//	   System.out.println(empleado.getIdEmpleado());
//	   System.out.println(empleado.getNombre());
//	   
//	   int counter = 1;
//	   while (empleado.getJefe() != null)
//	   {
//		   empleado = empleado.getJefe();
//		   System.out.println("Subempleado " + counter);
//		   System.out.println(empleado.getIdEmpleado());
//		   System.out.println(empleado.getNombre());
//		   counter++;
//	   }
	   
	   // Orden
//	   Orden orden = new Orden();
//	   orden = MyHibernate.find(Orden.class,1);
//	   System.out.println(orden.getIdOrden());
//	   System.out.println(orden.getFechaGenerada());
//	   System.out.println(orden.getFechaEntregada());
//	   System.out.println(orden.getCliente());
//	   System.out.println(orden.getCliente().getNombre());
//	   System.out.println(orden.getCliente().getTipoCliente().getDescripcion());
//	   System.out.println(orden.getEmpleado());
//	   System.out.println(orden.getEmpleado().getNombre());
	   
	   // Producto
//	   Producto producto = new Producto();
//	   producto = MyHibernate.find(Producto.class,1);
//	   System.out.println(producto.getIdProducto());
//	   System.out.println(producto.getDescripcion());
//	   System.out.println(producto.getProveedor());
//	   System.out.println(producto.getProveedor().getContacto());
//	   System.out.println(producto.getCategoria());
//	   System.out.println(producto.getCategoria().getDescripcion());
//	   System.out.println(producto.getPrecioUnitario());
//	   System.out.println(producto.getUnidadesStock());
//	   System.out.println(producto.getUnidadesReposicion());
	   
	   // Promocion
//	   Promocion promo = new Promocion();
//	   promo = MyHibernate.find(Promocion.class,1);
//	   System.out.println(promo.getIdPromocion());
//	   System.out.println(promo.getDescripcion());
	   
	   // PromocionProducto - ERROR
//	   PromocionProducto promoProducto = new PromocionProducto();
//	   promoProducto = MyHibernate.find(PromocionProducto.class,1);
//	   System.out.println(promoProducto.getIdPromocionProducto());
//	   System.out.println(promoProducto.getPromocionVigencia());
//	   System.out.println(promoProducto.getPromocionVigencia().getFechaFin());
//	   System.out.println(promoProducto.getProducto());
//	   System.out.println(promoProducto.getProducto().getDescripcion());
	   
	   // PromocionVigencia
//	   PromocionVigencia promoVigencia = new PromocionVigencia();
//	   promoVigencia = MyHibernate.find(PromocionVigencia.class,10);
//	   System.out.println(promoVigencia.getIdPromocionVigencia());
//	   System.out.println(promoVigencia.getFechaInicio());
//	   System.out.println(promoVigencia.getPromocion().getDescripcion());

	   // Proveedor
//	   Proveedor proveedor = new Proveedor();
//	   proveedor = MyHibernate.find(Proveedor.class,1);
//	   System.out.println(proveedor.getIdProveedor());
//	   System.out.println(proveedor.getEmpresa());
//	   System.out.println(proveedor.getContacto());
//	   System.out.println(proveedor.getDireccion());
	   
	   // ProveedorCategoria
//	   ProveedorCategoria proveedorCategoria = new ProveedorCategoria();
//	   proveedorCategoria = MyHibernate.find(ProveedorCategoria.class,1);
//	   System.out.println(proveedorCategoria.getIdProveedorCategoria());
//	   System.out.println(proveedorCategoria.getProveedor());
//	   System.out.println(proveedorCategoria.getProveedor().getContacto());
//	   System.out.println(proveedorCategoria.getCategoria());
//	   System.out.println(proveedorCategoria.getCategoria().getDescripcion());
	   
	   // TipoCliente
//	   TipoCliente tipoCliente = new TipoCliente();
//	   tipoCliente = MyHibernate.find(TipoCliente.class,1);
//	   System.out.println(tipoCliente.getIdTipoCliente());
//	   System.out.println(tipoCliente.getDescripcion());
   }
}
