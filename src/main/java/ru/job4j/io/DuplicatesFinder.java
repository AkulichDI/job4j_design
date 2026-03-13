package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {

    public static void main(String[] args) {

        DuplicatesVisitor x = new DuplicatesVisitor();

        try {


            Files.walkFileTree(Path.of("C:\\Users\\Kiosk\\Desktop"), x);



        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(x.getArrayDuplicate());


    }


}
