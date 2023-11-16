package serviciorest.servidor.modelo.persistencia;

import java.util.ArrayList;

import java.util.List;

import serviciorest.servidor.modelo.entidad.Libro;



public class DaoLibro {
	
	public List<Libro> listaLibros;
	public int contador;
	
	/**
	 * Cuando se cree el objeto dentro del contexto de Spring, se ejecutara
	 * su constructor, que creara las personas y las metera en una lista
	 * para que puedan ser consumidas por nuestros clientes
	 */
	public DaoLibro() {
		System.out.println("DaoPersona -> Creando la lista de personas!");
		listaLibros = new ArrayList<Libro>();
		Libro p1 = new Libro(contador++,"STEVE", "ROGERS", 39);//ID: 0
		Libro p2 = new Libro(contador++,"HARRY", "POTTER", 19);//ID: 1
		Libro p3 = new Libro(contador++,"CHIQUITO", "DE LA CALZADA", 79);//ID: 2
		Libro p4 = new Libro(contador++,"BUD", "SPENCER", 85);//ID:3
		Libro p5 = new Libro(contador++,"HARRY", "CALLAHAN", 87);//ID:4
		listaLibros.add(p1);
		listaLibros.add(p2);
		listaLibros.add(p3);
		listaLibros.add(p4);
		listaLibros.add(p5);
	}
	
	//1.Dar de alta un libro
	public void add(Libro l) {
		l.setId(contador++);
		listaLibros.add(l);
	}
	
	//2.Dar de baja un libro por ID
	public Libro delete(int id) {
		try {
			return listaLibros.remove(id);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("delete -> Persona fuera de rango");
			return null;
		}
	}
	
	//3.Modificar un libro por ID
	public Libro update(Libro l) {
		try {
			Libro lAux = listaLibros.get(l.getId());
		//	pAux.setId(p.getId());
			lAux.setTitulo(l.getTitulo());
			lAux.setEditorial(l.getEditorial());
			lAux.setNota(l.getNota());

			return lAux;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("update -> Libro fuera de rango");
			return null;
		}
	}
	
	//4.Obtener un libro por ID
	public Libro get(int id) {
		try {
			return listaLibros.get(id);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Persona fuera de rango");
			return null;
		}
	}
	
	
	
	//5.Listar todos los libros
	public List<Libro> list() {
		return listaLibros;
	}


	
	


}
