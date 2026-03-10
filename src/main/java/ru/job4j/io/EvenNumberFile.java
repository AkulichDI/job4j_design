package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

import static java.nio.file.Files.readAllLines;

public class EvenNumberFile {


    private static String printEvent ( String path){
        StringBuilder sb = new StringBuilder();
        try (FileInputStream input = new FileInputStream(path)){
            int read;
            while ( (read = input.read() ) != -1 ){
                sb.append((char) read);
            }


        } catch ( IOException e ){
            e.printStackTrace();
        }

        return sb.toString();
    }

    private static void validator ( String data ){
        String[] lines = data.split(System.lineSeparator());

        for ( String line : lines ){
            if (line == null || line.trim().isEmpty())continue;
            int x = Integer.parseInt(line);

            System.out.println( x % 2 == 0 ? "Число: " + line + " - четное" : "Число: " + line + " - нечетное");
        }

    }


    public static void main(String[] args) {

        String path = "data/";
        String fileName ="event.txt";

        String data = printEvent(path + fileName);
        validator(data);


    }
}
