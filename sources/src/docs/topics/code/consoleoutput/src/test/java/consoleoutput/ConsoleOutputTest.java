package consoleoutput;

import java.io.PrintStream;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class ConsoleOutputTest {
    //@Disabled("Think TDD")

    @Test
    public void tConsoleOutput() {

        ConsoleOutput cout = new ConsoleOutput();
        PrintStream out = cout.asPrintStream();

        System.setOut( out );

        System.out.println( "Hello world" );

        assertThat( cout.toString() ).contains( "Hello world" );

//        fail( "tConsoleOutput completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    public void tUsingAppenenAandClear() {
        final StringBuilder sb = new StringBuilder();

        AppendAndClear aac = new AppendAndClear() {
            final StringBuilder mysb = sb; // <1> pick up local var 

            @Override
            public void appendText( String toAppend ) {
                sb.append( toAppend );
            }

            @Override
            public void clear() {
                mysb.delete( 0, mysb.length() );
            }

        };
        ConsoleOutput cout = new ConsoleOutput( aac );
        PrintStream out = cout.asPrintStream();
        System.setOut( out );

        System.out.println( "Hello world" );

        assertThat( sb.toString() ).contains( "Hello world" );
        cout.clear();

        assertThat( sb.toString() ).isEmpty();

//        fail( "tUsingAppenenAandClear completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    public void tformattedPrint() {
        ConsoleOutput cout = new ConsoleOutput();
        PrintStream out = cout.asPrintStream();

        out.format( "%s at %d", "Oplegkaas", 957 );

        assertThat( cout.toString() ).contains( "at", "Oplegkaas", "957" );

//        fail( "tformattedPrint completed succesfully; you know what to do" );
    }
}
