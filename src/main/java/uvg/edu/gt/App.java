package uvg.edu.gt;

import uvg.edu.gt.interpreter.ArithmeticOperations;
import uvg.edu.gt.interpreter.FunctionDefinition;
import uvg.edu.gt.lexer.Lexer;
import uvg.edu.gt.model.LispFunction;
import uvg.edu.gt.utils.InputReader;
import uvg.edu.gt.utils.LispFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

    private static final Map<String, LispFunction> funcionesDefinidas = new HashMap<>();

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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la ruta del archivo:");
        String rutaArchivo = scanner.nextLine();

        try {
            List<String> lineas = LispFileReader.leerArchivo(rutaArchivo);

            String nombreFuncion = null;
            List<String> parametros = new ArrayList<>();
            List<String> cuerpo = new ArrayList<>();

            for (String linea : lineas) {
                if (linea.trim().startsWith("(defun")) {
                    if (nombreFuncion != null) {
                        // Ya se encontró una definición previa, procesarla
                        procesarDefinicion(nombreFuncion, parametros, cuerpo);
                        // Reiniciar las listas para la próxima definición
                        parametros.clear();
                        cuerpo.clear();
                    }
                    // Extraer el nombre de la función y los parámetros
                    nombreFuncion = linea.trim().substring(7).split("\\s+")[0];
                    parametros.addAll(Arrays.asList(linea.trim().substring(7).split("\\s+")[1].replaceAll("\\)", "").split("\\(")[1].split(" ")));
                } else if (nombreFuncion != null) {
                    cuerpo.add(linea.trim());
                }
            }

            // Procesar la última definición
            if (nombreFuncion != null) {
                procesarDefinicion(nombreFuncion, parametros, cuerpo);
            }

            System.out.println("Funciones definidas:");
            for (String nombre : funcionesDefinidas.keySet()) {
                System.out.println(nombre);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static void procesarDefinicion(String nombreFuncion, List<String> parametros, List<String> cuerpo) {
        // Convertir el cuerpo del texto Lisp a una cadena
        String cuerpoComoCadena = String.join("\n", cuerpo);
    
        // Crear una instancia de LispFunction
        LispFunction nuevaFuncion = new LispFunction(parametros, cuerpoComoCadena);
    
        // Almacenar la nueva función definida
        funcionesDefinidas.put(nombreFuncion, nuevaFuncion);
    
        // Ejecutar la función recién definida
        Object resultado = ejecutar(Arrays.asList(nombreFuncion)); // Pasar el nombre de la función como una lista de una sola cadena
        if (resultado != null) {
            System.out.println("Resultado de la función " + nombreFuncion + ": " + resultado.toString());
        }
    }
    
    
    public static Object ejecutar(List<String> cuerpo) {
        // Evaluar la expresión correspondiente
        return evaluarExpresion(cuerpo);
    }    
    

    private static Object evaluarExpresion(List<String> expresion) {
        String funcion = expresion.get(0);
        List<Object> argumentos = new ArrayList<>();
    
        for (int i = 1; i < expresion.size(); i++) {
            if (expresion.get(i).startsWith("(")) {
                // Es una subexpresión, evaluarla recursivamente
                List<String> subexpresion = new ArrayList<>();
                int balance = 0;
    
                do {
                    String token = expresion.get(i);
                    subexpresion.add(token);
    
                    for (char c : token.toCharArray()) {
                        if (c == '(') {
                            balance++;
                        } else if (c == ')') {
                            balance--;
                        }
                    }
    
                    i++;
                } while (balance > 0);
    
                argumentos.add(evaluarExpresion(subexpresion));
                i--;
            } else {
                // Es un valor literal
                argumentos.add(expresion.get(i));
            }
        }
    
        if (funcionesDefinidas.containsKey(funcion)) {
            // Es una llamada a una función definida
            LispFunction lispFunction = funcionesDefinidas.get(funcion);
            return lispFunction.evaluar(argumentos);
        } else {
            // No se encontró la función
            throw new RuntimeException("La función '" + funcion + "' no está definida.");
        }
    }
    

    private static LispFunction interpretarDefinicionFuncion(String nombreFuncion, List<String> parametros, List<String> cuerpo) {
        // Crear una instancia de LispFunction
        LispFunction nuevaFuncion = new LispFunction(parametros, String.join("\n", cuerpo)); 
    
        // Ejecutar automáticamente la función
        Object resultado = ejecutar(Arrays.asList(nombreFuncion)); // Pasar el nombre de la función como una lista de una sola cadena
        if (resultado != null) {
            System.out.println("Resultado de la función " + nombreFuncion + ": " + resultado.toString());
        }
    
        return nuevaFuncion;
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
}
