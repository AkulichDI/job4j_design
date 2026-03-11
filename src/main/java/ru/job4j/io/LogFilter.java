package ru.job4j.io;

import javax.swing.text.Style;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {

    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter(){

        List<String> result = new ArrayList<>();

        try  (BufferedReader in = new BufferedReader(new FileReader(this.file))){

           // return in.lines().filter(x -> x.contains(" 404 ")).toList();

            return in.lines().filter(x -> {
                String [] parts = x.trim().split(" ");
                return parts.length >= 2 && "404".equals(parts[parts.length - 2 ]);
            }).toList();


        }catch (IOException e ) {
            e.printStackTrace();
        }

        return List.of();
    }


    private static void init(){
        String start =  "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:18 +0300] \"GET /items/ajax.html HTTP/1.1\" 404 1113\n" +
                        "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:23 +0300] \"GET /job4j.ru/profile HTTP/1.1\" 302 -\n" +
                        "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:23 +0300] \"GET /job4j.ru/profile/ HTTP/1.1\" 404 1110\n" +
                        "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:34 +0300] \"GET /job4j.ru/profileNew/ HTTP/1.1\" 404 -\n" +
                        "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:48 +0300] \"GET / HTTP/1.1\" 200 11488\n" +
                        "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:49 +0300] \"GET /404.png HTTP/1.1\" 200 5103\n" +
                        "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:49 +0300] \"GET /tomcat.css HTTP/1.1\" 200 5931\n" +
                        "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:49 +0300] \"GET /bg-nav.png HTTP/1.1\" 200 1404\n" +
                        "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:49 +0300] \"GET /bg-button.png HTTP/1.1\" 200 713\n" +
                        "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:49 +0300] \"GET /bg-middle.png HTTP/1.1\" 200 4048";
        try (BufferedWriter out = new BufferedWriter(new FileWriter("data/log.txt", true))){

            for (String line :start.split(System.lineSeparator())) {
                out.write(line);
            }

        }catch ( IOException e ){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String  patch = "data/";
        String  fileName ="log.txt";
        if ( !new File(patch+fileName).exists()){
            System.out.println("Файл с логами отсутствует" + System.lineSeparator() + "Создание файла");
            init();
            System.out.println("Файл создан: " + patch + fileName);

        }else {
            System.out.println("Файл с логами существует");
        }

        LogFilter logFilter = new LogFilter("data/log.txt");

        logFilter.filter().forEach(System.out::println);

    }



}
