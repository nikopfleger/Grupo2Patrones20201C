package Entities;

import ann.Column;
import ann.Entity;
import ann.Id;
import ann.Table;

@Entity
@Table(name="categoria")
public class Categoria
{
	@Id
	@Column(name="categoria_id")
	private int categoriaId;
	
	@Column(name="descripcion")
	private String descripcion;
}
