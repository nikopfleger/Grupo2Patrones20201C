package principal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import Entities.Cliente;
import Entities.Empleado;
import Entities.Orden;
import Entities.Producto;
import Entities.Promocion;
import Entities.TipoCliente;
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
	   
//       Promocion promo = new Promocion();
//       promo = MyHibernate.find(Promocion.class,1);
//    
//	   System.out.println(promo.getIdPromocion());
//	   System.out.println(promo.getDescripcion());
//	   
//	   Producto product = new Producto();
//	   product = MyHibernate.find(Producto.class,1);
//	   
//	   System.out.println(product.getProveedor().getIdProveedor());
//	   System.out.println(product.getProveedor().getDireccion());
//	   System.out.println(product.getProveedor().getEmpresa());
//	   System.out.println(product.getProveedor().getContacto());
   
//	   Cliente cliente = new Cliente();
//	   cliente = MyHibernate.find(Cliente.class,1);
//	   
//	   System.out.println(cliente.getDireccion());
//	   System.out.println(cliente.getIdCliente());
//	   System.out.println(cliente.getNombre());
//	   System.out.println(cliente.getTipoCliente());
//	   System.out.println(cliente.getTipoCliente().getDescripcion());
//	   
//	   TipoCliente tipoCliente = new TipoCliente();
//	   tipoCliente = MyHibernate.find(TipoCliente.class,1);
//	   
//	   System.out.println(tipoCliente.getIdTipoCliente());
//	   System.out.println(tipoCliente.getDescripcion());
	   
	   Empleado empleado = new Empleado();
	   empleado = MyHibernate.find(Empleado.class,5);
	   
	   System.out.println(empleado.getIdEmpleado());
	   System.out.println(empleado.getNombre());
	   int counter = 1;
	   while (empleado.getEmpleado() != null)
	   {
		   
		   empleado = empleado.getEmpleado();
		   System.out.println("Subempleado " + counter);
		   System.out.println(empleado.getIdEmpleado());
		   System.out.println(empleado.getNombre());
		   counter++;
		   
	   }

	   

	   
//	   Orden orden = new Orden();
//	   orden = MyHibernate.find(Orden.class,1);
//	   
//	   System.out.println(orden.getCliente());
//	   System.out.println(orden.getCliente().getNombre());
//	   System.out.println(orden.getCliente().getTipoCliente().getDescripcion());
   }
}
