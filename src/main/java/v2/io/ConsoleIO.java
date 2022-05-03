package v2.io;

import v2.exceptions.IOExceptionWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleIO implements CustomIO{
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    @Override
    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new IOExceptionWrapper(e);
        }
    }

    @Override
    public void write(String str) {
        System.out.print(str);
    }
}
