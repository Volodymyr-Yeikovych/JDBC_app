package v2.db;

import v2.exceptions.SQLExceptionWrapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

//@Slf4j
public class CustomDBImpl implements CustomDB{

    @Override
    public ResultSet getAuthorById(long id) {
        try {
            return getConnection().createStatement().executeQuery(
                    "SELECT * FROM authors WHERE au_id = 'A0" + id + "';");
        } catch (SQLException e) {
            throw new SQLExceptionWrapper(e);
        }
    }

    @Override
    public ResultSet getBookById(long id) {
        try {
            return getConnection().createStatement().executeQuery(
                    "SELECT * FROM titles WHERE title_id = 'T0" + id + "';");
        } catch (SQLException e) {
            throw new SQLExceptionWrapper(e);
        }
    }

    @Override
    public void close() {

    }

    @Override
    public Connection getConnection() {
        try {
            Connection psqlDBConnection = DriverManager.getConnection(url, usrName, password);
//            logger.info
            return DriverManager.getConnection(url, usrName, password);
        } catch (SQLException e) {
            throw new SQLExceptionWrapper(e);
        }
    }
}
