package Ruleta;

import java.util.Scanner;

public class Ruleta {
	Scanner sc = new Scanner(System.in);

	int saldo;

	// NUMERO BOLITA
	int tirarRule() {
		int bolita = (int) (Math.random() * 36);
		return bolita;
	}

	// COLOR BOLITA
	String colorBolita(int bolita2) {
		final String ROJO = "ROJO";
		final String NEGRO = "NEGRO";
		final String VERDE = "VERDE";
		final String[] COLORES = { "VERDE", "ROJO", "NEGRO", "ROJO", "NEGRO", "ROJO", "NEGRO", "ROJO", "NEGRO", "ROJO",
				"NEGRO", "NEGRO", "ROJO", "NEGRO", "ROJO", "NEGRO", "ROJO", "NEGRO", "ROJO", "ROJO", "NEGRO", "ROJO",
				"NEGRO", "ROJO", "NEGRO", "ROJO", "NEGRO", "ROJO", "NEGRO", "NEGRO", "ROJO", "NEGRO", "ROJO", "NEGRO",
				"ROJO", "NEGRO", "ROJO" };
		String colorGanador = COLORES[bolita2];
		return colorGanador;

	}

	// SALDO INICIAL
	int saldoInicial() {
		do {
			System.out.print("Introduce tu saldo inicial: ");
			saldo = sc.nextInt();
		} while (saldo <= 0);

		return saldo;
	}

	// APOSTAR
	int saldo() {
		return saldo;
	}

	// APOSTAR COLOR

	int apostarColor(String colorElegido, int dineroApostado, String colorBolita) {
		saldo = saldo - dineroApostado;

		switch (colorElegido) {
		case "rojo" -> {
			if (colorBolita.equals("ROJO")) {
				saldo = saldo + (dineroApostado * 2);
				System.out.println("Has ganado");
			} else {
				System.out.println("Has perdido");
			}
		}
		case "negro" -> {
			if (colorBolita.equals("NEGRO")) {
				saldo = saldo + (dineroApostado * 2);
				System.out.println("Has ganado");
			} else {
				System.out.println("Has perdido");
			}
		}
		case "verde" -> {
			if (colorBolita.equals("VERDE")) {
				saldo = saldo + (dineroApostado * 16);
				System.out.println("Has ganado");
			} else {
				System.out.println("Has perdido");
			}

		}
		}
		return saldo;

	}

	int apuestaParimpar(int parimparElegido, int dineroApostado, int bolita) {
		saldo = saldo - dineroApostado;

		switch (parimparElegido) {
		case 1 -> {

			if (bolita % 2 == 0) {
				System.out.println("Has ganado");
				saldo = saldo + (dineroApostado * 2);
			} else {
				System.out.println("Has perdido");
			}
		}

		case 2 -> {
			if (bolita % 2 != 0) {
				System.out.println("Has ganado");
				saldo = saldo + (dineroApostado * 2);
			} else {
				System.out.println("Has perdido");
			}
		}
		}

		return saldo;
	}

	int apostarFila(int filaElegida, int dineroApostado, int bolita) {
		saldo = saldo - dineroApostado;
		switch (filaElegida) {
		case 1 -> {
			if (bolita % 3 == 0) {
				System.out.println("Has ganado");
				saldo = saldo + (dineroApostado * 3);
			} else {
				System.out.println("Has perdido");
			}
		}

		case 2 -> {
			if ((bolita != 0) && (bolita % 3 == 2)) {
				System.out.println("Has ganado");
				saldo = saldo + (dineroApostado * 3);
			} else {
				System.out.println("Has perdido");
			}
		}
		case 3 -> {
			if ((bolita != 0) && (bolita % 3 == 1)) {
				System.out.println("Has ganado");
				saldo = saldo + (dineroApostado * 3);
			} else {
				System.out.println("Has perdido");
			}
			
		}
		

		}

		return saldo;
	}

	// APOSTAR NUMERO

	int numeroApostado(int numeroApostado, int dineroApostado, int bolita) {
		saldo = saldo - dineroApostado;
		if (numeroApostado == bolita) {
			System.out.println("Has ganado");
			saldo = saldo + (dineroApostado * 36);
		} else {
			System.out.println("Has perdido");
		}
		return saldo;
	}
	
	int docenaApostado(int docenaApostado, int dineroApostado, int bolita) {
		saldo = saldo - dineroApostado;
		switch (docenaApostado) {
		case 1 -> {
			
			if ((bolita >= 1) && (bolita <= 12)) {
				System.out.println("Has ganado");
				saldo = saldo + (dineroApostado * 2);
			} else {
				System.out.println("Has perdido");
			}
		}
		case 2 -> {
			
			if ((bolita >= 13) && (bolita <= 24)) {
				System.out.println("Has ganado");
				saldo = saldo + (dineroApostado * 2);
			} else {
				System.out.println("Has perdido");
			}
		}
		case 3 -> {
			
			if ((bolita >= 25) && (bolita <= 36)) {
				System.out.println("Has ganado");
				saldo = saldo + (dineroApostado * 2);
				
			} else {
				System.out.println("Has perdido");
			}
		
		return saldo;	
		}


	

}