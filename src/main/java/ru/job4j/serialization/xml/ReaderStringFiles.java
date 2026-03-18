package ru.job4j.serialization.xml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;

public class ReaderStringFiles {


    public static void readFile(String path) {
        try (
                BufferedReader input = new BufferedReader(new FileReader(path))) {
            input.lines();
            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }


        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения XML", e);
        }
    }


}
