package assertjdemo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class University {

    static University hogwarts = new University();
    List<Professor> profs = new ArrayList<>();
    List<Student> students = new ArrayList<>();
    
    void addProfessor( Professor prof ) throws IllegalArgumentException {
        if ( prof.teaches( "Black Arts" ) ) {
            throw new IllegalArgumentException( "professor should teach a subject" );
        }
        profs.add( prof );
    }

    void addStudent( Student draco ) throws IllegalArgumentException {
        students.add( draco );
    }
    
}
