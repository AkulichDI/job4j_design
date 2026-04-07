package ru.job4j.jdbc;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;
import static org.junit.jupiter.api.Assertions.*;

class less1Test {




    @Test
    public void shouldGetJdbcConnection() throws SQLException {
        Path path = Path.of("C:\\Users\\Kiosk\\Desktop\\job4j_design\\data\\app.properties");
        try(Connection connection = getConnection(String.valueOf(path))) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        }
    }



}