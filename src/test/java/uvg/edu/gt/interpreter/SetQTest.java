package uvg.edu.gt.interpreter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SetQTest {

    @BeforeEach
    void setUp() {
        SetQ.setq("x", 10);
        SetQ.setq("y", "hello");
    }

    @AfterEach
    void tearDown() {
        SetQ.limpiarEntorno();
    }

    @Test
    void setq_AssignsValueToSymbol() {
        // Act
        SetQ.setq("z", 20);

        // Assert
        assertEquals(20, SetQ.get("z"));
    }

    @Test
    void get_RetrievesValueOfSymbol() {
        // Assert
        assertEquals(10, SetQ.get("x"));
        assertEquals("hello", SetQ.get("y"));
    }

    @Test
    void get_ReturnsNullForUndefinedSymbol() {
        // Assert
        assertNull(SetQ.get("undefined"));
    }

    @Test
    void estaDefinido_ReturnsTrueForDefinedSymbol() {
        // Assert
        assertTrue(SetQ.estaDefinido("x"));
        assertTrue(SetQ.estaDefinido("y"));
    }

    @Test
    void estaDefinido_ReturnsFalseForUndefinedSymbol() {
        // Assert
        assertFalse(SetQ.estaDefinido("undefined"));
    }

    @Test
    void eliminar_RemovesSymbolAndValue() {
        // Act
        SetQ.eliminar("x");

        // Assert
        assertFalse(SetQ.estaDefinido("x"));
        assertNull(SetQ.get("x"));
    }

    @Test
    void limpiarEntorno_RemovesAllSymbolsAndValues() {
        // Act
        SetQ.limpiarEntorno();

        // Assert
        assertTrue(SetQ.estaDefinido("x"));
        assertTrue(SetQ.estaDefinido("y"));
        assertNull(SetQ.get("x"));
        assertNull(SetQ.get("y"));
    }

    @Test
    void imprimirEntorno_PrintsAllSymbolsAndValues() {
        // Act
        SetQ.imprimirEntorno(); // Just check that it doesn't throw an exception
    }
}
