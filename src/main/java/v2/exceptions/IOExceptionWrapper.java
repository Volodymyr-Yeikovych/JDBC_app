package v2.exceptions;

import java.io.IOException;

public class IOExceptionWrapper extends RuntimeException{
    public IOExceptionWrapper(IOException cause) {
        super(cause);
    }
}
