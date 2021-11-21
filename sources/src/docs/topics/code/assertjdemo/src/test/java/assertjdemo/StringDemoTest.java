package assertjdemo;

import static assertjdemo.Professor.DUMBLEDORE;
import static assertjdemo.Professor.MCGONAGALL;
import static assertjdemo.Professor.SNAPE;
import static assertjdemo.University.hogwarts;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class StringDemoTest {

    @Test
    public void stringContains() {

        String hi = "Hello world";

        assertThat( hi )
                .isNotNull()
                .doesNotContainIgnoringCase( "good" )
                .doesNotContainIgnoringCase( "bye" )
                .contains( "Hello", "World" );
        fail( "stringContains completed succesfully; you know what to do" );
    }

    @Test
    public void listDemo() {

        List<Student> students = List.of( Student.HARRY, Student.HERMIONE ); //<1>

        assertThat( students ).isNotEmpty()
                .extracting( s -> s.getStudentNumber() )
                .containsExactlyInAnyOrder( 12345, 12346 );

        fail( "listDemo completed succesfully; you know what to do" );
    }

    @Test
    public void hogwartsProfessors() {

        List<Professor> professors = List.of( SNAPE, DUMBLEDORE, MCGONAGALL );
        List<Professor> teachesTransfiguration = professors.stream()
                .filter( p -> p.teaches( "Transfigurations" ) ).collect( toList() );
        assertThat( teachesTransfiguration )
                .isNotNull()
                .hasSizeGreaterThanOrEqualTo( 2 )
                .contains( DUMBLEDORE, MCGONAGALL );

        fail( "hogwartsProfessors completed succesfully; you know what to do" );
    }

    @Test
    public void fileUsingMethod() throws IOException {

        Files.lines( Path.of( "puk.txt" ) );

        fail( "fileUsingMethod completed succesfully; you know what to do" );
    }

    @Test
    public void addIllegalProfessor() {
        Professor malfoy = new Professor( "Lucius", "Malfoy", LocalDate.of( 1953, 10, 9 ) );
        ThrowingCallable code = () -> {
            hogwarts.addProfessor( malfoy );
        };

        assertThatThrownBy( code )
                .isInstanceOf( Exception.class ) // <1>
                .isExactlyInstanceOf( IllegalArgumentException.class )
                .hasMessageContainingAll( "should", "teach" );
        fail( "addIllegalProfessor completed succesfully; you know what to do" );
    }

    @Test
    public void addStudent() {
        Student draco = new Student( "Draco", "Malfoy", LocalDate.of( 1980, 6, 5 ) );
        ThrowingCallable code = () -> {
            hogwarts.addStudent( draco );
        };

        assertThatCode( code )
                .as( "draco should be accepted to make the adventures possible" )
                .doesNotThrowAnyException();
        fail( "addStudent completed succesfully; you know what to do" );
    }

    @Test
    public void f1Champ() {
        Driver f1Champ = new Driver( "Louis Hamilton" );
        Driver louis = new Driver( "Louis Hamilton" );
        assertThat( f1Champ ).isEqualTo( louis );
        assertThat( f1Champ ).isSameAs( louis );
        fail( "f1Champ completed succesfully; you know what to do" );
    }
}
