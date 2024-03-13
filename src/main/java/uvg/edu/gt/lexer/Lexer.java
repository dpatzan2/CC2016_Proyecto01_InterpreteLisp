package uvg.edu.gt.lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * La clase {@code Lexer} se utiliza para analizar el código LISP y convertirlo en tokens.
 */
public class Lexer {

    /**
     * Convierte una cadena de entrada en una lista de tokens.
     *
     * @param entrada La cadena de entrada que se va a analizar.
     * @return Una lista de tokens.
     */
    public static List<String> analizar(String entrada) {
        List<String> tokens = new ArrayList<>();
        String[] palabras = entrada.split("\\s+");

        for (String palabra : palabras) {
            if (palabra.startsWith("(") && palabra.length() > 1) {
                tokens.add("(");
                tokens.addAll(analizar(palabra.substring(1)));
            } else if (palabra.endsWith(")") && palabra.length() > 1) {
                tokens.addAll(analizar(palabra.substring(0, palabra.length() - 1)));
                tokens.add(")");
            } else {
                tokens.add(palabra);
            }
        }

        return tokens;
    }
}
