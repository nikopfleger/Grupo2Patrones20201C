package principal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import Entities.*;
import ann.Table;
import myhibernate.MyHibernate;
import myhibernate.Query;

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
	   
	   PruebaFindEntidades();
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
//	   System.out.println(detalleOrden.getCliente());
//	   System.out.println(detalleOrden.getCliente().getNombre());
//	   System.out.println(detalleOrden.getProducto());
//	   System.out.println(detalleOrden.getProducto().getDescripcion());
	   
	   // Empleado
//	   Empleado empleado = new Empleado();
//	   empleado = MyHibernate.find(Empleado.class,5);
//	   System.out.println(empleado.getIdEmpleado());
//	   System.out.println(empleado.getNombre());
//	   
//	   int counter = 1;
//	   while (empleado.getEmpleado() != null)
//	   {
//		   empleado = empleado.getEmpleado();
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
	   
	   // PromocionVigencia - ERROR
//	   PromocionVigencia promoVigencia = new PromocionVigencia();
//	   promoVigencia = MyHibernate.find(PromocionVigencia.class,1);
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
	   
	   // ProveedorCategoria - ERROR
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
