package pgjdbcdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates a connection using the DriverManager facility and reading connection
 * information (properties) from a "well know location". The default well known
 * location is the root directory of the project. 
 * 
 * The names can be read from the public constants.
 * All properties recognised by the @see{java.sql.DriverManager} are understood.
 * In addition, the value url is expected in the properties and defaults to
 * <tt>jdbc:postgresql://localhost/presidentDB</tt>
 * 
 *
 * @author hom
 */
public class ConnectionFactory {

    private final Properties properties;
    public static final String PROP_FILENAME = "connection.properties";
    public static final String DEFAULT_PROPERTIES = "resources/connection.properties";
    public final boolean verbose;
    private boolean foundFile = false;

    public boolean isFoundFile() {
        return foundFile;
    }
    
    /**
     * Create factory being silent about failed attempt of getting properties from 
     * well known location.
     * @throws IOException when reading properties fails.
     */
    public ConnectionFactory() throws IOException {
        this( false );
    }

    /**
     * Create a connection factory.
     * @param verbose logging. When set, use the logger, otherwise be silent.
     * @throws IOException when reading properties fails.
     */
    public ConnectionFactory( boolean verbose ) throws IOException {
        this.verbose = verbose;
        this.properties = new Properties();
        // first attempt to get a normal file from the well known location
        InputStream inputStream = null;
        String usedFile=PROP_FILENAME;
        try {
            File f = new File( usedFile );
            inputStream = new FileInputStream( f );
            foundFile = true;
        } catch ( FileNotFoundException ignored ) {
            if ( verbose ) {
                Logger.getLogger( ConnectionFactory.class.getName() ).log( Level.INFO,
                        "attempt to read from well known location '"
                        + PROP_FILENAME
                        + "' failed, attempting default configuration '"
                        + DEFAULT_PROPERTIES
                        + "' from jar/war/ear instead.", ignored );
            }
        }
        if ( !foundFile ) {
            usedFile = DEFAULT_PROPERTIES;
            inputStream = ConnectionFactory.class.getClassLoader()
                    .getResourceAsStream( usedFile );
        }
        System.out.println( "inputStream filename = " + usedFile );
        properties.load( inputStream );
        properties.forEach( ( a, b ) -> System.out.println( a + " = " + b ) );
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection( properties.getProperty( "url","jdbc:postgresql://localhost/presidentDB" ), properties );
    }

}
