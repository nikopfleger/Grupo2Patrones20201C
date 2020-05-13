package Entities;

import ann.Column;
import ann.Entity;
import ann.Id;
import ann.JoinColumn;
import ann.ManyToOne;
import ann.Table;

@Entity
@Table(name="detalle_orden")
public class DetalleOrden
{
	@Id
	@Column(name="id_detalle_orden")
	private int idDetalleOrden;
	
	@Column(name="cantidad")
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name="id_orden")
	private Orden orden;
	
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;
	
	public int getIdDetalleOrden()
	{
		return idDetalleOrden;
	}

	public void setIdDetalleOrden(int idDetalleOrden)
	{
		this.idDetalleOrden=idDetalleOrden;
	}
	
	public int getCantidad()
	{
		return cantidad;
	}

	public void setCantidad(int cantidad)
	{
		this.cantidad=cantidad;
	}
	
	public Orden getOrden()
	{
		return orden;
	}

	public void setOrden(Orden orden)
	{
		this.orden=orden;
	}
	
	public Producto getProducto()
	{
		return producto;
	}

	public void setProducto(Producto producto)
	{
		this.producto=producto;
	}
}
