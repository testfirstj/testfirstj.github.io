package myfirstassert;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class GreeterTest {

    @Test
    public void greet_not_null_nor_empty_and_as_expected() {
        var subject = "World";
        Greeter greeter = new Greeter( subject );

        String greet = greeter.greet();
        assertThat( greet ) // <1>
                .isNotNull() // <2>
                .isNotEmpty() // <3>
                .isNotBlank() // <4>
                .contains( "Hello", "World" ) // <5>
                .isEqualTo( "Hello World"); // <6>

//        fail( "method greet_not_null_nor_empty_and_as_expected reached end. You know what to do." );
    }

}
