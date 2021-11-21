package handledemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainUsingReflection {

    public static void main( String[] args ) {
        Class<Student> clz = Student.class;
        Student sam = new Student( 12134, "Simons",
                                   null,
                                   "Samantha",
                                   LocalDate.of( 199, 9, 11 ),
                                   2020, "s.simons@student.fontys.nl,",
                                   "F",
                                   "INF1-A" );

        String methodName="getFirstname";
        Method getFirstNameMethod=null;
                
        try {
            getFirstNameMethod = clz.getDeclaredMethod( methodName );
        } catch ( NoSuchMethodException ex ) {
            Logger.getLogger(MainUsingReflection.class.getName() )
                    .log( Level.SEVERE, null, ex );
        } catch ( SecurityException ex ) {
            Logger.getLogger(MainUsingReflection.class.getName() )
                    .log( Level.SEVERE, null, ex );
        } catch ( IllegalArgumentException ex ) {
            Logger.getLogger(MainUsingReflection.class.getName() )
                    .log( Level.SEVERE, null, ex );
        }
        
        
        if (getFirstNameMethod !=null) {
            
            try {
                String realName  = (String )getFirstNameMethod.invoke( sam);
                System.out.println( "realName = " + realName );
            } catch ( IllegalAccessException ex ) {
                Logger.getLogger( MainUsingReflection.class.getName() )
                        .log( Level.SEVERE, null, ex );
            } catch ( IllegalArgumentException ex ) {
                Logger.getLogger( MainUsingReflection.class.getName() )
                        .log( Level.SEVERE, null, ex );
            } catch ( InvocationTargetException ex ) {
                Logger.getLogger( MainUsingReflection.class.getName() )
                        .log( Level.SEVERE, null, ex );
            }
        }
    }

}
