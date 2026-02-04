package Biblioteca;

public class Socio {

	private String idSocio;
	private String nombre;
	private String email;
	private int librosPrestados;
	
	private int maxPrestados = 3;
	
	public Socio(String nombre, String email) {
		if(nombre.length() == 0) {
			this.nombre = "Usuario Anónimo";
		} else {
			this.nombre = nombre;
		}
		
		if(email.contains("@")) {
			this.email = email;
		} else {
			this.email = "email_erroneo@biblioteca.com";
		}
		
		this.librosPrestados = 0;
		this.idSocio = generarIdSocio();
	}
	
	private String generarIdSocio() {
		int numero = (int) (Math.random() * 1000 + 1);
		return "SOCIO-" + numero;
	}
	
	public boolean puedePedir() {
		return this.librosPrestados < maxPrestados;
	}
	
	public void sumarLibro() {
		if(puedePedir()) {
			this.librosPrestados++;
		}
	}
	
	public void restarLibro() {
		if(this.librosPrestados > 0) {
			this.librosPrestados--;
		}
	}
	
	public String getIdSocio() {
		return idSocio;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String toString() {
		return "SOCIO: " + nombre + " (ID: " + idSocio + ") | Libros en poder: " + librosPrestados + "/" + maxPrestados;
	}
	
}
