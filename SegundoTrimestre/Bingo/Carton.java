package Bingo;

public class Carton {

	private Casilla[][] matriz = new Casilla[3][9];
	private int id;

	private void generarNumeros() {
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 9; i++) {
				int numMin = i * 10;
				int numMax = numMin + 9;

				if (i == 0) {
					numMin = 1;
				} else if (i == 8) {
					numMax = 90;
				}

				int numFinal;
				boolean repetido;

				do {
					repetido = false;
					numFinal = (int) (Math.random() * (numMax - numMin + 1)) + numMin;

					for (int filaAnt = 0; filaAnt < j; filaAnt++) {
						if (matriz[filaAnt][i].numero == numFinal) {
							repetido = true;
							break;
						}
					}
				} while (repetido);

				matriz[j][i] = new Casilla(numFinal);

			}
		}
	}

	public Carton(int id) {
		this.id = id;
		generarNumeros();
		ordenarColumnas();
		generarHuecos();
	}

	public int getId() {
		return id;
	}

	private void ordenarColumnas() {
		for (int i = 0; i < 9; i++) {
			if (matriz[0][i].numero > matriz[1][i].numero) {
				Casilla aux = matriz[0][i];
				matriz[0][i] = matriz[1][i];
				matriz[1][i] = aux;
			}

			if (matriz[1][i].numero > matriz[2][i].numero) {
				Casilla aux = matriz[1][i];
				matriz[1][i] = matriz[2][i];
				matriz[2][i] = aux;
			}

			if (matriz[0][i].numero > matriz[1][i].numero) {
				Casilla aux = matriz[0][i];
				matriz[0][i] = matriz[1][i];
				matriz[1][i] = aux;
			}
		}
	}

	private void generarHuecos() {
		for (int i = 0; i < 3; i++) {
			int huecosBorrados = 0;

			while (huecosBorrados < 4) {
				int columnaAzar = (int) (Math.random() * 9);

				if (matriz[i][columnaAzar] != null) {
					int numerosEnColumna = 0;
					for (int fila = 0; fila < 3; fila++) {
						if (matriz[fila][columnaAzar] != null) {
							numerosEnColumna++;
						}
					}
					if (numerosEnColumna > 1) {
						matriz[i][columnaAzar] = null;
						huecosBorrados++;
					}
				}
			}
		}
	}

	public void imprimirCarton() {
		String ROJO = "\u001B[31m";
		String RESET = "\u001B[0m";
		String borde = "+----+----+----+----+----+----+----+----+----+";
		System.out.println(borde);

		for (int j = 0; j < 3; j++) {
			System.out.print("|");

			for (int i = 0; i < 9; i++) {
				if (matriz[j][i] != null) {
					if (matriz[j][i].marcada) {
						System.out.printf("%s %2d %s|", ROJO, matriz[j][i].numero, RESET);
					} else {
						System.out.printf(" %2d |", matriz[j][i].numero);

					}
				} else {
					System.out.print("    |");
				}
			}
			System.out.println("\n" + borde);
		}
	}

	public void tacharNumero(int bolita) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				if (matriz[i][j] != null) {
					if (matriz[i][j].numero == bolita) {
						matriz[i][j].marcada = true;
						return;
					}
				}
			}
		}
	}

	public boolean comprobarLinea() {
		for (int i = 0; i < 3; i++) {
			int contadorMarcadas = 0;
			for (int j = 0; j < 9; j++) {
				if (matriz[i][j] != null && matriz[i][j].marcada == true) {
					contadorMarcadas++;
				}
			}
			if (contadorMarcadas == 5) {
				return true;
			}
		}
		return false;
	}

	public boolean comprobarBingo() {
		int contadorMarcadas = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				if (matriz[i][j] != null && matriz[i][j].marcada == true) {
					contadorMarcadas++;
				}
			}
			if (contadorMarcadas == 15) {
				return true;
			}
		}
		return false;
	}
}