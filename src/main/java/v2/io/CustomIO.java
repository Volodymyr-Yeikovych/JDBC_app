package v2.io;

public interface CustomIO {

    String readLine();
    void write(String str);
    default void writeln() {
        writeln( "");
    }
    default void writeln (String str) {
        write(str + "\n");
    }
}
