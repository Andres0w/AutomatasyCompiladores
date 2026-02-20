package archivo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class revisionArchivo {

    public static void crearArchivo(String salida) {
        Path filePath = Path.of("salida.txt");
        try {
            // Create the file if it doesn't exist, and write the string
            Files.writeString(
                    filePath,
                    salida,
                    StandardOpenOption.CREATE, // Create if not exists
                    StandardOpenOption.TRUNCATE_EXISTING // Overwrite if exists
            );

            System.out.println("Archivo creado (" + filePath.toAbsolutePath() + ")");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        FileInputStream fstream;
        DataInputStream in;
        BufferedReader br;
        String regexRFC = "[A-Z]{4}[0-9]{2}(0[1-9]|1[0-2])([0-3]1|[1-2][0-9]|0[1-9]|30)[A-Z0-9]{3}";
        String regexCURP = "[A-Z]{4}[0-9]{2}(0[1-9]|1[0-2])([0-3]1|[1-2][0-9]|0[1-9]|30)(H|M)(AG|(B(C|S))|(C(C|M|H|O|L))|(D(F|G))|(G(J|T))|HG|JA|(M(X|I|O))|(N(A|L|E))|OA|PU|QR|SL|SI|SO|TB|TL|VR|YU|ZA)([B-DF-HJ-NP-TV-Z]{3})[A-Z0-9]{2}";
        String regexINE = "[A-Z]{6}[0-9]{2}(0[1-9]|1[0-2])([0-3]1|[1-2][0-9]|0[1-9]|30)([0-3]1|[1-2][0-9]|0[1-9]|30)(H|M)[A-Z0-9]{3}";
        String regexActual = "";
        try {
            fstream = new FileInputStream("entrada.txt");
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            int totalDatos = 0;
            int valorActual = -1;
            String datoActual = "";
            String archivo = "";
            try {
                int line = 0;
                while ((strLine = br.readLine()) != null) {
                    line++;
                    if (strLine.startsWith("#")) {
                        String data[] = strLine.split(" ");
                        switch (data[0]) {
                            case "#DATO":
                                // System.out.println(data[2]);
                                if (data[2].equals("RFC")) {
                                    regexActual = regexRFC;
                                    datoActual = "RFC";
                                } else if (data[2].equals("CURP")) {
                                    regexActual = regexCURP;
                                    datoActual = "CURP";
                                } else if (data[2].equals("INE")) {
                                    regexActual = regexINE;
                                    datoActual = "INE";
                                }
                                break;
                            case "#TOTAL":
                                // System.out.println(data[2]);
                                try {
                                    totalDatos = Integer.parseInt(data[2]);
                                    valorActual = totalDatos;
                                } catch (NumberFormatException e) {
                                    System.out.println("ERROR EN LINEA " + line +
                                            "\nRevise su archivo entrada.txt");
                                }
                                break;
                            default:
                                break;

                        }
                    }
                    if (!strLine.isEmpty() && (valorActual > 0) && !strLine.startsWith("#")) {
                        if (valorActual == totalDatos) {
                            archivo += "- - - - - - - - - - - - - - - - - - - " + "\n";
                            System.out.println("DATO ACTUAL: " + datoActual);
                            System.out.println("TOTAL DE DATOS: " + totalDatos);
                            archivo += "DATO ACTUAL: " + datoActual + "\n";
                            archivo += "TOTAL DE DATOS: " + totalDatos + "\n";
                        }
                        if (strLine.matches(regexActual)) {
                            System.out.println(strLine + " --- VALIDO");
                            archivo += strLine + " --- VALIDO" + "\n";
                        } else {
                            System.out.println(strLine + " --- NO VALIDO");
                            archivo += strLine + " --- NO VALIDO" + "\n";
                        }
                        valorActual--;
                    }
                }
                crearArchivo(archivo);
            } catch (IOException e) {
                System.err.println("No se");
            }
        } catch (FileNotFoundException e) {
            // TODO: handle exception
            System.out.println("No se encontro :v");
        }
        
    }
}