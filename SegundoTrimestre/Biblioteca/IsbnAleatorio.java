package Biblioteca;

public class IsbnAleatorio {
	
	String isbn;
	// ISBN ALEATORIO
	private String isbnAleatorio() {
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String codigoGenerado = "";

		for (int i = 0; i < 4; i++) {
			int indice = (int) (Math.random() * letras.length());
			codigoGenerado = codigoGenerado + letras.charAt(indice);
		}

		codigoGenerado = "LIB-" + codigoGenerado;
		return codigoGenerado;
	}

	public String getISBN() {
		return isbnAleatorio();
	}
}
