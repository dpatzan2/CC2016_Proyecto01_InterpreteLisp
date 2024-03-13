package uvg.edu.gt.model;

import uvg.edu.gt.interpreter.LispExpression;

import java.util.Objects;

/**
 * La clase {@code LispSymbol} representa un símbolo en el lenguaje de programación LISP.
 */
public class LispSymbol extends LispExpression {

    private final String nombre;

    /**
     * Crea un nuevo símbolo LISP con el nombre especificado.
     *
     * @param nombre El nombre del símbolo.
     */
    public LispSymbol(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre de este símbolo.
     *
     * @return El nombre del símbolo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Evalúa este símbolo LISP.
     *
     * @return El símbolo sin evaluar.
     */
    @Override
    public Object evaluar() {
        return this;
    }

    /**
     * Compara este símbolo con otro objeto y verifica si son iguales.
     *
     * @param obj El objeto a comparar.
     * @return {@code true} si el objeto es igual a este símbolo, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LispSymbol that = (LispSymbol) obj;
        return Objects.equals(nombre, that.nombre);
    }

    /**
     * Calcula el hash code de este símbolo.
     *
     * @return El hash code calculado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    /**
     * Devuelve una representación en forma de cadena de este símbolo.
     *
     * @return Una cadena que representa este símbolo.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
