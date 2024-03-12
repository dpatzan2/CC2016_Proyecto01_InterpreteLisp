package uvg.edu.gt.interpreter;

import java.util.List;

/**
 * La clase {@code Conditional} proporciona métodos para evaluar expresiones condicionales
 * en el lenguaje de programación LISP, como la instrucción COND.
 */
public class Conditional {

    /**
     * Evalúa la expresión condicional COND.
     *
     * @param condicionales La lista de condiciones y expresiones asociadas.
     * @return El resultado de la primera expresión asociada cuya condición se evalúa como verdadera,
     *         o {@code null} si ninguna condición es verdadera.
     * @throws IllegalArgumentException si las condiciones no están en el formato correcto.
     */
    public static Object evaluarCond(List<List<Object>> condicionales) {
        for (List<Object> condicional : condicionales) {
            if (condicional.size() < 2) {
                throw new IllegalArgumentException("Las condiciones deben estar en el formato (condición expresión)");
            }

            Object condicion = condicional.get(0);
            Object expresion = condicional.get(1);

            if (!(condicion instanceof Boolean)) {
                throw new IllegalArgumentException("La condición debe ser un valor booleano");
            }

            if ((boolean) condicion) {
                return expresion;
            }
        }

        return null;
    }
}
