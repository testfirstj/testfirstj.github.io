package pgjdbcdemo;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import org.postgresql.ds.PGConnectionPoolDataSource;

/**
 *
 * @author hom
 */
public class DataSourceDemo {

  public static void main( String[] args ) throws
          SQLException {
    ConnectionPoolDataSource source = createPGDataSource();
    String query
            = "select s.name as state,p.name as president,s.year_entered \n"
            + " from president p join state s on(p.state_id_born=s.id)\n"
            + "where s.name like 'O%'";
    try ( Connection con = source
            .getPooledConnection().getConnection(); ) {
      PgJDBCDemo.doQuery( con, query, System.out );
    }

  }

  static ConnectionPoolDataSource createPGDataSource() {
    PGConnectionPoolDataSource source
            = new PGConnectionPoolDataSource();
    System.out.println( "using pooling data source" );
    source.setServerName( "localhost" );
    source.setDatabaseName( "presidents" );
    source.setUser( "exam" );
    source.setPassword( "exam" );
    return source;
  }

  static AutoCloseable autoCloseWrapper(
          final PooledConnection c ) {
    return new AutoCloseable() {
      boolean closed = false;

      @Override
      public void close() throws Exception {
        if ( !closed ) {
          c.close();
        }
      }
    };
  }
}
