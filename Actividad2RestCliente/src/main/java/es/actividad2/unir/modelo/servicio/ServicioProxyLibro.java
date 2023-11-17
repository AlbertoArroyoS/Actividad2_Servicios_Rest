package es.actividad2.unir.modelo.servicio;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.actividad2.unir.modelo.entidad.Libro;
@Service
public class ServicioProxyLibro {
	
	//La URL base del servicio REST de personas
	public static final String URL = "http://localhost:8080/libros/";
	
	//Inyectamos el objeto de tipo RestTemplate que nos ayudará
	//a hacer las peticiones HTTP al servicio REST
	
	@Autowired
	private RestTemplate restTemplate;
	
	//1.Dar de alta un libro
	public Libro alta(Libro l){
		try {
			//Para hacer un post de una entidad usamos el metodo postForEntity
			//El primer parametro la URL
			//El segundo parametros la persona que ira en body
			//El tercer parametro el objeto que esperamos que nos envie el servidor
			ResponseEntity<Libro> re = restTemplate.postForEntity(URL, l, Libro.class);
			System.out.println("alta -> Codigo de respuesta " + re.getStatusCode());
			return re.getBody();
		} catch (HttpClientErrorException e) {//Errores 4XX
			System.out.println("alta -> El libro NO se ha dado de alta, id: " + l);
		    System.out.println("alta -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	//2.Dar de baja un libro por ID
	
	public boolean borrar(int id){
		try {
			//El metodo delete tampoco devuelve nada, por lo que si no 
			//ha podido borrar el id, daría un excepcion
			//Ej http://localhost:8080/personas/1 DELETE
			restTemplate.delete(URL + id);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("borrar -> El libro NO se ha borrado, id: " + id);
		    System.out.println("borrar -> Codigo de respuesta: " + e.getStatusCode());
		    return false;
		}
	}
	
	//3.Modificar un libro por ID
	
	public boolean modificar(Libro l){
		try {
			//El metodo put de Spring no devuelve nada
			//si no da error se ha dado de alta y si no daria una 
			//excepcion
			restTemplate.put(URL + l.getId(), l, Libro.class);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("modificar -> El libro NO se ha modificado, id: " + l.getId());
		    System.out.println("modificar -> Codigo de respuesta: " + e.getStatusCode());
		    return false;
		}
	}
	
	//4.Obtener un libro por ID
	
	public Libro obtener(int id){
		try {
			//Como el servicio trabaja con objetos ResponseEntity, nosotros 
			//tambien podemos hacerlo en el cliente
			//Ej http://localhost:8080/personas/1 GET
			ResponseEntity<Libro> re = restTemplate.getForEntity(URL + id, Libro.class);
			HttpStatus hs= re.getStatusCode();
			if(hs == HttpStatus.OK) {	
				//Si la persona existe, la persona viene en formato JSON en el body
				//Al ser el objeto ResponseEntity de tipo Persona, al obtener el 
				//body me lo convierte automaticamente a tipo Persona
				//(Spring utiliza librerías por debajo para pasar de JSON a objeto)
				return re.getBody();
			}else {
				System.out.println("obtener -> Respuesta no contemplada");
				return null;
			}
		}catch (HttpClientErrorException e) {//Errores 4XX
			System.out.println("obtener -> El libro NO se ha encontrado, id: " + id);
		    System.out.println("obtener -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	
	//5.Listar todos los libros
	
	public List<Libro> listar(String nombre){
		String queryParams = "";		
		if(nombre != null) {
			queryParams += "?nombre=" + nombre;
		}
		
		try {
			//Ej http://localhost:8080/personas?nombre=harry GET
			ResponseEntity<Libro[]> response =
					  restTemplate.getForEntity(URL + queryParams,Libro[].class);
			Libro[] arrayLibros = response.getBody();
			return Arrays.asList(arrayLibros);//convertimos el array en un ArrayList
		} catch (HttpClientErrorException e) {
			System.out.println("listar -> Error al obtener la lista de libros");
		    System.out.println("listar -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}

}
