package examen;

import java.util.Scanner;
import java.util.ArrayList;

public class ajedrez {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[][] tablero = { 
				{ '.', 'C', '.', '.', '.', '.', '.', '.' }, 
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' }, 
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' }, 
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', 'P', '.', '.', '.', '.', '.', '.' }, 
				{ '.', '.', '.', '.', '.', '.', '.', '.' } 
		};
		
		int caballoFila = 0;
		int caballoColumna = 1;

		int peonFila = 6;
		int peonColumna = 1;

		while (true) {

			System.out.println("  0 1 2 3 4 5 6 7");
			for (int i = 0; i < 8; i++) {
				System.out.print(i + " ");
				for (int j = 0; j < 8; j++) {
					System.out.print(tablero[i][j] + " ");
				}
				System.out.println();
			}

			System.out.print("¿Qué pieza quieres mover? (caballo/peon/salir): ");
			String pieza = sc.next();

			if (pieza.equals("salir")) {
				System.out.println("Fin del programa.");
				break;
			}

			ArrayList<Integer> posiblesFilas = new ArrayList<>();
			ArrayList<Integer> posiblesCols = new ArrayList<>();

			if (pieza.equals("caballo")) {
				System.out.println("Calculando movimientos del caballo...");
				
				int[] saltosFila = {-2, -2, -1, -1,  1, 1,  2, 2};
                int[] saltosCol  = {-1,  1, -2,  2, -2, 2, -1, 1};

                for (int k = 0; k < 8; k++) {
                    int nuevaFila = caballoFila + saltosFila[k];
                    int nuevaCol = caballoColumna + saltosCol[k];

                    if (nuevaFila >= 0 && nuevaFila < 8 && nuevaCol >= 0 && nuevaCol < 8) {
                        if (tablero[nuevaFila][nuevaCol] == '.') {
                            posiblesFilas.add(nuevaFila);
                            posiblesCols.add(nuevaCol);
                        }
                    }
                }

			} else if (pieza.equals("peon")) {
				System.out.println("Calculando movimientos del peón...");
				
				int filaDestino = peonFila - 1;
			    if (filaDestino >= 0 && tablero[filaDestino][peonColumna] == '.') {
			        posiblesFilas.add(filaDestino);
			        posiblesCols.add(peonColumna);
			    }

			} else {
				System.out.println("Pieza no reconocida, escribe 'caballo' o 'peon'.");
				continue;
			}
			
			if (posiblesFilas.isEmpty()) {
		        System.out.println("¡No hay movimientos posibles para esa pieza!");
		    } else {
		        System.out.println("Movimientos válidos:");
		        for (int i = 0; i < posiblesFilas.size(); i++) {
		            System.out.println("[" + i + "] (" + posiblesFilas.get(i) + ", " + posiblesCols.get(i) + ")");
		        }

		        System.out.print("Elige el índice del movimiento: ");
		        int indice = sc.nextInt();

		        if (indice >= 0 && indice < posiblesFilas.size()) {
		            
		            if (pieza.equals("peon")) {
		                tablero[peonFila][peonColumna] = '.';
		                
		                peonFila = posiblesFilas.get(indice);
		                peonColumna = posiblesCols.get(indice);
		                
		                tablero[peonFila][peonColumna] = 'P';
		                System.out.println("Moviendo peón a (" + peonFila + "," + peonColumna + ")...");

		            } else if (pieza.equals("caballo")) {
		                tablero[caballoFila][caballoColumna] = '.';
		                
		                caballoFila = posiblesFilas.get(indice);
		                caballoColumna = posiblesCols.get(indice);
		                
		                tablero[caballoFila][caballoColumna] = 'C';
		                System.out.println("Moviendo caballo a (" + caballoFila + "," + caballoColumna + ")...");
		            }
		            
		        } else {
		            System.out.println("Índice incorrecto.");
		        }
		    }
		}
	}
}