package Entities;

import ann.Column;
import ann.Entity;
import ann.Id;
import ann.JoinColumn;
import ann.ManyToOne;
import ann.Table;

@Entity
@Table(name="empleado")
public class Empleado implements IByteBuddy
{
	@Id
	@Column(name="id_empleado")
	private int idEmpleado;
	
	@Column(name="nombre")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="id_jefe")
	private Empleado jefe;
	
	private int jefeIdByteBuddy;
	
	public int getIdEmpleado()
	{
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado)
	{
		this.idEmpleado=idEmpleado;
	}
	
	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre=nombre;
	}
	
	public Empleado getJefe()
	{
		return jefe;
	}

	public void setJefe(Empleado jefe)
	{
		this.jefe=jefe;
	}

	public int getJefeIdByteBuddy()
	{
		return jefeIdByteBuddy;
	}

	public void setJefeIdByteBuddy(int jefeIdByteBuddy)
	{
		this.jefeIdByteBuddy = jefeIdByteBuddy;
	}
}
