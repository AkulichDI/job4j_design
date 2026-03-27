package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

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




    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("org.postgresql.Driver");

        Path path = Path.of("C:\\Users\\Kiosk\\Desktop\\job4j_design\\data\\app.properties");

        Map<String, String> properties = getProperties(path);

        System.out.println(properties);


        try (Connection connection = DriverManager.getConnection(properties.get("url"), properties.get("user"),properties.get("password")) ){

            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
            System.out.println(metaData.getDriverName());


        }catch (Exception e ){
            System.out.printf("Ошибка в подключении: %s",e);
        }

    }



}
