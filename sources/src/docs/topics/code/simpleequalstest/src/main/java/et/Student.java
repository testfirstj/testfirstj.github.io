package et;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Demo.
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class Student {
    final String name;
    final LocalDate birthDate;
    final int id;

    public Student( String name, LocalDate birthDate, int id ) {
        this.name = name;
        this.birthDate = birthDate;
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode( this.name );
        hash = 53 * hash + Objects.hashCode( this.birthDate );
        hash = 53 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Student other = (Student) obj;
        if ( this.id != other.id ) {
            return false;
        }
        if ( !Objects.equals( this.name, other.name ) ) {
            return false;
        }
        return Objects.equals( this.birthDate, other.birthDate );
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", birthDate=" + birthDate + ", id=" + id + '}';
    }
    
}
