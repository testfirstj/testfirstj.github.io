package pgjdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author hom
 */
public class PreparedStatementDemo {

    public static void main( String[] args ) throws
            SQLException {
        String sql = "select * from president where name like ?";
        String parm = args.length > 0 ? args[ 0 ] : "%B%";
//        try ( Connection con = PgJDBCDemo.createDemoConnection();
//            PreparedStatement pst = con.prepareStatement(
//                sql ); ) {
//            pst.setObject( 1, parm );
//            ResultSet rs = pst.executeQuery();
//            new ResultSetPrinter( rs ).printTable( System.out );
//        }

        String pName = "RODHAM-CLINTON HD";
        String pParty = "DEMOCRATIC";

        President hillary;
        hillary = new President( pName, 1947,
                pParty, 5 );
        String isSheIn
                = "select * from president where name=?";
        String insertQuery = "insert into president "
                + "(name,birth_year,party,state_id_born)\n"
                + "values(?,?,?,?)";

        try ( Connection con = PgJDBCDemo.createDemoConnection() ) {
            con.setAutoCommit( false );

            int affectedRows = executeUpdate( con,
                    insertQuery,
                    hillary.name,
                    hillary.birthyear,
                    hillary.party,
                    hillary.state_id_born );

            System.out.println( "affectedRows = " + affectedRows );
            try ( PreparedStatement pst = con.prepareStatement(
                    isSheIn ); ) {
                pst.setString( 1, pName );
                ResultSet rs = pst.executeQuery();
                new ResultSetPrinter( rs ).printTable( System.out );
                con.commit(); // just testing...
            }
        }
    }

    /**
     * Inserts the next president into our database.
     *
     * @param con to use
     * @param p   president to insert
     * @return the number of affected rows.
     * @throws SQLException
     */
    static int insertNextPresident( Connection con,
                                    President p ) throws SQLException {

        String template
                = "insert into president "
                + "(name,birth_year,party,state_id_born)\n"
                + "values(?,?,?,?)";
        return executeUpdate( con, template, p.name, p.birthyear, p.party,
                p.state_id_born );
    }

    static int executeUpdate( Connection con, String query, Object... params )
            throws SQLException {
        try (
                PreparedStatement stmt = con.prepareStatement( query ); ) {
            int count = 0;
            for ( Object param : params ) {
                stmt.setObject( ++count, param );
            }
            return stmt.executeUpdate();
        }
    }

    private static class President {

        final String name;
        final int birthyear;
        final String party;
        final int state_id_born;

        public President( String name, int birthyear,
                          String party, int state_id_born ) {
            this.name = name;
            this.birthyear = birthyear;
            this.party = party;
            this.state_id_born = state_id_born;
        }

    }
}
