package Entities;

import ann.Column;
import ann.Entity;
import ann.Id;
import ann.ManyToOne;
import ann.Table;

@Entity
@Table(name="proveedor_categoria")
public class ProveedorCategoria
{
	@Id
	@Column(name="id_proveedor_categoria")
	private int idProveedorCategoria;
	
	@ManyToOne
	@Column(name="id_proveedor")
	private int idProveedor;
	
	@ManyToOne
	@Column(name="id_categoria")
	private int idCategoria;

	public int getIdProveedorCategoria()
	{
		return idProveedorCategoria;
	}

	public void setIdProveedorCategoria(int idProveedorCategoria)
	{
		this.idProveedorCategoria=idProveedorCategoria;
	}

	public int getIdProveedor()
	{
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor)
	{
		this.idProveedor=idProveedor;
	}

	public int getIdCategoria()
	{
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria)
	{
		this.idCategoria=idCategoria;
	}
	
}
