package es.actividad2.unir.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.actividad2.unir.modelo.entidad.Libro;
import es.actividad2.unir.modelo.negocio.Crud;



//clase Abrir url y verbos
//Hay que darlo de alta como un objeto especial RestController

@RestController
public class ControladorCrud {
	
	@Autowired
	private Crud crud;
	
	@PostMapping(path="libros",consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> altaLibro(@RequestBody Libro l) {
		System.out.println("altaLibro: objeto libro: " +l );
		//daoLibro.add(l);
		crud.obtenerLibroPorId(1);
		return new ResponseEntity<Libro>(l,HttpStatus.OK);//201 CREATED
	}
	
}
