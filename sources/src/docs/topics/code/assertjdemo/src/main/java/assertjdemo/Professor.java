package assertjdemo;

import java.time.LocalDate;
import static java.time.LocalDate.of;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class Professor {

    private final String firstName;
    private final String lastName;
    final LocalDate birthDate;
    final List<String> teaches = new ArrayList<>();

    public Professor( String firstName, String lastName, LocalDate birthDate ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Collection<String> getTeaches() {
        return Collections.unmodifiableCollection( teaches );
    }

    Professor addsubject(String... aSubject){
        teaches.addAll(asList(aSubject) );
        return this;
    }
    
    
    boolean teaches(String subject){
        return this.teaches.contains(subject );
    }
    
    List<String> teaches(){
        return Collections.unmodifiableList( teaches );
    }
    
    static Professor SNAPE= new Professor("Severus", "Snape", of(1960,1,9))
            .addsubject( "Defence against the Dark Arts", "Potions" );
    static Professor DUMBLEDORE= new Professor("Albus", "Dumbledore", of(1881,8,29))
            .addsubject( "Transfigurations");
    static Professor MCGONAGALL= new Professor("Mervina", "McGonagall", of(1957,10,4))
            .addsubject( "Transfigurations","");
    
}
