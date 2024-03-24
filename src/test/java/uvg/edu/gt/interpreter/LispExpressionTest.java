package uvg.edu.gt.interpreter;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class LispExpressionTest {

    // Clase de prueba de ejemplo que extiende LispExpression
    private static class MockExpression extends LispExpression {
        private final Object value;

        public MockExpression(Object value) {
            this.value = value;
        }

        @Override
        public Object evaluar() {
            return value;
        }

        @Override
        public String toString() {
            return value.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            MockExpression that = (MockExpression) obj;
            return Objects.equals(value, that.value);
        }
    }

    @Test
    void evaluar_ReturnsCorrectResult() {
        // Arrange
        LispExpression expression = new MockExpression(42);

        // Act
        Object result = expression.evaluar();

        // Assert
        assertEquals(42, result);
    }

    @Test
    void toString_ReturnsCorrectStringRepresentation() {
        // Arrange
        LispExpression expression = new MockExpression(42);

        // Act
        String stringRepresentation = expression.toString();

        // Assert
        assertEquals("42", stringRepresentation);
    }

    @Test
    void equals_ReturnsTrueForEqualExpressions() {
        // Arrange
        LispExpression expression1 = new MockExpression(42);
        LispExpression expression2 = new MockExpression(42);

        // Act & Assert
        assertTrue(expression1.equals(expression2));
    }

    @Test
    void equals_ReturnsFalseForDifferentExpressions() {
        // Arrange
        LispExpression expression1 = new MockExpression(42);
        LispExpression expression2 = new MockExpression(24);

        // Act & Assert
        assertFalse(expression1.equals(expression2));
    }
}
