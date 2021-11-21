package consoleoutput;

/**
 * Write to somewhere and clear the output.
 * Consumer of text with Functional method 
 * {@code void appendText(String)}
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
@FunctionalInterface
public interface AppendAndClear {

    /**
     * Append a string to this AppendandClear.
     *
     * @param toAppend text to add.
     */
    void appendText( String toAppend );

    /**
     * Clear the output. Optional operation.
     */
    default void clear() {
    }
}
