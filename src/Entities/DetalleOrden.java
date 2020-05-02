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
	private int id_detalle_orden;
	
	@Column(name="cantidad")
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name="id_orden")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;
	
	public int getIdDetalleOrden()
	{
		return id_detalle_orden;
	}

	public void setIdDetalleOrden(int id_detalle_orden)
	{
		this.id_detalle_orden=id_detalle_orden;
	}
	
	public int getCantidad()
	{
		return cantidad;
	}

	public void setCantidad(int cantidad)
	{
		this.cantidad=cantidad;
	}
	
	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente(Cliente cliente)
	{
		this.cliente=cliente;
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
