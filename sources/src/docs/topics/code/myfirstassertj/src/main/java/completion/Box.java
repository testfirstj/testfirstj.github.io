package completion;

/**
 *
 * @author "Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}"
 */
class Box<T> {

    final T value;
    Box( T i ) {
        value=i;
    }

    T get() {
        return value;
    }
    
}
