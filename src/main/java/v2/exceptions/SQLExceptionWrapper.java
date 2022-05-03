package v2.exceptions;

import java.sql.SQLException;

public class SQLExceptionWrapper extends RuntimeException{
    public SQLExceptionWrapper(SQLException cause) {
        super(cause);
    }
}
