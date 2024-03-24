package uvg.edu.gt.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

            boolean condicionVerdadera = evaluarCondicion(condicion);
            if (condicionVerdadera) {
                // Si la condición es verdadera, devuelve la expresión asociada
                return expresion;
            }
        }

        // Si ninguna condición es verdadera, devuelve null o algún valor por defecto
        return null;
    }

    /**
     * Evalúa una condición del COND.
     *
     * @param condicion La condición a evaluar.
     * @return {@code true} si la condición es verdadera, {@code false} en caso contrario.
     */
    private static boolean evaluarCondicion(Object condicion) {
        if (condicion instanceof Boolean) {
            // Si la condición es un valor booleano, simplemente devuelve su valor
            return (boolean) condicion;
        } else if (condicion instanceof List) {
            // Si la condición es una expresión predicado, evalúa la expresión
            return evaluarPredicado((List<Object>) condicion);
        } else {
            // Si la condición no es ni un valor booleano ni una expresión predicado, la consideramos falsa
            return false;
        }
    }

    /**
     * Evalúa una expresión predicado.
     *
     * @param predicado La expresión predicado a evaluar.
     * @return {@code true} si la expresión predicado se evalúa como verdadera, {@code false} en caso contrario.
     */
    public static boolean evaluarPredicado(List<Object> predicado) {
        // Si el predicado está vacío, se considera falso
        if (predicado.isEmpty()) {
            return false;
        }

        // Obtenemos el operador del predicado (primer elemento de la lista)
        String operador = predicado.get(0).toString();

        // Dependiendo del operador, realizamos la evaluación correspondiente
        switch (operador) {
            case "EQUAL":
                // Si el operador es EQUAL, comparamos los dos argumentos
                if (predicado.size() != 3) {
                    throw new IllegalArgumentException("La expresión EQUAL debe tener dos argumentos");
                }
                Object arg1 = predicado.get(1);
                Object arg2 = predicado.get(2);
                return Objects.equals(arg1, arg2);
            case "ATOM":
                // Si el operador es ATOM, verificamos si el argumento es un átomo
                if (predicado.size() != 2) {
                    throw new IllegalArgumentException("La expresión ATOM debe tener un argumento");
                }
                Object argumentoAtom = predicado.get(1);
                return !(argumentoAtom instanceof List);
            case "LIST":
                // Si el operador es LIST, verificamos si el argumento es una lista
                if (predicado.size() != 2) {
                    throw new IllegalArgumentException("La expresión LIST debe tener un argumento");
                }
                Object argumentoList = predicado.get(1);
                return argumentoList instanceof List;
            case "<":
                // Si el operador es <, verificamos si el primer argumento es menor que el segundo
                if (predicado.size() != 3) {
                    throw new IllegalArgumentException("La expresión < debe tener dos argumentos");
                }
                Object argMenor1 = predicado.get(1);
                Object argMenor2 = predicado.get(2);
                if (argMenor1 instanceof Double && argMenor2 instanceof Double) {
                    return (Double) argMenor1 < (Double) argMenor2;
                } else {
                    throw new IllegalArgumentException("Los argumentos de la expresión < deben ser números");
                }
            case ">":
                // Si el operador es >, verificamos si el primer argumento es mayor que el segundo
                if (predicado.size() != 3) {
                    throw new IllegalArgumentException("La expresión > debe tener dos argumentos");
                }
                Object argMayor1 = predicado.get(1);
                Object argMayor2 = predicado.get(2);
                if (argMayor1 instanceof Double && argMayor2 instanceof Double) {
                    return (Double) argMayor1 > (Double) argMayor2;
                } else {
                    throw new IllegalArgumentException("Los argumentos de la expresión > deben ser números");
                }
                // Puedes agregar más casos para otros operadores según sea necesario
            default:
                // Si el operador no es reconocido, se considera falso
                return false;
        }
    }
}
