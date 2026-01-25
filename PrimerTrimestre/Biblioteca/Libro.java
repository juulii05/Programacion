package Biblioteca;

public class Libro {

	private String titulo;
	private String autor;
	private String isbn;
	private boolean disponible;

	private Fecha fechaPublicacion;

	public Libro(String titulo, String autor) {
		this.titulo = titulo;
		this.autor = autor;
		this.disponible = true;

		IsbnAleatorio isbnGenerador = new IsbnAleatorio();

		this.isbn = isbnGenerador.getISBN();

		int diaAzar = (int) (Math.random() * 30) + 1;
		int mesAzar = (int) (Math.random() * 12) + 1;
		int yearAzar = (int) (Math.random() * 55) + 1970;

		this.fechaPublicacion = new Fecha(diaAzar, mesAzar, yearAzar);
	}
	
	public boolean estaDisponible() {
		return this.disponible;
	}
	
	public void setDisponible(boolean nuevoEstado) {
	    this.disponible = nuevoEstado;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public String getTitle() {
		return this.titulo;
	}

	public String getAutor() {
		return this.autor;
	}
	
    public String toString() {
    	String estadoDisponibilidad = disponible ? "Sí" : "No";
    	return "LIBRO: " + titulo + " | ISBN: " + isbn + " | DISPONIBLE: " + estadoDisponibilidad + " | PUBLICADO: " + fechaPublicacion;
    }
}
