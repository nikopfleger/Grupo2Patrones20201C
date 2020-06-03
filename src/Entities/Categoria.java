package Entities;

import ann.Column;
import ann.Entity;
import ann.Id;
import ann.Table;

@Entity
@Table(name="categoria")
public class Categoria implements IByteBuddy
{
	@Id
	@Column(name="id_categoria")
	private int idCategoria;
	
	@Column(name="descripcion")
	private String descripcion;
	
	public int getIdCategoria()
    {
       return idCategoria;
    }

    public void setIdCategoria(int idCategoria)
    {
       this.idCategoria=idCategoria;
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
