package Entities;

import ann.Column;
import ann.Entity;
import ann.Id;
import ann.Table;

@Entity
@Table(name="proveedor")
public class Proveedor
{
   @Id
   @Column(name="id_proveedor")
   private int idProveedor;
   
   @Column(name="empresa")
   private String empresa;

   public int getIdProveedor()
   {
      return idProveedor;
   }

   public void setIdProveedor(int idProveedor)
   {
      this.idProveedor=idProveedor;
   }

   public String getEmpresa()
   {
      return empresa;
   }

   public void setEmpresa(String empresa)
   {
      this.empresa=empresa;
   }


}
