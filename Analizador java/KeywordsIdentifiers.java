
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.*;

public class KeywordsIdentifiers {

    // Tipos de token
    enum TokenType {
        KEYWORD, IDENTIFIER, WHITESPACE, UNKNOWN, SEPARATOR, OPERATOR, FLOATLIT, HEXADECIMALLIT,
        BINARYLIT, OCTALLIT, INTEGERLIT, STRINGLIT, CHARLIT
    }

    // Record para representar un Token (Java 16+)
    record Token(TokenType type, String lexeme, int line) {

        public String toString() {
            return String.format("[L%-3d] %-14s → \"%s\"", line, type, lexeme);
        }
    }

    // Patrón de keywords (todas las palabras reservadas de Java)
    static final String KW_PATTERN
            = "\\b(abstract|assert|boolean|break|byte|case|catch|char|"
            + "class|const|continue|default|do|double|else|enum|extends|"
            + "final|finally|float|for|if|implements|import|instanceof|"
            + "int|interface|long|new|package|private|protected|public|"
            + "return|short|static|super|switch|synchronized|this|throw|"
            + "throws|try|void|volatile|while|true|false|null)\\b";

    // Patrón MASTER con grupos nombrados
    static final Pattern MASTER = Pattern.compile(
            // 1. Comentarios primero (no producen tokens de salida)
            "(?<JAVADOC>/\\*\\*[\\s\\S]*?\\*/)|"
            + "(?<BLOCKCOMMENT>/\\*[\\s\\S]*?\\*/)|"
            + "(?<LINECOMMENT>//[^\\n]*)|"
            + // 2. Literales de cadena y char (contienen cualquier carácter)
            "(?<STRINGLIT>\"([^\"\\\\]|\\\\.)*\")|"
            + "(?<CHARLIT>'([^'\\\\]|\\\\.)')|"
            + // 3. Literales numéricos (FLOAT > HEX/BIN/OCT > INT)
            "(?<FLOATLIT>\\b[0-9][0-9_]*\\.[0-9][0-9_]*([eE][+-]?[0-9]+)?[fFdD]?)|"
            + "(?<HEXADECIMALLIT>\\b0[xX][0-9a-fA-F][0-9a-fA-F_]*[lL]?)\\b|"
            + "(?<BINARYLIT>\\b0[bB][01][01_]*[lL]?)\\b|"
            + "(?<OCTALLIT>\\b0[0-7]+[lL]?)\\b|"
            + "(?<INTEGERLIT>\\b0|[1-9][0-9_]*[lL]?)\\b|"
            + // 4. Keywords antes que identificadores
            "(?<KEYWORD>" + KW_PATTERN + ")|"
            + "(?<IDENTIFIER>[a-zA-Z_$][a-zA-Z0-9_$]*)|"
            + // 5. Operadores compuestos antes que simples
            "(?<OPERATOR>==|!=|<=|>=|&&|\\|\\||<<|>>>|>>|\\+\\+|--|\\+=|-=|\\*=|/=|[+\\-*/%<>=!&|^~?:])|"
            + // 6. Separadores
            "(?<SEPARATOR>[(){}\\[\\];,.])|"
            + // 7. Whitespace (descartar)
            "(?<WHITESPACE>[ \\t\\r\\n]+)",
            Pattern.MULTILINE
    );

    public static String salida = "Linea,Tipo,Token\n";

    // Método de Tokenización
    public static List<Token> tokenize(String source) {
        List<Token> tokens = new ArrayList<>();
        List<RuntimeException> errors = new ArrayList<>();
        Matcher m = MASTER.matcher(source);
        int line = 1, pos = 0;

        while (m.find()) {
            // Detectar caracteres no reconocidos
            if (m.start() > pos) {
                // String unknown = source.substring(pos, m.start());
                // tokens.add(new Token(TokenType.UNKNOWN, unknown, line));
                String illegal = source.substring(pos, m.start());
                int col = pos - source.lastIndexOf('\n', pos);
                System.err.printf("Error léxico [L%d, C%d]: '%s'%n", line, col, illegal);
                errors.add(new LexicalError(illegal.charAt(0), line, col));
                salida += line + "," + "ERROR" + "," + illegal  + "\n";
            }
            if (m.group("WHITESPACE") != null) {
                // Contar saltos de línea en el whitespace
                line += m.group("WHITESPACE").chars().filter(c -> c == '\n').count();
            } else if (m.group("KEYWORD") != null) {
                tokens.add(new Token(TokenType.KEYWORD, m.group(), line));
                salida += line + "," + TokenType.KEYWORD + "," + m.group() + "\n";
            } else if (m.group("IDENTIFIER") != null) {
                tokens.add(new Token(TokenType.IDENTIFIER, m.group(), line));
                salida += line + "," + TokenType.IDENTIFIER + "," + m.group() + "\n";
            } else if (m.group("OPERATOR") != null) {
                tokens.add(new Token(TokenType.OPERATOR, m.group(), line));
                salida += line + "," + TokenType.OPERATOR + "," + m.group() + "\n";
            } else if (m.group("SEPARATOR") != null) {
                tokens.add(new Token(TokenType.SEPARATOR, m.group(), line));
                salida += line + "," + TokenType.SEPARATOR + "," + m.group() + "\n";
            } else if (m.group("FLOATLIT") != null) {
                tokens.add(new Token(TokenType.FLOATLIT, m.group(), line));
                salida += line + "," + TokenType.FLOATLIT + "," + m.group() + "\n";
            } else if (m.group("HEXADECIMALLIT") != null) {
                tokens.add(new Token(TokenType.HEXADECIMALLIT, m.group(), line));
                salida += line + "," + TokenType.HEXADECIMALLIT + "," + m.group() + "\n";
            } else if (m.group("BINARYLIT") != null) {
                tokens.add(new Token(TokenType.BINARYLIT, m.group(), line));
                salida += line + "," + TokenType.BINARYLIT + "," + m.group() + "\n";
            } else if (m.group("OCTALLIT") != null) {
                tokens.add(new Token(TokenType.OCTALLIT, m.group(), line));
                salida += line + "," + TokenType.OCTALLIT + "," + m.group() + "\n";
            } else if (m.group("INTEGERLIT") != null) {
                tokens.add(new Token(TokenType.INTEGERLIT, m.group(), line));
                salida += line + "," + TokenType.INTEGERLIT + "," + m.group() + "\n";
            } else if (m.group("STRINGLIT") != null) {
                tokens.add(new Token(TokenType.STRINGLIT, m.group(), line));
                salida += line + "," + TokenType.STRINGLIT + "," + m.group() + "\n";
            } else if (m.group("CHARLIT") != null) {
                tokens.add(new Token(TokenType.CHARLIT, m.group(), line));
                salida += line + "," + TokenType.CHARLIT + "," + m.group() + "\n";
            }
            pos = m.end();
        }
        return tokens;
    }

    public static void crearArchivoCSV(String entrada) {
        try {
            Path path = Path.of("./salida.csv");
            // Write the string to the file using UTF-8 encoding
            Files.writeString(path, entrada, StandardCharsets.UTF_8);

            System.out.println("Archivo guardado en: " + path.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al escribir archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // String filePath = "./archivos/Ejemplo1.java";
        String filePath = "./archivos/Ejemplo2.java";
        // String filePath = "./archivos/EjemploError.java";
        
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
        
        tokenize(source).forEach(System.out::println);
        
        crearArchivoCSV(salida);

    }
}
