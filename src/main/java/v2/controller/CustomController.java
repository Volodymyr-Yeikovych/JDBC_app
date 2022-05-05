package v2.controller;

import v2.db.CustomDB;
import v2.enums.TableName;
import v2.io.CustomIO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Optional;

public class CustomController {
    private CustomIO io;
    private CustomDB db;

    public CustomController(CustomIO io, CustomDB db) {
        this.io = io;
        this.db = db;
    }

    public void run () {
        try {
            io.write("Determine which entity to select author/title (type A/T): ");
            TableName entity = TableName.parse(io.readLine().toUpperCase());
            io.writeln();
            while (!TableName.TITLES.equals(entity) && !TableName.AUTHORS.equals(entity)) {
                io.write("Invalid parameter. Select author (A) or title (T): ");
                entity = TableName.parse(io.readLine().toUpperCase());
                io.writeln();
            }

            io.write("Write author/title id (e. g. 1): ");
            int id = Integer.parseInt(io.readLine());
            io.writeln();

            ResultSet selectedTable = get(entity, id).orElseThrow(() -> new RuntimeException("Such table not exists."));

            printResult(selectedTable);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

    }

    private void printResult(ResultSet table) throws SQLException {
        ResultSetMetaData auOrTitMetaData = table.getMetaData();
        if (table.next()) table.next();
        else {
            io.writeln("That id does not exist.");
            return;
        }

        int columnSize = auOrTitMetaData.getColumnCount();
        for (int i = 1; i <= columnSize; i++) {
            io.write(auOrTitMetaData.getColumnName(i) + "\t | ");
        }
        io.writeln();
        for (int i = 1; i <= columnSize; i++) {
            io.write(table.getString(i) + "\t | ");
        }

    }

    @SuppressWarnings("all")
    private Optional<ResultSet> get(TableName name, long id) {
        return
                switch (name) {
                    case TITLES -> Optional.ofNullable(db.getBookById(id));
                    case AUTHORS -> Optional.ofNullable(db.getAuthorById(id));
                    default -> Optional.empty();
                };
    }

    public void close() {
        io.close();
        db.close();
    }
}
