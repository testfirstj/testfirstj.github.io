package pgjdbcdemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Transaction demo with multiple statements.
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class TransactionDemo {

    void demo() throws SQLException {

        try ( Connection con = PgJDBCDemo.createDemoConnection();
                TransactionBuilder tran = new TransactionBuilder( con ) ) {
            tran
                    .withQuery(
                            "UPDATE president SET years_served = ? WHERE id = ?" )
                    .andParams( 8, 43 ) // obama
                    .execute()
                    .withQuery( "INSERT INTO election VALUES (?,?,?,?)" )
                    .andParams( 2016, "TRUMP D J", 304, "W" )
                    .andParams( 2016, "CLINTON H D R", 227, "L" )
                    .execute()
                    .withQuery(
                            "INSERT INTO president "
                            + "(id, name, birth_year, years_served, party, state_id_born)"
                            + "VALUES (?,?,?,?,?,?)" )
                    .andParams( 44, "TRUMP D J", 1946, 0, "REPUBLICAN", 46 )
                    .execute()
                    .withQuery( "INSERT INTO administration "
                            + "(id,admin_nr,pres_id,year_inaugurated)"
                            + " VALUES(?,?,?,?)" )
                    .andParams( 67, 58, 44, 2017 )
                    .execute()
                    .withQuery( "INSERT INTO admin_vpres (admin_id,vice_pres_name) VALUES(?,?)" )
                    .andParams( 67, "PENCE M R" )
                    .execute()
                    .withQuery( "INSERT INTO pres_hobby (pres_id,hobby) VALUES(?,?)" )
                    .andParams( 44, "GOLF" )
                    .execute()
                    .withQuery( "INSERT INTO pres_marriage"
                            + " (pres_id,spouse_name,spouse_age,nr_children,marriage_year)"
                            + "VALUES(?,?,?,?,?)" )
                    .andParams( 44, "ZELNICKOVA I", 28, 3, 1977 )
                    .andParams( 44, "MAPLES M", 30, 1, 1993 )
                    .andParams( 44, "KNAUSS M", 34, 1, 2005 )
                   // .andParams( 44, "DANIELS STORMY", 39, 0, 2018 )
                    .execute();
            tran.rollback();
            int affectedRows = tran.getAffectedRows();
            System.out.println( "affectedRows = " + affectedRows );
        }
    }

    public static void main( String[] args ) throws SQLException {

        new TransactionDemo().demo();

    }
}
