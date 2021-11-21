package olifantysballs;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class OlifantysGumballTest {

    /**
     * Test of toString method, of class OlifantysGumball.
     */
    //@Disabled( "Think TDD" )
    @Test
    public void testToString() {
        OlifantysGumball ball = new OlifantysGumball( "CORAL" );
        assertThat( ball.toString() )
                .as( "has colour" )
                .contains( "CORAL" );
    }

}
