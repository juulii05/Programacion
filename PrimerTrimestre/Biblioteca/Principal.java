package Biblioteca;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {

		Biblioteca miBiblioteca = new Biblioteca();
		Scanner sc = new Scanner(System.in);

		int opcion = -1;

		do {
			System.out.println("\n=== MENÚ BIBLIOTECA ===");
			System.out.println("1. Mostrar todo (Socios y Libros)");
			System.out.println("2. Registrar nuevo socio");
			System.out.println("3. Prestar libro");
			System.out.println("4. Devolver libro");
			System.out.println("5. Registrar nuevo libro");
			System.out.println("0. Salir");
			System.out.print("--> Elige una opción: ");

			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			case 1:
				miBiblioteca.mostrarSocios();
				miBiblioteca.mostrarLibros();
				break;

			case 2:
				System.out.println("--- REGISTRAR NUEVO SOCIO ---");
				System.out.println("Introduce el nombre: ");
				String nombre = sc.nextLine();

				System.out.println("Introduce el email: ");
				String email = sc.nextLine();
				miBiblioteca.registrarSocio(nombre, email);
				break;

			case 3:
				System.out.println("--- PRESTAR LIBRO ---");
				System.out.print("Introduce ID Socio (ej: SOCIO-XXX): ");
				String idSocio = sc.nextLine();

				System.out.print("Introduce ISBN Libro (ej: LIB-XXX): ");
				String isbnLibro = sc.nextLine();

				miBiblioteca.realizarPrestamo(idSocio, isbnLibro);
				break;

			case 4:
				System.out.println("--- DEVOLVER LIBRO ---");
				System.out.print("Introduce ISBN Libro a devolver: ");
				String isbnDevolver = sc.nextLine();

				miBiblioteca.devolverLibro(isbnDevolver);
				break;

			case 5:
				System.out.println("--- REGISTRAR NUEVO LIBRO ---");
				System.out.print("Título: ");
				String titulo = sc.nextLine();
				System.out.print("Autor: ");
				String autor = sc.nextLine();
				miBiblioteca.registrarLibro(titulo, autor);
				break;

			case 0:
				System.out.println("Saliendo del sistema... ¡Adiós!");
				break;

			default:
				System.out.println("Opción incorrecta. Prueba otra vez.");
				break;
			}

		} while (opcion != 0);

		sc.close();
	}

}
