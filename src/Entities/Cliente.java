package Entities;

import ann.Column;
import ann.Entity;
import ann.Id;
import ann.JoinColumn;
import ann.ManyToOne;
import Entities.TipoCliente;
import ann.Table;

@Entity
@Table(name="cliente")
public class Cliente
{
   @Id
   @Column(name="id_cliente")
   private int idCliente;
   
   @Column(name="nombre")
   private String nombre;

   @Column(name="direccion")
   private String direccion;
   
   @ManyToOne
   @JoinColumn(name="id_tipo_cliente")
   private TipoCliente tipoCliente;
   
   private int tipoClienteIdByteBuddy;

   public int getIdCliente()
   {
      return idCliente;
   }

   public void setIdCliente(int idCliente)
   {
      this.idCliente=idCliente;
   }

   public String getNombre()
   {
      return nombre;
   }

   public void setNombre(String nombre)
   {
      this.nombre=nombre;
   }

   public String getDireccion()
   {
      return direccion;
   }

   public void setDireccion(String direccion)
   {
      this.direccion=direccion;
   }
   
   public TipoCliente getTipoCliente()
   {
      return tipoCliente;
   }
   
   public void setTipoCliente(TipoCliente tipoCliente)
   {
	   this.tipoCliente=tipoCliente;
   }

   public int getTipoClienteIdByteBuddy()
   {
	   	return tipoClienteIdByteBuddy;
   }

   public void setTipoClienteIdByteBuddy(int tipoClienteIdByteBuddy)
   {
	   this.tipoClienteIdByteBuddy = tipoClienteIdByteBuddy;
   }

}
