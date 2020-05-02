package Entities;

import ann.Column;
import ann.Entity;
import ann.Id;
import ann.Table;

@Entity
@Table(name="tipo_cliente")
public class TipoCliente
{
	@Id
	@Column(name="id_tipo_cliente")
	private int id_tipo_cliente;
	
	@Column(name="descripcion")
	private String descripcion;
	
	public int getIdTipoCliente()
	{
		return id_tipo_cliente;
	}

	public void setIdTipoCliente(int id_tipo_cliente)
	{
		this.id_tipo_cliente=id_tipo_cliente;
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion=descripcion;
	}
}
