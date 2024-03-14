package uvg.edu.gt.interpreter;

import java.util.List;

/**
 * La clase {@code ArithmeticOperations} proporciona métodos para realizar operaciones aritméticas
 * en el lenguaje de programación LISP, como suma, resta, multiplicación, división y módulo.
 */
public class ArithmeticOperations {

    /**
     * Realiza la suma de los elementos de la lista especificada.
     *
     * @param args La lista de números para sumar.
     * @return El resultado de la suma.
     * @throws IllegalArgumentException si la lista es nula o vacía, o si algún elemento no es un número.
     */
    public static int sumar(List<Integer> args) {
        if (args == null || args.isEmpty()) {   
            throw new IllegalArgumentException("La lista de argumentos no puede ser nula o vacía");
        }

        int suma = 0;
        for (Object arg : args) {
            if (!(arg instanceof Integer)) {
                throw new IllegalArgumentException("Todos los elementos de la lista deben ser números enteros");
            }
            suma += (int) arg;
        }
        return suma;
    }

    /**
     * Realiza la resta de los elementos de la lista especificada.
     *
     * @param args La lista de números para restar.
     * @return El resultado de la resta.
     * @throws IllegalArgumentException si la lista es nula o vacía, o si algún elemento no es un número.
     */
    public static int restar(List<Integer> args) {
        if (args == null || args.isEmpty()) {
            throw new IllegalArgumentException("La lista de argumentos no puede ser nula o vacía");
        }

        int resta = 0;
        boolean primerElemento = true;
        for (Object arg : args) {
            if (!(arg instanceof Integer)) {
                throw new IllegalArgumentException("Todos los elementos de la lista deben ser números enteros");
            }
            if (primerElemento) {
                resta = (int) arg;
                primerElemento = false;
            } else {
                resta -= (int) arg;
            }
        }
        return resta;
    }

    /**
     * Realiza la multiplicación de los elementos de la lista especificada.
     *
     * @param args La lista de números para multiplicar.
     * @return El resultado de la multiplicación.
     * @throws IllegalArgumentException si la lista es nula o vacía, o si algún elemento no es un número.
     */
    public static int multiplicar(List<Integer> args) {
        if (args == null || args.isEmpty()) {
            throw new IllegalArgumentException("La lista de argumentos no puede ser nula o vacía");
        }

        int producto = 1;
        for (Object arg : args) {
            if (!(arg instanceof Integer)) {
                throw new IllegalArgumentException("Todos los elementos de la lista deben ser números enteros");
            }
            producto *= (int) arg;
        }
        return producto;
    }

    /**
     * Realiza la división de los elementos de la lista especificada.
     *
     * @param args La lista de números para dividir.
     * @return El resultado de la división.
     * @throws IllegalArgumentException si la lista es nula o vacía, si algún elemento no es un número,
     *                                  o si se intenta dividir por cero.
     */
    public static double dividir(List<Integer> args) {
        if (args == null || args.isEmpty()) {
            throw new IllegalArgumentException("La lista de argumentos no puede ser nula o vacía");
        }

        double division = 0;
        boolean primerElemento = true;
        for (Object arg : args) {
            if (!(arg instanceof Integer)) {
                throw new IllegalArgumentException("Todos los elementos de la lista deben ser números enteros");
            }
            if (primerElemento) {
                division = (int) arg;
                primerElemento = false;
            } else {
                if ((int) arg == 0) {
                    throw new IllegalArgumentException("No se puede dividir por cero");
                }
                division /= (int) arg;
            }
        }
        return division;
    }

    /**
     * Realiza el módulo de los elementos de la lista especificada.
     *
     * @param args La lista de números para calcular el módulo.
     * @return El resultado del módulo.
     * @throws IllegalArgumentException si la lista es nula o vacía, si algún elemento no es un número,
     *                                  o si se intenta calcular el módulo con cero.
     */
    public static int modulo(List<Object> args) {
        if (args == null || args.isEmpty()) {
            throw new IllegalArgumentException("La lista de argumentos no puede ser nula o vacía");
        }

        int modulo = 0;
        boolean primerElemento = true;
        for (Object arg : args) {
            if (!(arg instanceof Integer)) {
                throw new IllegalArgumentException("Todos los elementos de la lista deben ser números enteros");
            }
            if (primerElemento) {
                modulo = (int) arg;
                primerElemento = false;
            } else {
                if ((int) arg == 0) {
                    throw new IllegalArgumentException("No se puede calcular el módulo con cero");
                }
                modulo %= (int) arg;
            }
        }
        return modulo;
    }
}
