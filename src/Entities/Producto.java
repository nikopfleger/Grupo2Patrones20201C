package Entities;

import ann.Column;
import ann.Entity;
import ann.Id;
import ann.JoinColumn;
import ann.ManyToOne;
import Entities.Proveedor;
import ann.Table;
import Entities.Categoria;

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
   
   @ManyToOne
   @JoinColumn(name="id_categoria")
   private Categoria categoria;
   
   @Column(name="precio_unitario")
   double precioUnitario;
   
   @Column(name="unidades_stock")
   int unidadesStock;
   
   @Column(name="unidades_reposicion")
   int unidadesReposicion;
   
   @Column(name="flg_discontinuo")
   boolean flgDiscontinuo;
   
   public Categoria getCategoria()
   {
	   return this.categoria;
   }
   
   public void setCategoria(Categoria categoria)
   {
	   this.categoria = categoria;
   }
   
   public double getPrecioUnitario()
   {
	   return this.precioUnitario;
   }
   
   public void setPrecioUnitario(double precioUnitario)
   {
	   this.precioUnitario = precioUnitario;
   }
   
   public int getUnidadesStock()
   {
      return this.unidadesStock;
   }

   public void setUnidadesStock(int unidadesStock)
   {
      this.unidadesStock=unidadesStock;
   }

   public int getUnidadesReposicion()
   {
      return this.unidadesReposicion;
   }

   public void setUnidadesReposicion(int unidadesReposicion)
   {
      this.unidadesReposicion=unidadesReposicion;
   }
   
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
   
   public boolean getFlgDiscontinuo()
   {
	   return this.flgDiscontinuo;
   }
   
   public void setFlgDiscontinuo(boolean flgDiscontinuo)
   {
	   this.flgDiscontinuo = flgDiscontinuo;
   }

}
