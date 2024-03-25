package uvg.edu.gt.interpreter;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;


public class ArithmeticOperationsTest {

    @Test
    public void testSumar() {
        List<Double> lista1 = Arrays.asList(1.0, 2.0, 3.0);
        Assertions.assertEquals(6.0, ArithmeticOperations.sumar(lista1));

        List<Double> lista2 = Arrays.asList(4.5, -2.5, 1.5, 3.5);
        Assertions.assertEquals(7.0, ArithmeticOperations.sumar(lista2));

        Assertions.assertThrows(IllegalArgumentException.class, () -> ArithmeticOperations.sumar(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArithmeticOperations.sumar(new ArrayList<>()));
    }

    @Test
    public void testRestar() {
        List<Double> lista1 = Arrays.asList(10.0, 2.0, 3.0);
        Assertions.assertEquals(5.0, ArithmeticOperations.restar(lista1));

        List<Double> lista2 = Arrays.asList(4.5, -2.5, 1.5, 3.5);
        Assertions.assertEquals(2.0, ArithmeticOperations.restar(lista2));

        Assertions.assertThrows(IllegalArgumentException.class, () -> ArithmeticOperations.restar(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArithmeticOperations.restar(new ArrayList<>()));
    }

    @Test
    public void testMultiplicar() {
        List<Double> lista1 = Arrays.asList(2.0, 3.0, 4.0);
        Assertions.assertEquals(24.0, ArithmeticOperations.multiplicar(lista1));

        List<Double> lista2 = Arrays.asList(1.5, -2.0, 2.5);
        Assertions.assertEquals(-7.5, ArithmeticOperations.multiplicar(lista2));

        Assertions.assertThrows(IllegalArgumentException.class, () -> ArithmeticOperations.multiplicar(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArithmeticOperations.multiplicar(new ArrayList<>()));
    }

    @Test
    public void testDividir() {
        List<Double> lista1 = Arrays.asList(10.0, 2.0, 2.0);
        Assertions.assertEquals(2.5, ArithmeticOperations.dividir(lista1));

        List<Double> lista2 = Arrays.asList(20.0, 4.0);
        Assertions.assertEquals(5.0, ArithmeticOperations.dividir(lista2));

        Assertions.assertThrows(IllegalArgumentException.class, () -> ArithmeticOperations.dividir(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArithmeticOperations.dividir(new ArrayList<>()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArithmeticOperations.dividir(Arrays.asList(1.0, 0.0)));
    }

    @Test
    public void testModulo() {
        List<Object> lista1 = Arrays.asList(10, 3, 2);
        Assertions.assertEquals(1, ArithmeticOperations.modulo(lista1));

        List<Object> lista2 = Arrays.asList(20, 7, 3);
        Assertions.assertEquals(1, ArithmeticOperations.modulo(lista2));

        Assertions.assertThrows(IllegalArgumentException.class, () -> ArithmeticOperations.modulo(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArithmeticOperations.modulo(new ArrayList<>()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArithmeticOperations.modulo(Arrays.asList(1, 2, 0)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArithmeticOperations.modulo(Arrays.asList(1, 2.5, 3)));
    }
}
