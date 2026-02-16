package Bingo;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Bingo {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Guardar datos = new Guardar();
		datos.cargar();
		int opcion = -1;

		do {
			try {
				System.out.println("\n===== MENÚ BINGO =====");
				System.out.println("1. Ingresar dinero");
				System.out.println("2. Comprobar dinero");
				System.out.println("3. Comprar cartones y JUGAR");
				System.out.println("0. Salir");
				System.out.print("--> Elige una opción: ");

				opcion = sc.nextInt();
				sc.nextLine();

				switch (opcion) {
				case 1 -> {
					double ingreso = 0;
					do {
						System.out.print("¿Cuánto dinero quieres ingresar? (Debe ser mayor que 0): ");
						ingreso = sc.nextDouble();
						sc.nextLine();

						if (ingreso <= 0) {
							System.out.println("¡Error! La cantidad debe ser positiva.");
						}
					} while (ingreso <= 0);

					datos.saldo += ingreso;
					datos.guardarTodo();
					System.out.println("Saldo actualizado con éxito.");
				}
				case 2 -> {
					System.out.println("Tu saldo actual es: " + datos.saldo + "€");
				}
				case 3 -> {
					double precio = 5.0;
					if (datos.saldo < precio) {
						System.out.println("No tienes dinero suficiente. ¡Ingresa dinero!");
					} else {
						datos.saldo = jugarPartida(sc, datos.saldo, precio);
						datos.guardarTodo();
					}
				}
				case 0 -> System.out.println("¡Gracias por jugar! ¡Adiós!");
				default -> System.out.println("Opción incorrecta.");
				}

			} catch (InputMismatchException e) {
				System.out.println("\n¡ERROR! Has introducido un dato no válido.");
				sc.nextLine();
				opcion = -1;
			}

		} while (opcion != 0);
		sc.close();
	}

	public static double jugarPartida(Scanner sc, double saldoActual, double precio) throws Exception {
		Bolita bombo = new Bolita();
		ArrayList<Carton> catalogo = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Carton c = new Carton(i + 1);
			catalogo.add(c);
			System.out.println("\nOPCIÓN " + c.getId() + ":");
			c.imprimirCarton();
		}
		System.out.println("\n¿Qué cartones quieres comprar? (Ej: 1,3):");
		System.out.println("(El precio de un cartón es de 5€)");
		String entrada = sc.nextLine();
		String[] elegidos = entrada.split(",");

		ArrayList<Carton> misCartones = new ArrayList<>();
		double costeTotal = elegidos.length * precio;

		if (costeTotal > saldoActual) {
			System.out.println("No tienes dinero suficiente.");
			return saldoActual;
		}

		saldoActual -= costeTotal;
		for (String s : elegidos) {
			int indice = Integer.parseInt(s.trim()) - 1;
			misCartones.add(catalogo.get(indice));
		}

		System.out.println("\n--- EL SORTEO COMIENZA CADA 1.5 SEGUNDOS ---");
		boolean lineaCantada = false;
		Carton ganadorBingo = null;

		while (ganadorBingo == null) {
			Thread.sleep(1500);
			int bolita = bombo.sacarBolita();
			System.out.println("\n>>> BOLA: " + bolita);

			for (Carton c : catalogo) {
				c.tacharNumero(bolita);

				if (misCartones.contains(c)) {
					System.out.println("TU CARTÓN " + c.getId() + ":");
					c.imprimirCarton();
				}

				if (!lineaCantada && c.comprobarLinea()) {
					lineaCantada = true;
					System.out.println("¡¡ LÍNEA EN EL CARTÓN " + c.getId() + " !!");
					if (misCartones.contains(c)) {
						System.out.println("¡Has ganado 10€!");
						saldoActual += 10;
					}
					Thread.sleep(3000);
				}

				if (c.comprobarBingo()) {
					ganadorBingo = c;
					break;
				}
			}
		}

		System.out.println("\n*************************************");
		System.out.println("¡BINGO EN EL CARTÓN " + ganadorBingo.getId() + "!");

		if (misCartones.contains(ganadorBingo)) {
			System.out.println("¡ENHORABUENA, HAS GANADO 50€!");
			saldoActual += 50;
		} else {
			System.out.println("HA GANADO LA BANCA.");
		}
		System.out.println("*************************************");

		ganadorBingo.imprimirCarton();
		Thread.sleep(3000);

		return saldoActual;
	}
}