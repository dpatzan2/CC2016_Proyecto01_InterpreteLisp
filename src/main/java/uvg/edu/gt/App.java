package uvg.edu.gt;

import uvg.edu.gt.interpreter.ArithmeticOperations;
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
}
