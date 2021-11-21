package assertjdemo;

import java.time.LocalDate;
import static java.time.LocalDate.of;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
class Student {

    private final Integer studentNumber;
    private String firstName;
    private String lastName;
    final LocalDate birthDate;
    static int nextNumber = 1237;

    public Student( Integer studentNumber, String firstName, String lastName, LocalDate birthDate ) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Student( String firstName, String lastName, LocalDate birthDate ) {
        this( nextNumber++, firstName, lastName, birthDate );
    }

    /**
     * Get the value of studentNumber
     *
     * @return the value of studentNumber
     */
    public Integer getStudentNumber() {
        return studentNumber;
    }

    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     */
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     */
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    static Student HARRY = new Student( 12345, "Harry", "Potter", of( 1980, 07, 31 ) );
    static Student HERMIONE = new Student( 12346, "Hermione", "Granger", of( 1979, 9, 19 ) );
    static Student RON = new Student( 12347, "Ron", "Weasley", of( 1980, 3, 1 ) );
}
