package pgjdbcdemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hom
 */
public class SQLInjectionDemo {

    public static void main( String[] args ) throws
            SQLException {
        if (args.length < 1) {
            System.out.println( "no arguments" );
                System.exit(1);
        }
        System.out.println( "args[0] = [" + args[0] +']');
        String sqlTemplate = "select * from president where name like '%s'";
        String sql = String.format( sqlTemplate, args[0] );
        try ( Connection con = PgJDBCDemo.createDemoConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery( sql ); ) {
            new ResultSetPrinter( rs ).printTable( System.out );
        }
    }

}
