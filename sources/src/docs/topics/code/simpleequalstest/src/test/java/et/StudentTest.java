package et;

import static et.TestHelpers.verifyEqualsAndHashCode;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class StudentTest {

    //@Disabled
    @Test
    void testMethod() {
        Student ref = new Student( "Jan", LocalDate.of( 1999, 03, 23 ), 123 );
        Student equ = new Student( "Jan", LocalDate.of( 1999, 03, 23 ), 123 );
        Student sna = new Student( "Jen", LocalDate.of( 1999, 03, 23 ), 123 );
        Student sda = new Student( "Jan", LocalDate.of( 1998, 03, 23 ), 123 );
        Student sid = new Student( "Jan", LocalDate.of( 1999, 03, 23 ), 456 );
        verifyEqualsAndHashCode( ref, equ, sna, sda, sid );
//        fail( "testMethod reached it's and. You will know what to do." );
    }
}
