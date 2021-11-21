package pgjdbcdemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exexute a sequence of sql statements with parameters in a fluent programming style.
 */
class TransactionBuilder implements AutoCloseable {

    private final Connection con;
    private String queryText = "";
    private final List<Object[]> params = new ArrayList<>();
    private SQLException trouble = null;
    private int affectedRows = 0;

    public int getAffectedRows() {
        return affectedRows;
    }

    public TransactionBuilder(Connection con) throws SQLException {
        this.con = con;
        con.setAutoCommit(false);
    }

    TransactionBuilder withQuery(String text) {
        queryText = text;
        return this;
    }

    TransactionBuilder andParams(Object... args) {
        this.params.add(args);
        return this;
    }

    TransactionBuilder execute() throws SQLException {
        try (final PreparedStatement stmt = con.prepareStatement(queryText)) {
            for (Object[] pa : params) {
                int count = 0;
                for (Object param : pa) {
                    stmt.setObject(++count, param);
                }
                affectedRows += stmt.executeUpdate();
            }
        } catch (SQLException sqe) {
            trouble = sqe;
            throw sqe;
        } finally {
            params.clear();
        }
        return this;
    }

    void rollback() throws SQLException {
        con.rollback();
        LOG.log(Level.INFO, "transaction rolled back");
    }
    private static final Logger LOG = Logger.getLogger(TransactionBuilder.class.getName());

    @Override
    public void close() throws SQLException {
        if (trouble != null) {
            con.rollback();
            con.close();
        } else {
            con.commit();
            con.close();
        }
    }
    
}
