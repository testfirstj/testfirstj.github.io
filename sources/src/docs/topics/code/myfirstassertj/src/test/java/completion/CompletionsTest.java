package completion;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author "Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}"
 */
public class CompletionsTest {

    //@Disabled("Think TDD")
    @Test
    public void complete() {
        Box<String> sb = new Box<String>( 
                 """
                    "Hello"
                    Text here
                    and  
                """
        );
        Box<Integer> ib = new Box<Integer>( 42 );

        String sbc =  sb.get();
        System.out.println( "sbc = " + sbc );
        
        fail( "method complete reached end. You know what to do." );
    }

}
