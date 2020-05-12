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
	private Proveedor proveedor;
	
	@ManyToOne
	@Column(name="id_categoria")
	private Categoria categoria;

	public int getIdProveedorCategoria()
	{
		return idProveedorCategoria;
	}

	public void setIdProveedorCategoria(int idProveedorCategoria)
	{
		this.idProveedorCategoria=idProveedorCategoria;
	}

	public Proveedor getProveedor()
	{
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor)
	{
		this.proveedor=proveedor;
	}

	public Categoria getCategoria()
	{
		return categoria;
	}

	public void setCategoria(Categoria categoria)
	{
		this.categoria = categoria;
	}
	
}
