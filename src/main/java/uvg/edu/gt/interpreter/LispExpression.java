package uvg.edu.gt.interpreter;

import java.util.Objects;

/**
 * La clase {@code LispExpression} representa una expresión en el lenguaje de programación LISP.
 * Es una clase abstracta que sirve como superclase para diferentes tipos de expresiones LISP,
 * como símbolos, números, listas, etc.
 */
public abstract class LispExpression {
    /**
     * Evalúa esta expresión LISP.
     *
     * @return El resultado de evaluar esta expresión.
     */
    public abstract Object evaluar();

    /**
     * Obtiene una representación en forma de cadena de esta expresión LISP.
     *
     * @return La representación en forma de cadena de esta expresión LISP.
     */
    @Override
    public abstract String toString();

    /**
     * Comprueba si esta expresión LISP es igual a otro objeto.
     *
     * @param obj El objeto con el que se va a comparar esta expresión LISP.
     * @return {@code true} si los objetos son iguales, {@code false} en caso contrario.
     */
    @Override
    public abstract boolean equals(Object obj);
}
