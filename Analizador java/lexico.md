# Preguntas de Discusión

## Parte 1


1. **¿Qué pasa si eliminas los \b del patrón KEYWORD?**
\
El analizador toma el primer patron que reconozca, por lo tanto integer se define como palabra clave `int` y el identificador `eger`  
2. **¿Por qué el patrón KEYWORD debe evaluarse antes que IDENTIFIER?**
\
Porque orden de la jerarquia importa, ya que queremos que las palabras reservadas no puedan usarse como identificadores
3. **¿Podrías construir una sola ER que reconozca ambos tokens a la vez?**
\
En mi opinion, siento que es posible, pero necesitaria mas casos

## Parte 2


1. **¿Cómo manejarías el operador ternario ?: en tu analizador?**
\
En los if de una sola cadena, como en la revision de errores
2. **El operador >>> (shift sin signo) debe reconocerse antes que >>. ¿Por qué?**
\
Porque el orden importa, siempre deben ser cadenas de mayor a menor tamaño, ya que si fuera la menor regresaria `>` tres veces o `>>` y `>` por separado
3. **¿Qué diferencia hay entre un separador y un operador desde la perspectiva de la gramática?**
\
Cumplen diferentes funciones, como el separador la de no juntar lineas o encapsular ideas, y la del operador unir, juntar, restar, etc algun numero o alguna frase como la concatenacion de Strings

## Parte 3

1. **¿Por qué 08 NO es un octal válido en Java? ¿Cómo ajustarías la ER?**
\
Porque los octales solo tienen como valido los numeros 0 a 7, (8 numeros), solo validar numeros de `0-7`
2. **¿Cómo extenderías el patrón STRING para soportar Text Blocks de Java 15+ ("""...""")?**
\
Añadiria el caracter \" varias veces, ej `\"\"\"` o `(([^\"\"\"\\\\]|\\\\.)*\"\"\")`, para que verifique que al inicio de la cadena siempre sea `"""` y al final siempre termine en `"""`
3. **¿Tiene sentido que el analizador léxico verifique si un número está en rango (e.g., int > 2^31)?**
\
Es mi opinion no, ya que eso siento que es un trabajo del analizador sintactico, para que tenga sentido

## Parte 3

1. **¿Cómo manejaría tu lexer un comentario de bloque no cerrado al final del archivo?**
\
Al no leer nada extra del archivo con la linea de comentario, podria devolver un `UNKNOWN` para mostrar que no se encontro ninguna entrada compatible 
2. **¿Deberían los JavaDoc comments producir un token diferente a los block comments?**
\
No, ya que solo son comentarios para la documentacion que no tienen que ver en el codigo
3. **¿Cuál es la diferencia entre recuperación de errores en el lexer vs en el parser?**
\
En el lexer se identifican con un escape de errores a la salida, mientras que en el parser, se identifican como cadena desconocida `UNKNOWN`

# Cambios al codigo

En el codigo se utilizaron diferentes funciones y añadidos nuevos, por ejemplo:

### Cadenas de literales numericas

```java
+ "(?<HEXADECIMALLIT>\\b0[xX][0-9a-fA-F][0-9a-fA-F_]*[lL]?)\\b|"
+ "(?<BINARYLIT>\\b0[bB][01][01_]*[lL]?)\\b|"
+ "(?<OCTALLIT>\\b0[0-7]+[lL]?)\\b|"
+ "(?<INTEGERLIT>\\b0|[1-9][0-9_]*[lL]?)\\b|"
```
Se añadio `\\b` para que cada cadena pueda producir un error, ya que al final, cada una de estas debe estar separadas por un espacio, asi nos marca exactamente donde esta el error

### Salida de texto

Se creo una nueva entrada estatica, la cual es formada por un formato csv, donde la entrada principal define los valores que se va a formar, siguiendo el formato de un archivo csv, donde se define cada fila por su valor como "valor1, valor2, valor3"
```java
public static String salida = "Linea,Tipo,Token\n";
```
Cada salida de este archivo en donde estan definidas nos da lo siguiente, con su linea, palabra clave, y m.group() definiendo el token

```java
salida += line + "," + TokenType.KEYWORD + "," + m.group() + "\n";
```

Mientras que la definicion de errores sigue un formato similar, donde illegal es el token no reconocido
```java
salida += line + "," + "ERROR" + "," + illegal  + "\n";
```

La cadena principal se convierte a un archivo CSV con una funcion estatica definida en la siguiente funcion, donde se toma la cadena de entrada definida anteriormente para escribirlo en el archivo csv

```java
public static void crearArchivoCSV(String entrada) {
        try {
            Path path = Path.of("./salida.csv");
            // Escribir el string a un archivo
            Files.writeString(path, entrada, StandardCharsets.UTF_8);
            // Salida del archivo
            System.out.println("Archivo guardado en: " + path.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al escribir archivo: " + e.getMessage());
        }
    }
```

Tambien se añadio la lectura de archivos de una carpeta llamada "archivos" donde está bien definido que la carpeta esta en la ruta actual en `./archivos/Ejemplo2.java` (que se puede cambiar), si es que la ruta o el archivo no esta disponible, usa un placeholder para que ejecute el valor por defecto

```java
String filePath = "./archivos/Ejemplo2.java";
        
        String source = """
            /** JavaDoc comment */
            public class Prueba {
                public static void main(String[] args) {
                    integer x = 0xFF;    // hex
                    double pi = 3.14;
                    String s = "mundo";
                    if (x == 255 && pi > 0.0) { x++; }
                    @  // ← error léxico intencional
                }
            }
            """;
        try {
            source = Files.readString(Path.of(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
```

