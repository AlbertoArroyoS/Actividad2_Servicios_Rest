package es.actividad2.unir.modelo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicioProxyMensaje {

	//La URL base del servicio REST
	public static final String URL = "http://localhost:8080/";
	
	//Inyectamos el objeto de tipo RestTemplate que nos ayudará
	//a hacer las peticiones HTTP al servicio REST
	@Autowired
	private RestTemplate restTemplate;

	public String obtener(String path){
		//Con el método getForObject del objeto restTemplate podemos hacer
		//peticiones HTTP a un servicio REST. Tenemos que pasarle la URL
		//Y el tipo que nos va a devolver (String)
		//URL Ej: http://localhost:8080/mensaje
		//Este método usara el verbo GET para hacer la request
		//de manera implicita
		
		String pathFinal = URL + path;
		System.out.println("obtener -> Ruta final: " + pathFinal);
		String mensaje = restTemplate.getForObject(pathFinal, String.class);
		return mensaje;
	}

}
