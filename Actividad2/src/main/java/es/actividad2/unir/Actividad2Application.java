package es.actividad2.unir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import es.actividad2.unir.controlador.ControladorCrud;

@SpringBootApplication
public class Actividad2Application implements CommandLineRunner{
	/*
	@Autowired
	private ServicioProxyPersona spp;
	
	@Autowired
	private ApplicationContext context;
	*/
	
	
	//dar de alta objetos sin crearlo nosotros
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(Actividad2Application.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/*
	public void pararAplicacion() {
		
		SpringApplication.exit(context, () -> 0);

	}*/

}
