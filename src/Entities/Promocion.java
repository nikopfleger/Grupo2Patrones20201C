package Entities;

import ann.Column;
import ann.Entity;
import ann.Id;
import ann.Table;

@Entity
@Table(name="promocion")
public class Promocion implements IByteBuddy
{

	@Id
	@Column(name="id_promocion")
	private int idPromocion;
	
	@Column(name="descripcion")
	private String descripcion;
	
	public String getDescripcion()
	{
		return this.descripcion;
	}
	
	public int getIdPromocion()
	{
		return this.idPromocion;
	}
	
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	
	public void setIdPromocion(int idPromocion)
	{
		this.idPromocion = idPromocion;
	}
}
