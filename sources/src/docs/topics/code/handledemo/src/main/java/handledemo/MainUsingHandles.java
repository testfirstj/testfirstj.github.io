package handledemo;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainUsingHandles {

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
        MethodHandle getFirstName = null;

        try {
            MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
            MethodType mt = MethodType.methodType( String.class );
            getFirstName = publicLookup.findVirtual( clz, methodName, mt );
        } catch ( NoSuchMethodException ex ) {
            Logger.getLogger( MainUsingHandles.class.getName() )
                    .log( Level.SEVERE, null, ex );
        } catch ( IllegalAccessException ex ) {
            Logger.getLogger( MainUsingHandles.class.getName() )
                    .log( Level.SEVERE, null, ex );
        }

        if ( getFirstName != null ) {
            try {
                String realName = (String) getFirstName.invoke( sam );
                System.out.println( "realname = " + realName );
            } catch ( Throwable ex ) {
                Logger.getLogger( MainUsingHandles.class.getName() )
                        .log( Level.SEVERE, null, ex );
            }
        }
    }

}
