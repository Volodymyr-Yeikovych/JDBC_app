package v2;

import v2.controller.CustomController;
import v2.db.CustomDBImpl;
import v2.io.ConsoleIO;

public class Main {

    public static void main(String[] args) {
        new CustomController(new ConsoleIO(), new CustomDBImpl()).run();
    }
}
