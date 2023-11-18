package serviciorest.servidor.controlador;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import serviciorest.servidor.modelo.entidad.Libro;
import serviciorest.servidor.modelo.persistencia.DaoLibro;

@RestController
public class ControladorLibro {
	

	@Autowired
	private DaoLibro daoLibro;
	
	
	
	//1.Dar de alta un libro
	//POST 
	//"http://localhost:8080/libros" y el metodo a usar seria POST
	//Pasandole la persona sin el ID dentro del body del HTTP request
	
	@PostMapping(path="libros",consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> altaLibro(@RequestBody Libro l) {
		
		Libro libroNuevo = daoLibro.add(l);
		if(libroNuevo != null) {
			System.out.println("altaLibro: objeto libro: " + l);
			return new ResponseEntity<Libro>(l,HttpStatus.OK);//200 OK
		}else {
			System.out.println("No se ha dado de alta el libro: " + l);
			return new ResponseEntity<Libro>(HttpStatus.BAD_REQUEST);//404 NOT FOUND
		}
	}
	
	//2.Dar de baja un libro por ID
	
	//DELETE
	//Aqui vamos a borar una persona a traves de un ID que le pasemos en el
	//PATH.
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8080/personas/ID" y el metodo a usar seria DELETE
	@DeleteMapping(path="libros/{id}")
	public ResponseEntity<Libro> borrarLibro(@PathVariable("id") int id) {
		System.out.println("ID a borrar: " + id);
		Libro l = daoLibro.delete(id);
		if(l != null) {
			return new ResponseEntity<Libro>(l,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	//3. Modificar un libro por ID
	
	//PUT
	//Si todo ha ido bien devolvemos el codigo de respuesta de 200 OK,
	//si id de la persona no existe devolvemos 404 NOT FOUND
	
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8080/libros/ID" y el metodo a usar seria PUT
	//Pasandole el libro sin el ID dentro del body del HTTP request
	@PutMapping(path="libros/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> modificarLibro(
			@PathVariable("id") int id, 
			@RequestBody Libro l) {
		System.out.println("ID a modificar: " + id);
		System.out.println("Datos a modificar: " + l);
		l.setId(id);
		Libro pUpdate = daoLibro.update(l);
		if(pUpdate != null) {
			return new ResponseEntity<Libro>(HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	//4. Obtener un libro por ID

	//GET PERSONA POR ID
	//el codigo 200 "OK" si existe o 404 NOT FOUND si no existe
	
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8080/personas/ID" y el metodo a usar seria GET
	//ID sería el identificador que queremos buscar
	@GetMapping(path="libros/{id}",produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Libro> getPersona(@PathVariable("id") int id) {
		System.out.println("Buscando libro con id: " + id);
		Libro l = daoLibro.get(id);
		if(l != null) {
			return new ResponseEntity<Libro>(l,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	//5. Listar todos los libros
	
	//GET LISTA PERSONAS
	
	//La URL para acceder a este metodo en caso de querer todas las personas
	//sería: 
	//"http://localhost:8080/libros" y el metodo a usar seria GET
	@GetMapping(path="libros",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> listaLibros(
			@RequestParam(name="nombre",required=false) String nombre) {
		List<Libro> listaLibros = null;
		//Si no me viene el nombre, devolvemos toda la lista
		if(nombre == null) {
			System.out.println("Listando los libros");
			listaLibros = daoLibro.list();			
		}
		System.out.println(listaLibros);
		return new ResponseEntity<List<Libro>>(listaLibros,HttpStatus.OK);
	}
	
	//PUT
	// Modificar libro
	//Si todo ha ido bien devolvemos el codigo de respuesta de 200 OK,
	//si id de la persona no existe devolvemos 404 NOT FOUND
	
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8080/libros/ID" y el metodo a usar seria PUT
	//Pasandole la persona sin el ID dentro del body del HTTP request
	@PutMapping(path="personas/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> modificarPersona(
			@PathVariable("id") int id, 
			@RequestBody Libro p) {
		System.out.println("ID a modificar: " + id);
		System.out.println("Datos a modificar: " + p);
		p.setId(id);
		Libro pUpdate = daoLibro.update(p);
		if(pUpdate != null) {
			return new ResponseEntity<Libro>(HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	//DELETE
	//Aqui vamos a borar un libro a traves de un ID que le pasemos en el
	//PATH.
	
	//Si todo ha ido bien devolvemos el codigo de respuesta de 200 OK y
	//devolvemos la persona que hemos borrado
	
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8080/libro/ID" y el metodo a usar seria DELETE
	@DeleteMapping(path="personas/{id}")
	public ResponseEntity<Libro> borrarPersona(@PathVariable("id") int id) {
		System.out.println("ID a borrar: " + id);
		Libro p = daoLibro.delete(id);
		if(p != null) {
			return new ResponseEntity<Libro>(p,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}

}
