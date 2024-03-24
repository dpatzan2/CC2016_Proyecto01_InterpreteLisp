package uvg.edu.gt.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * La clase {@code SetQ} proporciona un método para asignar valores a símbolos en el lenguaje de programación LISP,
 * utilizando la instrucción SETQ.
 */
public class SetQ {
    private static final Map<String, Object> entorno = new HashMap<>();

    /**
     * Asigna un valor a un símbolo en el entorno.
     *
     * @param nombre El nombre del símbolo.
     * @param valor  El valor a asignar al símbolo.
     */
    public static void setq(String nombre, Object valor) {
        entorno.put(nombre, valor);
    }

    /**
     * Obtiene el valor asociado a un símbolo en el entorno.
     *
     * @param nombre El nombre del símbolo.
     * @return El valor asociado al símbolo, o {@code null} si no hay ningún valor asociado.
     */
    public static Object get(String nombre) {
        return entorno.get(nombre);
    }

    /**
     * Verifica si un símbolo está definido en el entorno.
     *
     * @param nombre El nombre del símbolo.
     * @return {@code true} si el símbolo está definido, {@code false} de lo contrario.
     */
    public static boolean estaDefinido(String nombre) {
        return entorno.containsKey(nombre);
    }

    /**
     * Elimina un símbolo del entorno y su valor asociado.
     *
     * @param nombre El nombre del símbolo a eliminar.
     */
    public static void eliminar(String nombre) {
        entorno.remove(nombre);
    }

    /**
     * Limpia el entorno, eliminando todos los símbolos y valores asociados.
     */
    public static void limpiarEntorno() {
        entorno.clear();
    }

    /**
     * Imprime todos los símbolos y sus valores asociados en el entorno.
     */
    public static void imprimirEntorno() {
        System.out.println("Entorno:");
        for (Map.Entry<String, Object> entry : entorno.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
