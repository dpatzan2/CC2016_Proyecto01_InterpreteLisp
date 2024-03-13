package uvg.edu.gt.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase {@code LispFileReader} se utiliza para leer un archivo LISP y convertirlo en una lista de cadenas.
 */
public class LispFileReader {

    /**
     * Lee un archivo LISP y devuelve su contenido como una lista de cadenas.
     *
     * @param rutaArchivo La ruta del archivo a leer.
     * @return Una lista de cadenas que representa el contenido del archivo.
     * @throws IOException si ocurre un error de entrada/salida al leer el archivo.
     */
    public static List<String> leerArchivo(String rutaArchivo) throws IOException {
        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        }

        return lineas;
    }
}
