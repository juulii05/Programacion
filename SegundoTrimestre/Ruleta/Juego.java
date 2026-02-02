package Ruleta;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Juego {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int saldoInicial = 0;

		do {
			try {
				System.out.println("Introduce el saldo inicial: ");
				saldoInicial = sc.nextInt();
			} catch (InputMismatchException error) {
				System.out.println("Error, dato no válido");
				saldoInicial = 0;
			}
			sc.nextLine();
		} while (saldoInicial <= 0);

		Ruleta ruleta = new Ruleta(saldoInicial);

		int opcion = 0;

		do {
			System.out.println("\n-----------------------------");
			System.out.println("Saldo actual: " + ruleta.getSaldo());

			System.out.println("¿Qué quieres hacer?");
			System.out.println("1. Apostar a un color");
			System.out.println("2. Apostar a una fila");
			System.out.println("3. Apostar a par/impar");
			System.out.println("4. Apostar a un número");
			System.out.println("5. Apostar a una docena");
			System.out.println("6. Salir");

			try {
				opcion = sc.nextInt();
			} catch (InputMismatchException error) {
				System.out.println("Error, opción no válida");
				opcion = 0;
			}
			sc.nextLine();

			if (opcion == 6) {
				break;
			}

			if (opcion < 1 || opcion > 6) {
				System.out.println("Elige una opción correcta del menú.");
				continue;
			}

			int dineroApostado = 0;
			do {
				try {
					System.out.println("¿Cuánto dinero quieres apostar?");
					dineroApostado = sc.nextInt();

					if (dineroApostado > ruleta.getSaldo()) {
						System.out.println("No tienes suficiente saldo.");
					} else if (dineroApostado <= 0) {
						System.out.println("El importe debe ser mayor que 0.");
					}
				} catch (InputMismatchException error) {
					System.out.println("Error, cantidad no válida");
					dineroApostado = 0;
				}
				sc.nextLine();
			} while (dineroApostado > ruleta.getSaldo() || dineroApostado <= 0);

			boolean haGanado = false;

			switch (opcion) {
			case 1 -> {
				String colorElegido = "";
				do {
					System.out.println("¿Qué color? (ROJO, NEGRO, VERDE)");
					colorElegido = sc.next().toUpperCase();
					if (!colorElegido.equals("ROJO") && !colorElegido.equals("NEGRO")
							&& !colorElegido.equals("VERDE")) {
						System.out.println("Color incorrecto. Debe ser ROJO, NEGRO o VERDE.");
					}
				} while (!colorElegido.equals("ROJO") && !colorElegido.equals("NEGRO")
						&& !colorElegido.equals("VERDE"));

				haGanado = ruleta.apostarColor(colorElegido, dineroApostado);
			}
			case 2 -> {
				int filaElegida = 0;
				do {
					try {
						System.out.println("¿Qué fila? (1, 2 o 3)");
						filaElegida = sc.nextInt();
					} catch (InputMismatchException error) {
						System.out.println("Error, fila no válida");
						filaElegida = 0;
					}
					sc.nextLine();
				} while (filaElegida < 1 || filaElegida > 3);
				haGanado = ruleta.apostarFila(filaElegida, dineroApostado);
			}
			case 3 -> {
				String paridadElegida = "";
				do {
					System.out.println("¿Par o Impar?");
					paridadElegida = sc.next().toUpperCase();
					if (!paridadElegida.equals("PAR") && !paridadElegida.equals("IMPAR")) {
						System.out.println("Opción incorrecta. Escribe PAR o IMPAR.");
					}
				} while (!paridadElegida.equals("PAR") && !paridadElegida.equals("IMPAR"));

				haGanado = ruleta.apostarParidad(paridadElegida, dineroApostado);
			}
			case 4 -> {
				int numeroApostado = -1;
				do {
					try {
						System.out.println("¿A qué número? (0-36)");
						numeroApostado = sc.nextInt();
					} catch (InputMismatchException error) {
						System.out.println("Error, número no válido");
						numeroApostado = -1;
					}
					sc.nextLine();
				} while (numeroApostado > 36 || numeroApostado < 0);
				haGanado = ruleta.apostarNumero(numeroApostado, dineroApostado);
			}
			case 5 -> {
				int docenaElegida = 0;
				do {
					try {
						System.out.println("¿Qué docena? (1, 2 o 3)");
						docenaElegida = sc.nextInt();
					} catch (InputMismatchException error) {
						System.out.println("Error, docena no válida");
						docenaElegida = 0;
					}
					sc.nextLine();
				} while (docenaElegida < 1 || docenaElegida > 3);
				haGanado = ruleta.apostarDocena(docenaElegida, dineroApostado);
			}
			default -> {
				System.out.println("Opción no válida.");
			}
			}

			if (opcion >= 1 && opcion <= 5) {
				int numeroSalido = ruleta.getUltimoNumero();
				String colorSalido = ruleta.getColor(numeroSalido);

				System.out.println("Ha salido el " + numeroSalido + " " + colorSalido);

				if (haGanado) {
					System.out.println("Has ganado");
				} else {
					System.out.println("Has perdido");
				}
			}

			if (ruleta.getSaldo() <= 0) {
				System.out.println("Te has quedado sin dinero.");
				opcion = 6;
			}

		} while (opcion != 6);

		System.out.println("Gracias por jugar. Saldo final: " + ruleta.getSaldo());
		sc.close();
	}
}