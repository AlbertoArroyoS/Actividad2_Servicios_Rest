package es.actividad2.unir.modelo.entidad;

import java.util.Objects;

import org.springframework.stereotype.Component;

//Javabeans o pojo

@Component
public class Libro {
	private int id;
	private String titulo;
	private String editorial;
	private int nota;
	
	
	
	
	public Libro() {
		super();
	}
	public Libro(int id, String titulo, String editorial, int nota) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.editorial = editorial;
		this.nota = nota;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", editorial=" + editorial + ", nota=" + nota + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, titulo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return id == other.id && Objects.equals(titulo, other.titulo);
	}
	
	
	
	
}
