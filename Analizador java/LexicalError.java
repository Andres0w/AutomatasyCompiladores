public class LexicalError extends RuntimeException {

        private final int line, column;
        private final char illegal;

        public LexicalError(char c, int line, int col) {
            super(String.format(
                    "Se detecto un error léxico en [línea %d, col %d]: carácter ilegal '%c' (U+%04X)",
                    line, col, c, (int) c
            ));
            this.line = line;
            this.column = col;
            this.illegal = c;
        }
    }