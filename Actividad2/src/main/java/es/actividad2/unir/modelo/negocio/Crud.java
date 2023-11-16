package es.actividad2.unir.modelo.negocio;

import java.util.List;

import org.springframework.stereotype.Component;

import es.actividad2.unir.modelo.entidad.Libro;

//Aqui la logica de negocio

@Component
public class Crud {
	
	//metodos para hacer las tareas
	//los metodos son siempre verbos
	
	public Libro altaLibro (Libro libro) {
		return null;
		
	}
	
	public Libro bajaLibro (int id) {
			
			return null;
			
	}
	
	public Libro modificarLibro (int id) {
		
		return null;
		
	}
	
	public Libro obtenerLibroPorId (int id) {
		Libro libro2 = new Libro(1,"prueba","editorial",9);
		return libro2;
		
	}
	
	public List<Libro> listarLibros (){
		
		return null;
		
	}
	
}
