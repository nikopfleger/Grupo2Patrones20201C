package Entities;

import ann.Column;
import ann.Entity;
import ann.Id;
import ann.JoinColumn;
import ann.ManyToOne;
import Entities.Proveedor;
import ann.Table;

@Entity
@Table(name="producto")
public class Producto
{
   @Id
   @Column(name="id_producto")
   private int idProducto;
   
   @Column(name="descripcion")
   private String descripcion;

   @ManyToOne
   @JoinColumn(name="id_proveedor")
   private Proveedor proveedor;

   public int getIdProducto()
   {
      return idProducto;
   }

   public void setIdProducto(int idProducto)
   {
      this.idProducto=idProducto;
   }

   public String getDescripcion()
   {
      return descripcion;
   }

   public void setDescripcion(String descripcion)
   {
      this.descripcion=descripcion;
   }

   public Proveedor getProveedor()
   {
      return proveedor;
   }

   public void setProveedor(Proveedor proveedor)
   {
      this.proveedor=proveedor;
   }

}
