package v2.db;

import java.sql.Connection;
import java.sql.ResultSet;

public interface CustomDB {
    String url = "jdbc:postgresql://localhost/firstdb";
    String usrName = "postgres";
    String password = "admin4";

    Connection getConnection();

    ResultSet getAuthorById(long id);

    ResultSet getBookById(long id);

    void close ();
}
