package uvg.edu.gt.interpreter;

/**
 * La clase {@code Quote} proporciona un método para interrumpir el proceso de evaluación de expresiones
 * en el lenguaje de programación LISP, utilizando la instrucción QUOTE o ' (comilla simple).
 */
public class Quote {

    /**
     * Interrumpe el proceso de evaluación y devuelve la expresión sin evaluar.
     *
     * @param expresion La expresión a interrumpir.
     * @return La expresión sin evaluar.
     */
    public static Object quote(Object expresion) {
        return expresion;
    }
}
