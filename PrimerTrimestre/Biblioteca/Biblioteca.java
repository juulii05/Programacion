package Biblioteca;

import java.util.ArrayList;

public class Biblioteca {

	private ArrayList<Socio> listaSocios;
	private ArrayList<Libro> listaLibros;
	private ArrayList<Prestamo> listaPrestamos;
	
	public Biblioteca() {
		this.listaSocios = new ArrayList<Socio>();
		this.listaLibros = new ArrayList<Libro>();
		this.listaPrestamos = new ArrayList<Prestamo>();
		
		precargarDatos();
	}
	
	private void precargarDatos() {
		Socio s1 = new Socio("Santiago Prieto", "santiago.prieto@gmail.com");
		Socio s2 = new Socio("Elena Vega", "elena.vega@hotmail.com");
		Socio s3 = new Socio("Pablo Herrera", "pablo.herrera@yahoo.com");
		
		listaSocios.add(s1);
		listaSocios.add(s2);
		listaSocios.add(s3);
		
		Libro l1 = new Libro("Cien años de soledad", "Gabriel García Márquez");
		Libro l2 = new Libro("El Señor de los Anillos", "J.R.R. Tolkien");
		Libro l3 = new Libro("1984", "George Orwell");
		Libro l4 = new Libro("Don Quijote", "Miguel de Cervantes");
		Libro l5 = new Libro("Harry Potter", "J.K. Rowling");
		Libro l6 = new Libro("Dune", "Frank Herbert");
		
		listaLibros.add(l1);
		listaLibros.add(l2);
		listaLibros.add(l3);
		listaLibros.add(l4);
		listaLibros.add(l5);
		listaLibros.add(l6);
		
		Prestamo prestamo1 = new Prestamo(s1, l1); 
		listaPrestamos.add(prestamo1);
		s1.sumarLibro();
		l1.setDisponible(false);
		
		Prestamo p2 = new Prestamo(s2, l2);
		listaPrestamos.add(p2);
		s2.sumarLibro();
		l2.setDisponible(false);
		
	}
	

	
	public void mostrarSocios() {
		System.out.println("Lista Socios");
		for(Socio s : listaSocios) {
			System.out.println(s);
		}
	}
	
	public void mostrarLibros() {
		System.out.println();
		System.out.println("Lista Libros");
		for(Libro l : listaLibros) {
			System.out.println(l);
		}
	}
	
	public void mostrarPrestamos() {
		System.out.println("--- LISTA DE PRÉSTAMOS ---");
		for(Prestamo p : listaPrestamos) {
			System.out.println(p);
		}
	}
	
	public void registrarSocio(String nombre, String email) {
		Socio nuevoSocio = new Socio(nombre, email);
		listaSocios.add(nuevoSocio);
		System.out.println("Socio registrado, el ID es: " + nuevoSocio.getIdSocio());
	}
	
	public void realizarPrestamo(String idSocio, String isbnLibro) {
		Socio elSocio = null;
		Libro elLibro = null;
		
		for(Socio s : listaSocios) {
			if(s.getIdSocio().equals(idSocio)) {
				elSocio = s;
			}
		}
		
		for(Libro l : listaLibros) {
			if(l.getIsbn().equals(isbnLibro)) {
				elLibro = l;
			}
		}
		
		if(elSocio == null) {
			System.out.println("Error, el ID no existe");
			return;
		}
		
		if(elLibro == null) {
			System.out.println("Error, el ISBN no existe");
			return;
		}
		
		if(!elLibro.estaDisponible()) {
			System.out.println("Error, el libro ya esta prestado");
			return;
		}
		
		if(!elSocio.puedePedir()) {
			System.out.println("Error, ya tiene 3 libros");
			return;
		}
		
		Prestamo nuevoPrestamo = new Prestamo(elSocio, elLibro);
		listaPrestamos.add(nuevoPrestamo);
		elSocio.sumarLibro();
		elLibro.setDisponible(false);
		
		System.out.println("Préstamo realizado");
		System.out.println(nuevoPrestamo);
	}
	
	public void devolverLibro(String isbnLibro) {
		Prestamo prestamoEncontrado = null;
		
		for(Prestamo p : listaPrestamos) {
			if(p.getLibro().getIsbn().equals(isbnLibro) && p.estaActivo()) {
				prestamoEncontrado = p;
			}
		}
		
		if(prestamoEncontrado == null) {
			System.out.println("Error: No hay ningún préstamo activo para este libro.");
			return;
		}
		
		prestamoEncontrado.finalizarPrestamo();
		prestamoEncontrado.getLibro().setDisponible(true);
		prestamoEncontrado.getSocio().restarLibro();
		System.out.println("Devolución realizada correctamente.");
		System.out.println(prestamoEncontrado);
	}
	
	public void registrarLibro(String titulo, String autor) {
		Libro nuevoLibro = new Libro(titulo, autor);
		listaLibros.add(nuevoLibro);
		System.out.println("Libro registrado correctamente.");
		System.out.println(nuevoLibro);
	}

}