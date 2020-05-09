package Entities;

import ann.Column;
import ann.Entity;
import ann.Id;
import ann.JoinColumn;
import ann.ManyToOne;
import ann.Table;

import java.sql.Date;

import Entities.Promocion;

@Entity
@Table(name="promocion_vigencia")
public class PromocionVigencia
{
	@Id
	@Column(name="id_promocion_vigencia")
	private int idPromocionVigencia;
	
	@ManyToOne
	@JoinColumn(name="id_promocion")
	Promocion promocion;
	
	@Column(name="fecha_inicio")
	Date fechaInicio;
	
	@Column(name="fecha_fin")
	Date fechaFin;
	
	public int getIdPromocionVigencia()
	{
		return this.idPromocionVigencia;
	}
	
	public Promocion getPromocion()
	{
		return this.promocion;
	}
	
	public Date getFechaInicio()
	{
		return this.fechaInicio;
	}
	
	public Date getFechaFin()
	{
		return this.fechaFin;
	}
	
	public void setIdPromocionVigencia(int promocionVigencia)
	{
		this.idPromocionVigencia = promocionVigencia;
	}
	
	public void setPromocion(Promocion promocion)
	{
		this.promocion = promocion;
	}
	
	public void setFechaInicio(Date fechaInicio)
	{
		this.fechaInicio = fechaInicio;
	}
	
	public void setFechaFin(Date fechaFin)
	{
		this.fechaFin = fechaFin;
	}
}
