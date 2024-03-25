package uvg.edu.gt.model;

import java.util.List;
import java.util.Objects;

/**
 * La clase {@code LispFunction} representa una función definida por el usuario en el lenguaje de programación LISP.
 */
public class LispFunction {

    private List<String> parametros;
    private String cuerpo;  

    /**
     * Crea una nueva función LISP con los parámetros y cuerpo especificados.
     *
     * @param parametros Los nombres de los parámetros de la función.
     * @param cuerpo     La expresión que representa el cuerpo de la función.
     */
    public LispFunction(List<String> parametros, String cuerpo) {
        this.parametros = parametros;
        this.cuerpo = cuerpo;
    }

    /**
     * Obtiene los nombres de los parámetros de esta función.
     *
     * @return Los nombres de los parámetros.
     */
    public List<String> getParametros() {
        return parametros;
    }

    /**
     * Obtiene el cuerpo de esta función.
     *
     * @return El cuerpo de la función.
     */
    public String getCuerpo() {
        return cuerpo;
    }

    /**
     * Evalúa esta función LISP con los argumentos especificados.
     *
     * @param args Los argumentos que se pasarán a la función.
     * @return El resultado de evaluar la función.
     * @throws IllegalArgumentException si el número de argumentos no coincide con el número de parámetros.
     */
    public Object evaluar(List<Object> args) {
        if (args.size() != parametros.size()) {
            throw new IllegalArgumentException("Número incorrecto de argumentos para la función");
        }

        // Crear un nuevo entorno local para la función
        // Asignar valores a los parámetros en el entorno local
        // Evaluar el cuerpo de la función con el nuevo entorno
        // Devolver el resultado de la evaluación del cuerpo
        return null; // Implementación de la evaluación del cuerpo de la función
    }

    

    /**
     * Compara esta función con otro objeto y verifica si son iguales.
     *
     * @param obj El objeto a comparar.
     * @return {@code true} si el objeto es igual a esta función, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LispFunction that = (LispFunction) obj;
        return parametros.equals(that.parametros) &&
                cuerpo.equals(that.cuerpo);
    }

    /**
     * Calcula el hash code de esta función.
     *
     * @return El hash code calculado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(parametros, cuerpo);
    }

    /**
     * Devuelve una representación en forma de cadena de esta función.
     *
     * @return Una cadena que representa esta función.
     */
    @Override
    public String toString() {
        return "LispFunction{" +
                "parametros=" + parametros +
                ", cuerpo=" + cuerpo +
                '}';
    }
}
