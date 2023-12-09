import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DB {

    static String url = "jdbc:postgresql:fridgeList"; // change name to name of DB
    static String user = "root"; // change to your username
    static String password = "root"; // change to your password

    @SuppressWarnings("all")
    public static int insertData(String name, int day, String month, int year) {
        int generatedId = -1; // Default value in case of failure

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (Statement statement = connection.createStatement()) {
                String sql = "INSERT INTO items (name, day, month, year) VALUES ";
                sql += "('" + name + "', " + day + ", '" + month + "', " + year + ") RETURNING id;";
                System.out.println(sql);
                // Execute the SQL statement and retrieve the generated ID
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    if (resultSet.next()) {
                        generatedId = resultSet.getInt("id");
                        System.out.println("Insert data successful. Generated ID: " + generatedId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    @SuppressWarnings("all")
    public static List<Material> readData() {
        List<Material> materialsList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (Statement statement = connection.createStatement()) {
                String sql = "SELECT * FROM items;";

                try (ResultSet result = statement.executeQuery(sql)) {
                    while (result.next()) { // fetch data from db
                        int id = result.getInt("id");
                        String name = result.getString("name");
                        int day = result.getInt("day");
                        String month = result.getString("month");
                        int year = result.getInt("year");
                        Material material = new Material(id, name, day, month, year);
                        materialsList.add(material);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materialsList;
    }

    @SuppressWarnings("all")
    public static void deleteAllData() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (Statement statement = connection.createStatement()) {
                String sql = "DELETE FROM items;";
                int rowsAffected = statement.executeUpdate(sql);
                // reset auto increment
                sql = "ALTER SEQUENCE items_id_seq RESTART WITH 1;";
                statement.executeUpdate(sql);

                System.out.println("Deleted " + rowsAffected + " rows from the table.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("all")
    public static void updateData(int id, String name, int day, String month, int year) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (Statement statement = connection.createStatement()) {
                String sql = "UPDATE items SET name='" + name + "', day=" + day + ", month='" + month + "', year="
                        + year
                        + " WHERE id=" + id + ";";
                System.out.println(sql);
                int rowsAffected = statement.executeUpdate(sql);
                if (rowsAffected > 0) {
                    System.out.println("Update data " + rowsAffected + " rows.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("all")
    public static void deleteData(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (Statement statement = connection.createStatement()) {
                String sql = "DELETE FROM items" + " WHERE id=" + id + ";";
                int rowsAffected = statement.executeUpdate(sql);
                if (rowsAffected > 0) {
                    System.out.println("Delete data " + rowsAffected + " row(s).");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
