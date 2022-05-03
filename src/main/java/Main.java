import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost/firstdb";
        String usrName = "postgres";
        String password = "admin4";

        Connection psqlDBConnection = null;

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver connected.");

            psqlDBConnection = DriverManager.getConnection(url, usrName, password);
            System.out.println("DB connection established");

            Statement statement = psqlDBConnection.createStatement();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Determine which entity to select author/title (type A/T): ");
            String entity = reader.readLine().toUpperCase();
            System.out.println();
            while (!"T".equals(entity) && !"A".equals(entity)) {
                System.out.print("Invalid parameter. Select author (A) or title (T): ");
                entity = reader.readLine();
                System.out.println();
            }

            String table = "T".equals(entity) ? "titles" : "authors";
            String validID = "T".equals(entity) ? "title_id" : "au_id";
            ResultSet selectedTable = statement.executeQuery("SELECT * FROM " + table + " ORDER BY " + validID + ";");
            int maxLength = 0;
            while (selectedTable.next()) {
                if (selectedTable.isLast()) maxLength = selectedTable.getRow();
            }

            System.out.print("Write author/title id (e. g. 1) max is(" + maxLength + "): ");
            int id = Integer.parseInt(reader.readLine());
            System.out.println();
            while (id > maxLength && id < 1) {
                System.out.println("Id is invalid. Either it is more than max or is invalid.");
                System.out.print("Write correct id: ");
                id = Integer.parseInt(reader.readLine());
                System.out.println();
            }

            ResultSet selectedAuOrTit = statement.executeQuery(
                    "SELECT * FROM " + table + " WHERE " + validID + " = '" + entity + "0" + id + "'");
            ResultSetMetaData auOrTitMetaData = selectedAuOrTit.getMetaData();

            while (selectedAuOrTit.next()) {
                int columnSize = auOrTitMetaData.getColumnCount();
                for (int i = 1; i <= columnSize; i++) {
                    System.out.print(auOrTitMetaData.getColumnName(i) + "\t | ");
                }
                System.out.println();
                for (int i = 1; i <= columnSize; i++) {
                    System.out.print(selectedAuOrTit.getString(i) + "\t | ");
                }
            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            if (psqlDBConnection != null) {
                try {
                    psqlDBConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
