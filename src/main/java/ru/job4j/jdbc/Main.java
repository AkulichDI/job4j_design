package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {

        Properties config = new Properties();
        try (InputStream in = Main.class.getClassLoader().getResourceAsStream("app.properties")) {

            config.load(in);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(config);

        TableEditor tableEditor = new TableEditor(config);
        System.out.println(tableEditor.getTableScheme("cars"));

        tableEditor.createTable("sys");

        System.out.println(tableEditor.getTableScheme("sys"));
        tableEditor.addColumn("sys", "first","text");

        System.out.println(tableEditor.getTableScheme("sys"));














    }


}
