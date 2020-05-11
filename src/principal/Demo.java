package principal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import Entities.Producto;
import Entities.Promocion;
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
       Promocion promo = new Promocion();
       promo = MyHibernate.find(Promocion.class,1);
    
	   System.out.println(promo.getIdPromocion());
	   System.out.println(promo.getDescripcion());
	   
   }
}
