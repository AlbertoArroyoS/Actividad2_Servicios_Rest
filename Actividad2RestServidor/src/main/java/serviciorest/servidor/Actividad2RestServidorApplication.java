package serviciorest.servidor;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Actividad2RestServidorApplication {

	public static void main(String[] args) {
		System.out.println("Servicio Rest -> Cargando el contexto de Spring...");
		SpringApplication.run(Actividad2RestServidorApplication.class, args);
		System.out.println("Servicio Rest -> Contexto de Spring cargado!");
	}

}
