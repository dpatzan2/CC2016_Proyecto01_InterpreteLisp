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
    public static double sumar(List<Double> args) {
        if (args == null || args.isEmpty()) {
            throw new IllegalArgumentException("The list of arguments cannot be null or empty");
        }

        double suma = 0;
        for (double arg : args) {
            suma += arg;
        }
        return suma;
    }

    public static double restar(List<Double> args) {
        if (args == null || args.isEmpty()) {
            throw new IllegalArgumentException("The list of arguments cannot be null or empty");
        }

        double resta = args.get(0);
        for (int i = 1; i < args.size(); i++) {
            resta -= args.get(i);
        }
        return resta;
    }

    public static double multiplicar(List<Double> args) {
        if (args == null || args.isEmpty()) {
            throw new IllegalArgumentException("The list of arguments cannot be null or empty");
        }

        double producto = 1;
        for (double arg : args) {
            producto *= arg;
        }
        return producto;
    }

    public static double dividir(List<Double> args) {
        if (args == null || args.isEmpty()) {
            throw new IllegalArgumentException("The list of arguments cannot be null or empty");
        }

        double division = args.get(0);
        for (int i = 1; i < args.size(); i++) {
            if (args.get(i) == 0) {
                throw new IllegalArgumentException("Cannot divide by zero");
            }
            division /= args.get(i);
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

    public static double operar(String operador, List<Object> operandos) {
        double resultado = 0;
        if (operador.equals("+")) {
            for (Object operando : operandos) {
                resultado += convertirAValorNumerico(operando);
            }
        } else if (operador.equals("-")) {
            resultado = convertirAValorNumerico(operandos.get(0));
            for (int i = 1; i < operandos.size(); i++) {
                resultado -= convertirAValorNumerico(operandos.get(i));
            }
        } else if (operador.equals("*")) {
            resultado = 1;
            for (Object operando : operandos) {
                resultado *= convertirAValorNumerico(operando);
            }
        } else if (operador.equals("/")) {
            resultado = convertirAValorNumerico(operandos.get(0));
            for (int i = 1; i < operandos.size(); i++) {
                double valor = convertirAValorNumerico(operandos.get(i));
                if (valor == 0) {
                    throw new ArithmeticException("División por cero");
                }
                resultado /= valor;
            }
        }
        return resultado;
    }

    private static double convertirAValorNumerico(Object obj) {
        if (obj instanceof Integer) {
            return (int) obj;
        } else if (obj instanceof Double) {
            return (double) obj;
        } else {
            throw new IllegalArgumentException("El objeto no se puede convertir a valor numérico: " + obj);
        }
    }

    public static double sumarDoubles(List<Double> numeros) {
        if (numeros.isEmpty()) {
            throw new IllegalArgumentException("La lista de números está vacía");
        }
        double suma = numeros.get(0);
        for (int i = 1; i < numeros.size(); i++) {
            suma += numeros.get(i);
        }
        return suma;
    }

    public static double restarDoubles(List<Double> numeros) {
        if (numeros.isEmpty()) {
            throw new IllegalArgumentException("La lista de números está vacía");
        }
        double resta = numeros.get(0);
        for (int i = 1; i < numeros.size(); i++) {
            resta -= numeros.get(i);
        }
        return resta;
    }

    public static double multiplicarDoubles(List<Double> numeros) {
        if (numeros.isEmpty()) {
            throw new IllegalArgumentException("La lista de números está vacía");
        }
        double producto = numeros.get(0);
        for (int i = 1; i < numeros.size(); i++) {
            producto *= numeros.get(i);
        }
        return producto;
    }

    public static double dividirDoubles(List<Double> numeros) {
        if (numeros.isEmpty()) {
            throw new IllegalArgumentException("La lista de números está vacía");
        }
        double division = numeros.get(0);
        for (int i = 1; i < numeros.size(); i++) {
            division /= numeros.get(i);
        }
        return division;
    }
}
