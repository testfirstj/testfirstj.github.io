package firstcontact;

import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author "Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}"
 */
public class GreetingTest {

    //@Disabled("Think TDD")
    @Test
    public void firstContact() {
        Greeting g = new Greeting( "Johnny" );
        String greet = g.greet();
        assertThat( greet )
                .as( "expecting polity greeting" )
                .contains( "Hello", "Johnny" );
//        fail( "method firstContact reached end. You know what to do." );
    }

}
