package pgjdbcdemo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import static java.util.stream.Collectors.joining;

/**
 * Simple query tester. Test query with substituted parameters.
 *
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class QueryTester {

    /**
     * Test a query from a file
     *
     * @param args filename object... parameters
     * @throws IOException
     * @throws SQLException
     */
    public static void main( String[] args ) throws IOException, SQLException {
        
        usage( args );
        String sqlText = queryFromFile( args[ 0 ] );
        System.out.println( "sqlText = \"" + sqlText + '"' );
        String[] params = {};
        if ( args.length > 1 ) {
            params = Arrays.copyOfRange( args, 1, args.length );
        }
        
        System.out.println( "with args " + Arrays.toString( params ) );
        try ( Connection con = PgJDBCDemo.createDemoConnection() ) {
            con.setAutoCommit( false );
            
            try ( PreparedStatement pst = con.prepareStatement(
                    sqlText ); ) {
                int cid = 1;
                for ( Object arg : params ) {
                    pst.setObject( cid ++, arg );
                }
                ResultSet rs = pst.executeQuery();
                new ResultSetPrinter( rs ).printTable( System.out );
                con.commit();
            }
        }
    }
    
    private static String queryFromFile( String fileName ) throws IOException {
        final String queryText
                = Files.lines( Paths.get( fileName ) )
                        .filter( s ->  ! s.startsWith( "--" ) )
                        .filter( s ->  ! s.isEmpty() )
                        .collect( joining( System
                                .lineSeparator() ) );
        return queryText;
    }
    
    private static void usage( String[] args ) {
        if ( args.length < 1 ) {
            System.out.println( "call me with at least the query file name" );
            throw new IllegalArgumentException( "go figure" );
        }
    }
}
