package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

/**
 *         - createTable() – создает пустую таблицу без столбцов с указанным именем;
 *
 *         - dropTable() – удаляет таблицу по указанному имени;
 *
 *         - addColumn() – добавляет столбец в таблицу;
 *
 *         - dropColumn() – удаляет столбец из таблицы;
 *
 *         - renameColumn() – переименовывает столбец.
 */



public class TableEditor implements AutoCloseable{

   private Connection connection;

   private Properties properties;

    public TableEditor( Properties properties) {
        this.properties = properties;

        try {
            initConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void initConnection() throws SQLException {

            connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));



    }

    public void createTable(String tableName){

        try ( Statement statement = connection.createStatement()){
            String sql =  String.format(
                    "CREATE TABLE IF NOT EXISTS %s();",
                    tableName);

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void dropTable(String tableName){

        try ( Statement statement = connection.createStatement()){

            String sql = String.format(
                    "DROP TABLE IF EXISTS %S",
                    tableName
            );

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void addColumn(String tableName, String columnName, String type) {

        String sql = String.format("ALTER TABLE %S ADD COLUMN %S %S",
                tableName,columnName,type
        );

        try(Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void dropColumn(String tableName, String columnName) {

        String sql = String.format("ALTER TABLE %S DROP COLUMN %S", tableName,columnName);

        try( Statement statement = connection.createStatement() ){

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {

        String sql = String.format("ALTER TABLE %S RENAME COLUMN %S TO %S", tableName,columnName,newColumnName);

        try ( Statement statement = connection.createStatement() ){

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                                metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }






    @Override
    public void close() throws Exception {
        if (connection != null){
            connection.close();
        }
    }











}
