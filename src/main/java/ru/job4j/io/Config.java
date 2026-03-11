package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DataTruncation;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path){
        this.path = path;
    }


    public void load(){
        try ( BufferedReader reader = new BufferedReader(new FileReader(this.path))) {

            for (String line = reader.readLine(); line != null; line = reader.readLine()){

                if ( line == null || line.trim().isEmpty() || line.startsWith("#")) continue;
                if ( (!line.contains("=")) || line.split("=")[0].trim().isEmpty() || (line.split("=")[0].trim().isEmpty() && line.split("=")[1].trim().isEmpty())){
                    throw new IllegalArgumentException("Неверный формат файла конфигурации");
                }

                String key = line.split("=", 0)[0];
                String value = line.split("=", 0 )[1];
                values.putIfAbsent(key,value );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String value(final String key){
        return values.get(key);

    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }


    public static void main(String[] args) {

        System.out.println(new Config("data/app.properties1"));

    }

}
