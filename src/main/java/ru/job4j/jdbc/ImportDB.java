package ru.job4j.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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


    public static void main(String[] args) throws IOException, SQLException {


        Properties config = new Properties();
        try(InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")){

            config.load(input);

        }
        ImportDB dataBase = new ImportDB(config,"src/main/resources/dump.txt");
        dataBase.save(dataBase.load());





    }




}
