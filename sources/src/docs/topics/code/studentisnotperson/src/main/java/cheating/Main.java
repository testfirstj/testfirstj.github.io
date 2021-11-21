package cheating;

import java.util.ArrayList;
import java.util.List;

class Person {
}

class Student extends Person {
}

/**
 *
 * @author "Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}"
 */
public class Main {

    public static void main( String[] args ) {
        List<Student> sl = new ArrayList<Student>();
        sl.add(new Student()); //accepted
        List ol = (List) sl; // give the compiler a raw alias
        ol.add( new Person() ); // so it will gladly accept this
        Student serena = sl.get( 1 ); // and make you program fall on its face with a Class cast exception.
        System.out.println( "serena = " + serena.toString() );
    }
    
}
