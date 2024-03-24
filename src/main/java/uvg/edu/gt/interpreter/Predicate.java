package uvg.edu.gt.interpreter;

import java.util.List;
import java.util.Objects;

/**
 * La clase {@code Predicate} proporciona métodos para evaluar predicados en el lenguaje de programación LISP,
 * como ATOM, LIST, EQUAL, < y >.
 */
public class Predicate {

    /**
     * Verifica si el objeto dado es un átomo.
     *
     * @param obj El objeto a verificar.
     * @return {@code true} si el objeto es un átomo, {@code false} en caso contrario.
     */
    public static boolean esAtom(Object obj) {
        return !(obj instanceof List);
    }

    /**
     * Verifica si el objeto dado es una lista.
     *
     * @param obj El objeto a verificar.
     * @return {@code true} si el objeto es una lista, {@code false} en caso contrario.
     */
    public static boolean esList(Object obj) {
        return obj instanceof List;
    }

    /**
     * Verifica si dos objetos son iguales.
     *
     * @param obj1 El primer objeto a comparar.
     * @param obj2 El segundo objeto a comparar.
     * @return {@code true} si los objetos son iguales, {@code false} en caso contrario.
     */
    public static boolean igual(Object obj1, Object obj2) {
        return Objects.equals(obj1, obj2);
    }

    /**
     * Verifica si el primer objeto es menor que el segundo.
     *
     * @param obj1 El primer objeto a comparar.
     * @param obj2 El segundo objeto a comparar.
     * @return {@code true} si el primer objeto es menor que el segundo, {@code false} en caso contrario.
     * @throws IllegalArgumentException si los objetos no son comparables.
     */
    public static boolean menorQue(Object obj1, Object obj2) {
        if (!(obj1 instanceof Comparable && obj2 instanceof Comparable)) {
            throw new IllegalArgumentException("Los objetos no son comparables");
        }
        return ((Comparable) obj1).compareTo(obj2) < 0;
    }

    /**
     * Verifica si el primer objeto es mayor que el segundo.
     *
     * @param obj1 El primer objeto a comparar.
     * @param obj2 El segundo objeto a comparar.
     * @return {@code true} si el primer objeto es mayor que el segundo, {@code false} en caso contrario.
     * @throws IllegalArgumentException si los objetos no son comparables.
     */
    public static boolean mayorQue(Object obj1, Object obj2) {
        if (!(obj1 instanceof Comparable && obj2 instanceof Comparable)) {
            throw new IllegalArgumentException("Los objetos no son comparables");
        }
        return ((Comparable) obj1).compareTo(obj2) > 0;
    }
}