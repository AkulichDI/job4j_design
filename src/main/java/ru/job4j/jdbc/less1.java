package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class less1 {

    private static Map<String, String> getProperties(Path path) {
        Map<String, String> result = new HashMap<>();


        try (BufferedReader input = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = input.readLine()) != null) {
                if (line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    String key = parts[0].trim();
                    String value = parts[1].trim();

                    if (!key.isEmpty() && !value.isEmpty()) {
                        result.putIfAbsent(key, value);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка в чтении файла: " + e);
        }
        return result;
    }


    private static Connection getConnection(Path path) throws Exception {
        Class.forName("org.postgresql.Driver");

        Map<String, String> properties = getProperties(path);

        System.out.println(properties);

        return   DriverManager.getConnection(properties.get("url"), properties.get("user"),properties.get("password"));
    }


    public static String getTableScheme(Connection connection, String tableName) throws SQLException {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try ( var statement = connection.createStatement()){
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));

            var metaData = selection.getMetaData();

            for ( int i = 1; i <= metaData.getColumnCount(); i++){

                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i)));

            }

        }

        return buffer.toString();
    }








    public static void main(String[] args) throws ClassNotFoundException {

            Path path = Path.of("C:\\Users\\Kiosk\\Desktop\\job4j_design\\data\\app.properties");

            try (Connection connection = getConnection(path);
                 Statement statement = connection.createStatement()) {

                String sql = String.format(
                        "CREATE TABLE IF NOT EXISTS demo_table(%s, %s);",
                        "id SERIAL PRIMARY KEY",
                        "name TEXT"
                );

                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));

            } catch (Exception e) {
                e.printStackTrace();
            }


    }



}
