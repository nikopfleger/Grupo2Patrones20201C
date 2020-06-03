package Entities;

import ann.Column;
import ann.Entity;
import ann.Id;
import ann.JoinColumn;
import ann.ManyToOne;
import ann.Table;
import Entities.Producto;
import Entities.PromocionVigencia;

@Entity
@Table(name="promocion_producto")
public class PromocionProducto implements IByteBuddy
{
	
	@Id
	@Column(name="id_promocion_producto")
	private int idPromocionProducto;
	
	@ManyToOne
	@JoinColumn(name="id_promocion_vigencia")
	private PromocionVigencia promocionVigencia;
	
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;
	
	@Column(name="descuento")
	private float descuento;
	
	public int getIdPromocionProducto()
	{
		return this.idPromocionProducto;
	}
	
	public void setIdPromocionProducto(int idPromocionProducto)
	{
		this.idPromocionProducto = idPromocionProducto;
	}
	
	public PromocionVigencia getPromocionVigencia()
	{
		return this.promocionVigencia;
	}
	
	public Producto getProducto()
	{
		return this.producto;
	}
	
	public float getDescuento()
	{
		return this.descuento;
	}
	
	public void setPromocionVigencia(PromocionVigencia promocionVigencia)
	{
		this.promocionVigencia = promocionVigencia;
	}
	
	public void setProducto(Producto producto)
	{
		this.producto = producto;
	}
	
	public void setDescuento(float descuento)
	{
		this.descuento = descuento;
	}
	

}
