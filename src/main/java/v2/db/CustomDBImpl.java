package v2.db;

import org.postgresql.ds.PGSimpleDataSource;
import v2.exceptions.SQLExceptionWrapper;

import java.sql.*;

public class CustomDBImpl implements CustomDB{

    private Connection psqlDBConnection;

    @Override
    public ResultSet getAuthorById(long id) {
        try {
            PreparedStatement pst = getConnection().prepareStatement("SELECT * FROM authors WHERE au_id = ?;");
            pst.setString(1, "A0" + id);
            return pst.executeQuery();
        } catch (SQLException e) {
            throw new SQLExceptionWrapper(e);
        }
    }

    @Override
    public ResultSet getBookById(long id) {
        try {
            PreparedStatement pst = getConnection().prepareStatement("SELECT * FROM titles WHERE title_id = ?;");
            pst.setString(1, "T0" + id);
            return pst.executeQuery();
        } catch (SQLException e) {
            throw new SQLExceptionWrapper(e);
        }
    }

    @Override
    public void close() {
        try {
            psqlDBConnection.close();
        } catch (SQLException e) {
            throw new SQLExceptionWrapper(e);
        }
    }

    @Override
    public Connection getConnection() {
        try {
            PGSimpleDataSource source = new PGSimpleDataSource();
            source.setDatabaseName("firstdb");
            source.setServerNames(new String[]{"localhost"});

            psqlDBConnection = source.getConnection(usrName, password);
            return psqlDBConnection;
        } catch (SQLException e) {
            throw new SQLExceptionWrapper(e);
        }
    }
}
