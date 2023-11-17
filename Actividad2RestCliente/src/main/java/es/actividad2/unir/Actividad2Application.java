package es.actividad2.unir;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import es.actividad2.unir.modelo.entidad.Libro;
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
	
	//Para poder leer las opciones del menu	que estan en un metodo estatico
	private static Scanner leer;
	static {
			leer = new Scanner(System.in);
	}

	public static void main(String[] args) {
		System.out.println("Cliente -> Cargando el contexto de Spring");
		SpringApplication.run(Actividad2Application.class, args);
		
	}
	

	@Override
	public void run(String... args) throws Exception {
		System.out.println("****** Arrancando el cliente REST ******");
		//System.out.println("*************  MENSAJE *****************");
		//String mensaje = spm.obtener("mensaje");
		//System.out.println("run -> Mensaje: " + mensaje);
		System.out.println("Bienvenido a la aplicación de gestión de libros");

		int opcion=0;
        do {
        	opcion = menu();

            switch (opcion) {
                case 1:
                    darDeAltaLibro();
                    break;
                case 2:
                    darDeBajaLibro();
                    break;
                case 3:
                    modificarLibro();
                    break;
                case 4:
                    obtenerLibroPorId();
                    break;
                case 5:
                    listarTodosLosLibros();
                    break;
                case 6:
                    System.out.println("Saliendo de la aplicación.");
                    pararAplicacion();
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 6);
		
	}
	
	
	public void pararAplicacion() {
		
		SpringApplication.exit(context, () -> 0);

	}
	private int menu() {
		int opcion = 0;
		System.out.println("----------------------------------------------------");
		System.out.println("|                      MENU                        |");
		System.out.println("----------------------------------------------------");
        System.out.println("1. Dar de alta un libro");
        System.out.println("2. Dar de baja un libro por ID");
        System.out.println("3. Modificar un libro por ID");
        System.out.println("4. Obtener un libro por ID");
        System.out.println("5. Listar todos los libros");
        System.out.println("6. Salir");
		System.out.println("----------------------------------------------------");
		System.out.println("Introduzca una opción del 1 al 6, si quiere salir 6");
		System.out.println("----------------------------------------------------");
		
		try {
			opcion = leer.nextInt();
			
		} catch (java.util.InputMismatchException e) {
	        // Atrapar la excepción si se ingresa algo que no es un entero
	        System.out.println("Entrada no válida. Ingrese un número entero.");
	        leer.next(); // Limpiar el búfer de entrada para evitar un bucle infinito
	    }
		
		if (opcion<1 || opcion > 6) {
			System.out.println("OPCION INCORRECTA");
		}
		
		return opcion;	

    }

	private void darDeAltaLibro() {
		leer.nextLine(); // Limpiar el búfer de nueva línea
	    System.out.println("Introduzca título del libro");
	    String titulo = leer.nextLine();
	    System.out.println("Introduzca editorial del libro");
	    String editorial = leer.nextLine();
	    int nota = 0;
	    boolean entradaValida = false;
	    // Bucle para comprobar si hemos metido un número y si está dentro del rango
	    while (!entradaValida) {
	        try {
	            System.out.println("Introduzca valoración del libro");
	            nota = Integer.parseInt(leer.nextLine());
	            
	            if (nota >= 0 && nota <= 10) {
	                entradaValida = true; // Si es un número válido, pasa a true
	            } else {
	                System.out.println("La valoración debe ser de 0 a 10.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Entrada no válida. Debe ingresar un número entero.");
	        }
	    }

	    Libro libro = new Libro(0, titulo.toUpperCase(), editorial.toUpperCase(), nota);
	    spl.alta(libro);

        
    }

    private void darDeBajaLibro() {
    	leer.nextLine();
    	int id = 0;
	    boolean entradaValida = false;
	    // Bucle para comprobar si hemos metido un número y si está dentro del rango
	    while (!entradaValida) {
	        try {
	        	System.out.println("Introduzca id del libro a borrar");
	            id = Integer.parseInt(leer.nextLine());
	            entradaValida = true; // Si es un número válido, pasa a true
	           
	        } catch (NumberFormatException e) {
	            System.out.println("Entrada no válida. Debe ingresar un número entero.");
	        }
	    }
    	boolean borrada = spl.borrar(id);
    	System.out.println("run -> Libro con id " + id +" borrada? " + borrada);
    }

    private void modificarLibro() {
    	leer.nextLine();
    	int id = 0;
	    boolean entradaValidaId = false;
	    // Bucle para comprobar si hemos metido un número y si está dentro del rango
	    while (!entradaValidaId) {
	        try {
	        	System.out.println("Introduzca id del libro a modificar");
	            id = Integer.parseInt(leer.nextLine());
	            entradaValidaId = true; // Si es un número válido, pasa a true
	           
	        } catch (NumberFormatException e) {
	            System.out.println("Entrada no válida. Debe ingresar un número entero.");
	        }
	    }
    	System.out.println("Introduzca titulo del libro a modificar");
    	String titulo = leer.nextLine();
    	System.out.println("Introduzca editorial del libro a modificar");
    	String editorial = leer.nextLine();
    	int nota = 0;
	    boolean entradaValida = false;
	    // Bucle para comprobar si hemos metido un número y si está dentro del rango
	    while (!entradaValida) {
	        try {
	            System.out.println("Introduzca valoración del libro");
	            nota = Integer.parseInt(leer.nextLine());
	            
	            if (nota >= 0 && nota <= 10) {
	                entradaValida = true; // Si es un número válido, pasa a true
	            } else {
	                System.out.println("La valoración debe ser de 0 a 10.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Entrada no válida. Debe ingresar un número entero.");
	        }
	    }
    	Libro libro = new Libro(id,titulo,editorial,nota);
    	boolean modificada = spl.modificar(libro);
    	System.out.println("run -> libro modificado? " + modificada);
    }

    private void obtenerLibroPorId() {	
    	leer.nextLine();
    	int id = 0;
	    boolean entradaValidaId = false;
	    // Bucle para comprobar si hemos metido un número y si está dentro del rango
	    while (!entradaValidaId) {
	        try {
	        	System.out.println("Introduzca id del libro a buscar");
	            id = Integer.parseInt(leer.nextLine());
	            entradaValidaId = true; // Si es un número válido, pasa a true
	           
	        } catch (NumberFormatException e) {
	            System.out.println("Entrada no válida. Debe ingresar un número entero.");
	        }
	    }
    	Libro libro = spl.obtener(id);
    	System.out.println("run -> Libro con id : " + id + ": " +libro);
    }

    private void listarTodosLosLibros() {
    	
    	List<Libro> listaLibros = spl.listar(null);
    	for (Libro libro : listaLibros){
    		System.out.println(libro);		
    	}
    }
	

    

}
