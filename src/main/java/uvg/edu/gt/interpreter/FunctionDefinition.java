package uvg.edu.gt.interpreter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * La clase {@code FunctionDefinition} proporciona métodos para definir y llamar funciones
 * en el lenguaje de programación LISP, utilizando la instrucción DEFUN.
 */
public class FunctionDefinition {

    private static final Map<String, Function<List<Object>, Object>> funciones = new HashMap<>();

    /**
     * Define una nueva función LISP.
     *
     * @param nombre    El nombre de la función.
     * @param parametros Los nombres de los parámetros de la función.
     * @param cuerpo    La expresión que representa el cuerpo de la función.
     * @throws IllegalArgumentException si el nombre de la función ya está en uso.
     */
    public static void definirFuncion(String nombre, List<String> parametros, List<Object> cuerpo) {
        if (funciones.containsKey(nombre)) {
            throw new IllegalArgumentException("La función '" + nombre + "' ya está definida");
        }

        funciones.put(nombre, args -> {
            Map<String, Object> entornoLocal = new HashMap<>();
            if (parametros != null) {
                if (args.size() != parametros.size()) {
                    throw new IllegalArgumentException("Número incorrecto de argumentos para la función '" + nombre + "'");
                }
                for (int i = 0; i < parametros.size(); i++) {
                    entornoLocal.put(parametros.get(i), args.get(i));
                }
            }
            // Evaluar el cuerpo de la función con el nuevo entorno
            // Aquí debería ir la lógica para evaluar la expresión 'cuerpo'
            return null;
        });
    }

    /**
     * Llama a una función LISP previamente definida.
     *
     * @param nombre El nombre de la función.
     * @param args   Los argumentos que se pasarán a la función.
     * @return El resultado de llamar a la función.
     * @throws IllegalArgumentException si la función no está definida o si se proporciona un número incorrecto de argumentos.
     */
    public static Object llamarFuncion(String nombre, List<Object> args) {
        Function<List<Object>, Object> funcion = funciones.get(nombre);
        if (funcion == null) {
            throw new IllegalArgumentException("La función '" + nombre + "' no está definida");
        }

        return funcion.apply(args);
    }

    public static boolean llamarFuncionExiste(String nombre) {
        return funciones.containsKey(nombre);
    }
}
