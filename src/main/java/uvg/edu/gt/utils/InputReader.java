package uvg.edu.gt.utils;

import java.util.Scanner;

/**
 * La clase {@code InputReader} se utiliza para leer la entrada del usuario desde la consola.
 */
public class InputReader {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Lee una línea de texto ingresada por el usuario desde la consola.
     *
     * @return La línea de texto ingresada por el usuario.
     */
    public static String leerLinea() {
        return scanner.nextLine();
    }

    /**
     * Cierra el objeto Scanner utilizado para leer la entrada del usuario.
     * Se recomienda llamar a este método al finalizar la lectura de la entrada.
     */
    public static void cerrarScanner() {
        scanner.close();
    }
}
