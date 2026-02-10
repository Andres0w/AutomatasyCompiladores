package cadenas1;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        System.out.println("Analizador de cadenas de simbolos");
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cadena a analizar: ");
        String cadena = sc.nextLine();
        List<String> numEnteros = new ArrayList<>();
        List<String> palabrasMinusculas = new ArrayList<>();
        List<String> palabrasMayusculas = new ArrayList<>();
        List<String> identificadores = new ArrayList<>();
        String[] tokens = cadena.split(" ");
        for (String token : tokens) {
            if (token.matches("\\d+")) {
                numEnteros.add(token);
            } else if (token.matches("[a-z]+")) {
                palabrasMinusculas.add(token);
            } else if (token.matches("[A-Z]+")) {
                palabrasMayusculas.add(token);
            } else if (token.matches("^(?![0-9])[a-zA-Z0-9]+")) {
                identificadores.add(token);
            }
        }

        System.out.println("Números enteros: " + numEnteros);
        System.out.println("Palabras en minúscula: " + palabrasMinusculas);
        System.out.println("Palabras en mayúscula: " + palabrasMayusculas);
        System.out.println("Identificadores: " + identificadores);
        sc.close();
    }
}