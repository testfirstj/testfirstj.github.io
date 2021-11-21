package pgjdbcdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hom
 */
public class PgJDBCDemo {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws
            ClassNotFoundException, IOException {
        String sql = "SELECT * FROM president ";
        
        System.out.println( "using create connection " );
        try (Connection con = createDemoConnection()) {
            ResultSetPrinter.getDBProperties(con);
            doQuery(con, sql, out);

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        
        System.out.println("========== alternative usage ==");

        try (Connection con = new ConnectionFactory()
                .getConnection()) {
            ResultSetPrinter.getDBProperties(con);
            doQuery(con, sql, out);

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

    }

    static void doQuery(Connection con, String query,
            PrintStream out) throws SQLException {
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {
            ResultSetMetaData meta = rs.getMetaData();

            new ResultSetPrinter(rs).printTable(out);
        }
    }

    private final static Logger LOGGER
            = Logger.getLogger(PgJDBCDemo.class.getName());

    static Connection createDemoConnection() throws
            SQLException {
        String url = "jdbc:postgresql://localhost/presidentDB";

        Properties props = new Properties();
        props.setProperty("user", "exam");
        props.setProperty("password", "exam");
        Connection conn = DriverManager
                .getConnection(url, props);
        return conn;
    }

}
