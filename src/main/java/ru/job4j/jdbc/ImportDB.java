package ru.job4j.jdbc;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Properties;

public class ImportDB {

    private Properties config;
    private String dump;

    public ImportDB(Properties config, String dump){
        this.config = config;
        this.dump   = dump;
    }


    public List<User> load(){

        List<User> users = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(dump))){
            //TODO реализовать логику чтения данных
            reader.lines();
            String line;
            while (  (line = reader.readLine()) != null){

                String first = line.split(";")[0];
                String second = line.split(";")[1];

                if ( first.isBlank() && second.isBlank() ){

                    throw new IllegalArgumentException();

                }

                users.add(new User(first,second));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public void save(List<User> users) throws SQLException {

        try(Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )){
            for (User user : users ){

                try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, email) VALUES ( ?, ? )")){
                    statement.setString(1,user.getName());
                    statement.setString(2,user.getEmail());
                    statement.execute();
                }catch (Exception e ){
                    e.printStackTrace();
                }


            }
        }

    }


    public void initTableUsers() throws SQLException {

        try ( Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )){

            try( Statement statement = connection.createStatement()){

                String sql = "CREATE TABLE IF NOT EXISTS users (" +
                        "id SERIAL PRIMARY KEY," +
                        "name TEXT," +
                        "email TEXT" +
                        ");";
                statement.execute(sql);
            }catch (Exception e ){
                e.printStackTrace();
            }


        }


    }


    public void printUsers() throws SQLException {
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        );
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("SELECT id, name, email FROM users")) {

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");

                System.out.println(id + " | " + name + " | " + email);
            }
        }
    }






    public static void main(String[] args) throws IOException, SQLException {


        Properties config = new Properties();
        try(InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")){

            config.load(input);

        }
        ImportDB dataBase = new ImportDB(config,"src/main/resources/dump.txt");
        dataBase.initTableUsers();
        dataBase.save(dataBase.load());
        dataBase.printUsers();




    }




}
