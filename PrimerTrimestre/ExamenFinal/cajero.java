package examen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class cajero {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int saldo = 100;

		List<String> historial = new ArrayList<>();

		while (true) {
			System.out.println("--- CAJERO AUTOMÁTICO ---");

			System.out.println("1. Consultar saldo");
			System.out.println("2. Ingresar saldo");
			System.out.println("3. Retirar dinero");
			System.out.println("4. Ver historial");
			System.out.println("5. Salir");
			
			System.out.print("Elige una opción: ");
			int opcion = sc.nextInt();
			System.out.println();

			if(opcion == 5) {
				System.out.println("¡Hasta luego!");
				break;
			}

			switch (opcion) {
			case 1 -> {
				System.out.print("Saldo actual: " + saldo + "€");
				System.out.println();
			}

			case 2 -> {
				System.out.print("Cantidad a ingresar: ");
				int ingresar = sc.nextInt();
				
				while (ingresar <= 0) {
					System.out.println("Error, cantidad no válida");
					System.out.print("Cantidad a ingresar: ");
					ingresar = sc.nextInt();
				}
				
				String ingresar1 = Integer.toString(ingresar);
				historial.add("Ingreso de " + ingresar1 + "€");

				saldo += ingresar;
				
				System.out.println("Nuevo saldo: " + saldo + "€");
				System.out.println();

			}

			case 3 -> {
				System.out.print("Cantidad a retirar: ");
				int retirar = sc.nextInt();
				
				while (retirar > saldo) {
					System.out.println("La retirada supera tu saldo, porfavor intente de nuevo (saldo = " + saldo + "): ");
					retirar = sc.nextInt();
				}

				String retirar1 = Integer.toString(retirar);
				historial.add("Retirada de " + retirar1 + "€");
				saldo -= retirar;
				System.out.println("Retirada realizada");
				System.out.println("Nuevo saldo: " + saldo + "€");
				System.out.println();
			}

			case 4 -> {
				System.out.println("--- HISTORIAL ---");

				for (String fin : historial) {
					System.out.println(fin);
				}
				
				System.out.println();

			}

			}

		}
	}

}
