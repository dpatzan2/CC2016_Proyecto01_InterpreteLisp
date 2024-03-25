package uvg.edu.gt.interpreter;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PredicateTest {

    @Test
    void esAtom_ReturnsTrueForAtom() {
        // Arrange
        Object atom = 42;

        // Act
        boolean result = Predicate.esAtom(atom);

        // Assert
        assertTrue(result);
    }

    @Test
    void esAtom_ReturnsFalseForList() {
        // Arrange
        Object list = new ArrayList<>();

        // Act
        boolean result = Predicate.esAtom(list);

        // Assert
        assertFalse(result);
    }

    @Test
    void esList_ReturnsTrueForList() {
        // Arrange
        Object list = new ArrayList<>();

        // Act
        boolean result = Predicate.esList(list);

        // Assert
        assertTrue(result);
    }

    @Test
    void esList_ReturnsFalseForAtom() {
        // Arrange
        Object atom = 42;

        // Act
        boolean result = Predicate.esList(atom);

        // Assert
        assertFalse(result);
    }

    @Test
    void igual_ReturnsTrueForEqualObjects() {
        // Arrange
        Object obj1 = "hello";
        Object obj2 = "hello";

        // Act & Assert
        assertTrue(Predicate.igual(obj1, obj2));
    }

    @Test
    void igual_ReturnsFalseForDifferentObjects() {
        // Arrange
        Object obj1 = "hello";
        Object obj2 = "world";

        // Act & Assert
        assertFalse(Predicate.igual(obj1, obj2));
    }

    @Test
    void menorQue_ReturnsTrueForLessThan() {
        // Arrange
        int num1 = 5;
        int num2 = 10;

        // Act & Assert
        assertTrue(Predicate.menorQue(num1, num2));
    }

    @Test
    void menorQue_ReturnsFalseForGreaterThan() {
        // Arrange
        int num1 = 10;
        int num2 = 5;

        // Act & Assert
        assertFalse(Predicate.menorQue(num1, num2));
    }

    @Test
    void menorQue_ThrowsExceptionForNonComparableObjects() {
        // Arrange
        Object obj1 = new Object();
        Object obj2 = new Object();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Predicate.menorQue(obj1, obj2));
    }

    @Test
    void mayorQue_ReturnsTrueForGreaterThan() {
        // Arrange
        int num1 = 10;
        int num2 = 5;

        // Act & Assert
        assertTrue(Predicate.mayorQue(num1, num2));
    }

    @Test
    void mayorQue_ReturnsFalseForLessThan() {
        // Arrange
        int num1 = 5;
        int num2 = 10;

        // Act & Assert
        assertFalse(Predicate.mayorQue(num1, num2));
    }

    @Test
    void mayorQue_ThrowsExceptionForNonComparableObjects() {
        // Arrange
        Object obj1 = new Object();
        Object obj2 = new Object();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> Predicate.mayorQue(obj1, obj2));
    }
}
