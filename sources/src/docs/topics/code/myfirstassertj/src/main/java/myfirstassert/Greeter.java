package myfirstassert;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class Greeter {

    private final String subject;
    private final String GLUE = " ";

    Greeter( String subject ) {
        this.subject = subject;
    }

    String greet() {
        return "Hello" + GLUE + subject;
    }

    public static void main( String[] args ) {
        System.out.println( new Greeter( "PRC2 Class" ).greet() );
    }
}
