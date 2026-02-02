package Ruleta;

public class Ruleta {
    public final int NUMEROS_RULETA = 37;
    public final int MULTIPLICADOR_PAR = 2;
    public final int MULTIPLICADOR_IMPAR = 2;
    public final int MULTIPLICADOR_ROJO = 2;
    public final int MULTIPLICADOR_NEGRO = 2;
    public final int MULTIPLICADOR_VERDE = 36;
    public final int MULTIPLICADOR_DOCENA = 3;
    public final int MULTIPLICADOR_FILA = 3;
    public final int MULTIPLICADOR_NUMERO = 36;

    public final String VERDE = "VERDE";
    public final String ROJO = "ROJO";
    public final String NEGRO = "NEGRO";

    private int saldo;
    private int ultimoNumero;

    public Ruleta(int saldo) {
        this.setSaldo(saldo);
    }

    public int getSaldo() {
        return saldo;
    }

    public int getUltimoNumero() {
        return ultimoNumero;
    }

    public void setSaldo(int saldo) {
        if (saldo < 0) {
            this.saldo = 0;
        } else {
            this.saldo = saldo;
        }
    }

    public int tirarBolita() {
        this.ultimoNumero = (int) (Math.random() * this.NUMEROS_RULETA);
        return this.ultimoNumero;
    }

    public String getColor(int numero) {
        String[] COLORES = {
            VERDE, ROJO, NEGRO, ROJO, NEGRO, ROJO, NEGRO, ROJO, NEGRO, ROJO, NEGRO, NEGRO, ROJO, NEGRO,
            ROJO, NEGRO, ROJO, NEGRO, ROJO, ROJO, NEGRO, ROJO, NEGRO, ROJO, NEGRO, ROJO, NEGRO, ROJO, NEGRO, NEGRO,
            ROJO, NEGRO, ROJO, NEGRO, ROJO, NEGRO, ROJO
        };
        if (numero >= 0 && numero < COLORES.length) {
            return COLORES[numero];
        }
        return VERDE;
    }

    private boolean checkNumero(int apostado) {
        return apostado == ultimoNumero;
    }

    private boolean checkFila(int fila) {
        if (ultimoNumero == 0) {
            return false;
        }
        if (fila == 1) {
            return ultimoNumero % 3 == 0;
        }
        if (fila == 2) {
            return ultimoNumero % 3 == 2;
        }
        if (fila == 3) {
            return ultimoNumero % 3 == 1;
        }
        return false;
    }

    private boolean checkColor(String color) {
        return getColor(ultimoNumero).equalsIgnoreCase(color);
    }

    private boolean checkParidad(String paridad) {
        if (ultimoNumero == 0) {
            return false;
        }
        boolean esPar = (ultimoNumero % 2 == 0);

        if (paridad.equalsIgnoreCase("PAR")) {
            return esPar;
        }
        if (paridad.equalsIgnoreCase("IMPAR")) {
            return !esPar;
        }
        return false;
    }

    private boolean checkDocena(int docena) {
        if (ultimoNumero == 0) {
            return false;
        }
        if (docena == 1) {
            return ultimoNumero >= 1 && ultimoNumero <= 12;
        }
        if (docena == 2) {
            return ultimoNumero >= 13 && ultimoNumero <= 24;
        }
        if (docena == 3) {
            return ultimoNumero >= 25 && ultimoNumero <= 36;
        }
        return false;
    }

    public boolean esPar(int numero) {
        if (numero == 0) {
            return false;
        }
        return numero % 2 == 0;
    }

    public boolean apostarNumero(int numero, int cantidad) {
        if (cantidad > this.saldo || numero < 0 || numero > 36) {
            return false;
        }
        this.saldo -= cantidad;
        tirarBolita();
        if (checkNumero(numero)) {
            this.saldo += cantidad * MULTIPLICADOR_NUMERO;
            return true;
        }
        return false;
    }

    public boolean apostarColor(String color, int cantidad) {
        if (cantidad > this.saldo) {
            return false;
        }
        color = color.toUpperCase();
        if (!color.equals(ROJO) && !color.equals(NEGRO) && !color.equals(VERDE)) {
            return false;
        }
        this.saldo -= cantidad;
        tirarBolita();
        if (checkColor(color)) {
            if (color.equals(VERDE)) {
                this.saldo += cantidad * MULTIPLICADOR_VERDE;
            } else {
                this.saldo += cantidad * MULTIPLICADOR_ROJO;
            }
            return true;
        }
        return false;
    }

    public boolean apostarParidad(String paridad, int cantidad) {
        if (cantidad > this.saldo) {
            return false;
        }
        paridad = paridad.toUpperCase();
        if (!paridad.equals("PAR") && !paridad.equals("IMPAR")) {
            return false;
        }
        this.saldo -= cantidad;
        tirarBolita();
        if (checkParidad(paridad)) {
            this.saldo += cantidad * MULTIPLICADOR_PAR;
            return true;
        }
        return false;
    }

    public boolean apostarDocena(int docena, int cantidad) {
        if (cantidad > this.saldo || docena < 1 || docena > 3) {
            return false;
        }
        this.saldo -= cantidad;
        tirarBolita();
        if (checkDocena(docena)) {
            this.saldo += cantidad * MULTIPLICADOR_DOCENA;
            return true;
        }
        return false;
    }

    public boolean apostarFila(int fila, int cantidad) {
        if (cantidad > this.saldo || fila < 1 || fila > 3) {
            return false;
        }
        this.saldo -= cantidad;
        tirarBolita();
        if (checkFila(fila)) {
            this.saldo += cantidad * MULTIPLICADOR_FILA;
            return true;
        }
        return false;
    }
}