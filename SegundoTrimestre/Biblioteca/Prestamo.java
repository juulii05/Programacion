package Biblioteca;

public class Prestamo {

	private String idPrestamo;
	private Socio socio;
	private Libro libro;
	private Fecha fechaInicio;
	private Fecha fechaFin;

	public Prestamo(Socio socio, Libro libro) {
		this.socio = socio;
		this.libro = libro;
		this.fechaInicio = new Fecha(1, 1, 2025);
		this.fechaFin = null;
		this.idPrestamo = "PRES-" + (int)(Math.random() * 1000);
	}

	public void finalizarPrestamo() {
		this.fechaFin = new Fecha(15, 1, 2025);
	}
	
	public boolean estaActivo() {
		return this.fechaFin == null;
	}

	public Socio getSocio() {
		return socio;
	}

	public Libro getLibro() {
		return libro;
	}

	public String toString() {
		String estado = (fechaFin == null) ? "ACTIVO" : "DEVUELTO";
		return "Préstamo [" + idPrestamo + "] | " + socio.getNombre() + " - " + libro.getTitle() + " (" + estado + ")";
	}
}