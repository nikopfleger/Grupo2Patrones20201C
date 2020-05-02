package Entities;

import ann.Column;
import ann.Entity;
import ann.Id;

@Entity
public class Categoria
{

	@Id
	@Column(name="categoria_id")
	private int categoriaId;
	
	@Column(name="descripcion")
	private String descripcion;
	
}
