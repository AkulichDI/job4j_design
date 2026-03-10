package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadFile {

    private static void init (){

        try (FileOutputStream out = new FileOutputStream("data/input.txt")){

            out.write("login=login".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("password=password".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("url=url".getBytes());
            out.write(System.lineSeparator().getBytes());

            System.out.println("\nФайл создан\n");

        }catch ( IOException e ){
            e.printStackTrace();
        }

    }


    private static String readFile(String path, String fileName) {
        StringBuilder sb = new StringBuilder();

        try (FileInputStream input = new FileInputStream(path+fileName)){
            int read;
            while ( (read = input.read()) != -1 ){
                sb.append((char)read);
            }
        } catch (IOException e ){
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static void printData(String data){
        String[] lines = data.split(System.lineSeparator());
        int counter = 0;
        for ( String line : lines ){
            System.out.println("Строка №" + ++counter + ": " + line);
        }

    }


    public static void main(String[] args) {

        String patch = "data/";
        String fileName = "input.txt";

            if ( !new File(patch + fileName).exists()) init();
            else System.out.println("\nФайл: " + patch + fileName + " уже существует\n");


        printData(readFile(patch, fileName));


    }




}
