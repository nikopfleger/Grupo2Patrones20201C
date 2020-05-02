package Entities;

import java.sql.Date;
import ann.Column;
import ann.Entity;
import ann.Id;
import ann.JoinColumn;
import ann.ManyToOne;
import ann.Table;
import Entities.Cliente;
import Entities.Empleado;


@Entity
@Table(name="orden")
public class Orden
{
	@Id
	@Column(name="id_orden")
	private int idOrden;
   
	@Column(name="fecha_generada")
	private Date fechaGenerada;

	@Column(name="fecha_entregada")
	private Date fechaEntregada;
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="id_empleado")
	private Empleado empleado;
	
	public int getIdOrden()
	{
		return idOrden;
	}

	public void setIdOrden(int idOrden)
	{
		this.idOrden=idOrden;
	}
	
	public Date getFechaGenerada()
	{
		return fechaGenerada;
	}

	public void setFechaGenerada(Date fechaGenerada)
	{
		this.fechaGenerada=fechaGenerada;
	}
	
	public Date getFechaEntregada()
	{
		return fechaEntregada;
	}

	public void setFechaEntregada(Date fechaEntregada)
	{
		this.fechaEntregada=fechaEntregada;
	}
	
	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente(Cliente cliente)
	{
		this.cliente=cliente;
	}
	
	public Empleado getEmpleado()
	{
		return empleado;
	}

	public void setEmpleado(Empleado empleado)
	{
		this.empleado=empleado;
	}
}
