package es.actividad2.unir;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import es.actividad2.unir.modelo.servicio.ServicioProxyLibro;
import es.actividad2.unir.modelo.servicio.ServicioProxyMensaje;

@SpringBootApplication
public class Actividad2Application implements CommandLineRunner{
	
	@Autowired
	private ServicioProxyLibro spl;
	
	@Autowired
	private ServicioProxyMensaje spm;
	
	@Autowired
	private ApplicationContext context;
	
	
	//dar de alta objetos sin crearlo nosotros
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		System.out.println("Cliente -> Cargando el contexto de Spring");
		SpringApplication.run(Actividad2Application.class, args);
		/*
		 System.out.println("****** Arrancando el cliente REST ******");
		System.out.println("*************  MENSAJE *****************");
		String mensaje = spm.obtener("mensaje");
		System.out.println("run -> Mensaje: " + mensaje);
		
		System.out.println("***********  MENSAJE HTML **************");
		String mensajeHTML = spm.obtener("mensajeHTML");
		System.out.println("run -> Mensaje: " + mensajeHTML);
		
		System.out.println("*********** ALTA PERSONA ***************");
		Persona persona = new Persona();
		persona.setNombre("Asterix");
		persona.setApellidos("El Galo");
		persona.setEdad(35);
		
		Persona pAlta = spp.alta(persona);
		System.out.println("run -> Persona dada de alta " + pAlta);
		
		System.out.println("************ GET PERSONA ***************");
		persona = spp.obtener(pAlta.getId());
		System.out.println("run -> Persona con id 5: " + persona);
		
		System.out.println("************ GET PERSONA ERRONEA ***************");
		persona = spp.obtener(20);
		System.out.println("run -> Persona con id 20: " + persona);
		
		System.out.println("********* MODIFICAR PERSONA *************");	
		Persona pModificar = new Persona();
		pModificar.setId(pAlta.getId());
		pModificar.setNombre("Obelix");
		pModificar.setApellidos("El del menhir");
		pModificar.setEdad(40);
		boolean modificada = spp.modificar(pModificar);
		System.out.println("run -> persona modificada? " + modificada);
		
		System.out.println("********* MODIFICAR PERSONA ERRONEA*************");			
		pModificar.setNombre("Panoramix");
		pModificar.setApellidos("El de la pocion magica");
		pModificar.setId(20);
		modificada = spp.modificar(pModificar);
		System.out.println("run -> persona modificada? " + modificada);
		
		System.out.println("********** BORRAR PERSONAS **************");
		boolean borrada = spp.borrar(pAlta.getId());
		System.out.println("run -> Persona con id 5 borrada? " + borrada);	
		
		System.out.println("******** BORRAR PERSONAS ERRONEA *******");
		borrada = spp.borrar(20);
		System.out.println("run -> Persona con id 20 borrada? " + borrada);		
		
		System.out.println("********** LISTAR PERSONAS ***************");
		List<Persona> listaPersonas = spp.listar(null);
		//Recorremos la lista y la imprimimos con funciones lambda
		//Tambien podríamos haber usado un for-each clásico de java
		listaPersonas.forEach((v) -> System.out.println(v));
		
		System.out.println("******* LISTAR PERSONAS POR NOMBRE *******");
		listaPersonas = spp.listar("HARRY");
		listaPersonas.forEach((v) -> System.out.println(v));
		
		System.out.println("******************************************");		
		System.out.println("******** Parando el cliente REST *********");	
		//Mandamos parar nuestra aplicación Spring Boot
		pararAplicacion();
		 */
	}
	

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	public void pararAplicacion() {
		
		SpringApplication.exit(context, () -> 0);

	}

}
