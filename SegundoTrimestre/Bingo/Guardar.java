package Bingo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Guardar {
	public double saldo = 0.0;
	private final Path archivo = Paths.get("config.json");

	public Guardar() {
		try {
			if (!Files.exists(archivo)) {
				String jsonInicial = "{\n  \"saldo\": 0.0\n}";
				Files.writeString(archivo, jsonInicial);
			}
		} catch (IOException e) {
			System.out.println("Error al crear el archivo: " + e.getMessage());
		}
	}

	public void guardarTodo() {
		try {
			String json = "{\n  \"saldo\": " + this.saldo + "\n}";
			Files.writeString(archivo, json, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			System.out.println("Error al guardar.");
		}
	}

	public void cargar() {
		try {
			if (!Files.exists(archivo))
				return;

			String contenido = Files.readString(archivo);
			String[] lineas = contenido.split("\n");

			for (String linea : lineas) {
				if (linea.contains("\"saldo\"")) {
					String numeroLimpio = linea.split(":")[1].replaceAll("[^0-9.]", "");
					if (!numeroLimpio.isEmpty()) {
						this.saldo = Double.parseDouble(numeroLimpio);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo.");
		}
	}
}