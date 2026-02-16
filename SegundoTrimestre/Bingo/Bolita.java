package Bingo;

import java.util.ArrayList;

public class Bolita {

	private ArrayList<Integer> bomboBolita = new ArrayList<>();

	private int bolitaAzar() {
		int bolita = (int) (Math.random() * 90 + 1);
		return bolita;
	}

	private boolean comprobarBolita(int numero) {
		return bomboBolita.contains(numero);
	}

	private void añadirBolita(int numero) {
		bomboBolita.add(numero);

	}

	public int sacarBolita() {
		while (true) {
			int candidato = bolitaAzar();

			if (!comprobarBolita(candidato)) {
				añadirBolita(candidato);
				return candidato;
			}
		}
	}

}