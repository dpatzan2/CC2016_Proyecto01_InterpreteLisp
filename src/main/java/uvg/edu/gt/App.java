package uvg.edu.gt;

import uvg.edu.gt.interpreter.ArithmeticOperations;
import uvg.edu.gt.interpreter.Conditional;
import uvg.edu.gt.interpreter.FunctionDefinition;
import uvg.edu.gt.lexer.Lexer;
import uvg.edu.gt.utils.InputReader;
import uvg.edu.gt.utils.LispFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        mostrarMenu();
    }

    private static void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Cargar código LISP desde la consola");
        System.out.println("2. Cargar código LISP desde un archivo");
        System.out.println("Seleccione una opción:");

        int opcion = InputReader.leerEntero();

        switch (opcion) {
            case 1:
                cargarDesdeConsola();
                break;
            case 2:
                cargarDesdeArchivo();
                break;
            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                mostrarMenu();
                break;
        }
    }

    private static void cargarDesdeConsola() {
        System.out.println("Ingrese el código Lisp:");
        String codigoLisp = InputReader.leerLinea();
        evaluarCodigoLisp(codigoLisp);
    }

    private static void cargarDesdeArchivo() {
        System.out.println("Ingrese la ruta del archivo:");
        String rutaArchivo = InputReader.leerLinea();

        try {
            List<String> lineas = LispFileReader.leerArchivo(rutaArchivo);
            StringBuilder sb = new StringBuilder();
            for (String linea : lineas) {
                sb.append(linea).append("\n");
            }
            evaluarCodigoLisp(sb.toString());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static void evaluarCodigoLisp(String codigoLisp) {
        List<String> tokens = Lexer.analizar(codigoLisp);

        // Si la lista de tokens está vacía, muestra un error
        if (tokens.isEmpty()) {
            System.out.println("Error: Expresión Lisp vacía");
            return;
        }

        // Si la expresión comienza con un paréntesis, eliminamos el primer y último token
        if (tokens.get(0).equals("(") && tokens.get(tokens.size() - 1).equals(")")) {
            tokens = tokens.subList(1, tokens.size() - 1);
        }

        // Si la lista de tokens ahora está vacía, muestra un error
        if (tokens.isEmpty()) {
            System.out.println("Error: Expresión Lisp vacía");
            return;
        }

        if (tokens.get(0).equals("cond")) {
            // Implementa la lógica para evaluar expresiones COND
            List<List<Object>> condicionales = parseCondicionales(tokens.subList(1, tokens.size()));
            evaluarExpresionCond(condicionales);
        } else if (tokens.get(0).equals("ATOM")) {
            // Implementa la lógica para evaluar el predicado ATOM
            if (tokens.size() != 2) {
                System.out.println("Error: La expresión ATOM debe tener un argumento");
                return;
            }
            String argumento = tokens.get(1);
            boolean esAtom = !argumento.startsWith("(") && !argumento.endsWith(")");
            System.out.println("Resultado de la evaluación: " + esAtom);
        } else if (tokens.get(0).equals("LIST")) {
            // Implementa la lógica para evaluar el predicado LIST
            if (tokens.size() != 2) {
                System.out.println("Error: La expresión LIST debe tener un argumento");
                return;
            }
            String argumento = tokens.get(1);
            boolean esLista = argumento.startsWith("(") && argumento.endsWith(")");
            System.out.println("Resultado de la evaluación: " + esLista);
        } else if (tokens.get(0).equals("EQUAL")) {
            // Implementa la lógica para evaluar el predicado EQUAL
            if (tokens.size() != 3) {
                System.out.println("Error: La expresión EQUAL debe tener dos argumentos");
                return;
            }
            String arg1 = tokens.get(1);
            String arg2 = tokens.get(2);
            boolean sonIguales = arg1.equals(arg2);
            System.out.println("Resultado de la evaluación: " + sonIguales);
        } else if (tokens.get(0).equals("<")) {
            // Implementa la lógica para evaluar el predicado <
            if (tokens.size() != 3) {
                System.out.println("Error: La expresión < debe tener dos argumentos");
                return;
            }
            double num1 = Double.parseDouble(tokens.get(1));
            double num2 = Double.parseDouble(tokens.get(2));
            boolean esMenor = num1 < num2;
            System.out.println("Resultado de la evaluación: " + esMenor);
        } else if (tokens.get(0).equals(">")) {
            // Implementa la lógica para evaluar el predicado >
            if (tokens.size() != 3) {
                System.out.println("Error: La expresión > debe tener dos argumentos");
                return;
            }
            double num1 = Double.parseDouble(tokens.get(1));
            double num2 = Double.parseDouble(tokens.get(2));
            boolean esMayor = num1 > num2;
            System.out.println("Resultado de la evaluación: " + esMayor);
        } else {
            System.out.println("Error: Operación no reconocida");
        }

        if (tokens.get(0).equals("defun")) {
            // Lógica para definir la función
            // Aquí debería ir la lógica para analizar y ejecutar la definición de función
            System.out.println("Función definida: " + tokens.get(1));
        } else {
            // Si el primer token no es una definición de función, intentamos evaluar como llamada de función
            String nombreFuncion = tokens.get(0);
            if (FunctionDefinition.llamarFuncionExiste(nombreFuncion)) {
                // Si es una llamada de función, se evalúa utilizando el intérprete Lisp
                List<Object> args = new ArrayList<>();
                for (int i = 1; i < tokens.size(); i++) {
                    if (tokens.get(i).equals("(")) {
                        // Encontramos una expresión dentro de paréntesis, evaluamos recursivamente
                        StringBuilder subExpresion = new StringBuilder();
                        int nivelParentesis = 1;
                        while (nivelParentesis != 0 && i + 1 < tokens.size()) {
                            i++;
                            String token = tokens.get(i);
                            if (token.equals("(")) {
                                nivelParentesis++;
                            } else if (token.equals(")")) {
                                nivelParentesis--;
                            }
                            subExpresion.append(token).append(" ");
                        }
                        args.add(subExpresion.toString().trim());
                    } else {
                        args.add(tokens.get(i));
                    }
                }

                // Evaluar la llamada de función
                Object resultado = FunctionDefinition.llamarFuncion(nombreFuncion, args);
                System.out.println("Resultado de la evaluación: " + resultado);
            } else {
                // Si no es una llamada de función válida, muestra un error
                System.out.println("Error: Función no definida - " + nombreFuncion);
            }
        }

        // Si el primer token es una comilla simple ('), o la palabra "quote", es una llamada a la función quote
        if (tokens.get(0).equals("'") || tokens.get(0).equalsIgnoreCase("quote")) {
            // Imprimir la expresión sin evaluar ningún token
            if (tokens.size() == 1) {
                System.out.println("Error: La expresión 'quote' debe tener un argumento");
                return;
            }
            String argumento = String.join(" ", tokens.subList(1, tokens.size()));
            System.out.println("Resultado de la evaluación: " + argumento);
            return;
        }

        // Si el primer token es un operador válido, realiza la operación aritmética
        if ("+-*/".contains(tokens.get(0))) {
            String operador = tokens.get(0);
            List<Double> operandos = new ArrayList<>();
            for (int i = 1; i < tokens.size(); i++) {
                try {
                    operandos.add(Double.parseDouble(tokens.get(i)));
                } catch (NumberFormatException e) {
                    System.out.println("Error: Los tokens no son números decimales válidos.");
                    return;
                }
            }

            // Realizar la operación correspondiente
            double resultado;
            switch (operador) {
                case "+":
                    resultado = ArithmeticOperations.sumar(operandos);
                    break;
                case "-":
                    resultado = ArithmeticOperations.restar(operandos);
                    break;
                case "*":
                    resultado = ArithmeticOperations.multiplicar(operandos);
                    break;
                case "/":
                    resultado = ArithmeticOperations.dividir(operandos);
                    break;
                default:
                    System.out.println("Error: Operador no reconocido");
                    return;
            }

            System.out.println("Resultado de la evaluación: " + resultado);
        } else if (tokens.get(0).equals("'")) {
            // Si el primer token es "'", implementamos el comportamiento de 'quote'
            // Devolvemos el argumento sin evaluar
            if (tokens.size() == 1) {
                System.out.println("Error: La expresión 'quote' debe tener un argumento");
                return;
            }
            String argumento = String.join(" ", tokens.subList(1, tokens.size()));
            System.out.println("Resultado de la evaluación: " + argumento);
        } else {
            // Si no es una operación aritmética, intenta evaluar como llamada de función
            String nombreFuncion = tokens.get(0);
            if (FunctionDefinition.llamarFuncionExiste(nombreFuncion)) {
                // Si es una llamada de función, se evalúa utilizando el intérprete Lisp
                List<Object> args = new ArrayList<>();
                for (int i = 1; i < tokens.size(); i++) {
                    if (tokens.get(i).equals("(")) {
                        // Encontramos una expresión dentro de paréntesis, evaluamos recursivamente
                        StringBuilder subExpresion = new StringBuilder();
                        int nivelParentesis = 1;
                        while (nivelParentesis != 0 && i + 1 < tokens.size()) {
                            i++;
                            String token = tokens.get(i);
                            if (token.equals("(")) {
                                nivelParentesis++;
                            } else if (token.equals(")")) {
                                nivelParentesis--;
                            }
                            subExpresion.append(token).append(" ");
                        }
                        args.add(subExpresion.toString().trim());
                    } else {
                        args.add(tokens.get(i));
                    }
                }

                // Evaluar la llamada de función
                Object resultado = FunctionDefinition.llamarFuncion(nombreFuncion, args);
                System.out.println("Resultado de la evaluación: " + resultado);
            } else {
                // Si no es una llamada de función válida, muestra un error
                System.out.println("Error: Función no definida - " + nombreFuncion);
            }
        }
    }


    /**
     * Parsea la lista de tokens para obtener una lista de expresiones condicionales (condicionales).
     *
     * @param tokens Lista de tokens.
     * @return Lista de expresiones condicionales.
     */
    private static List<List<Object>> parseCondicionales(List<String> tokens) {
        List<List<Object>> condicionales = new ArrayList<>();
        List<Object> condicionalActual = new ArrayList<>();
        int nivelParentesis = 0;

        for (String token : tokens) {
            if (token.equals("(")) {
                nivelParentesis++;
                // Si comenzamos una nueva expresión condicional, limpiamos la lista actual
                if (nivelParentesis == 1) {
                    condicionalActual.clear();
                }
            } else if (token.equals(")")) {
                nivelParentesis--;
                // Si terminamos una expresión condicional, agregamos la lista actual a la lista de condicionales
                if (nivelParentesis == 0 && !condicionalActual.isEmpty()) {
                    condicionales.add(new ArrayList<>(condicionalActual));
                }
            } else if (nivelParentesis == 1) {
                // Agregamos el token actual a la expresión condicional actual
                condicionalActual.add(token);
            }
        }

        return condicionales;
    }

    /**
     * Evalúa una lista de expresiones condicionales (condicionales).
     *
     * @param condicionales Lista de expresiones condicionales.
     */
    private static void evaluarExpresionCond(List<List<Object>> condicionales) {
        for (List<Object> condicional : condicionales) {
            boolean condicion = Conditional.evaluarPredicado((List<Object>) condicional.get(0));
            if (condicion) {
                System.out.println("Resultado de la evaluación: " + condicional.get(1));
                return;
            }
        }
        System.out.println("Error: Ninguna condición es verdadera en la expresión COND");
    }

}
