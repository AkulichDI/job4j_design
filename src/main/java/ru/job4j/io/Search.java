package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {



    private static void validateArgsConfiguration(String[] args){

        if ( args.length != 2){
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }


    }


    public static List<Path> searchP(Path root, Predicate<Path> condition) throws IOException {

        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }


    private static void search (String [] args ) throws IOException {

        searchP(Path.of(args[0]), path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);

    }


    public static void main(String[] args) throws IOException {

        validateArgsConfiguration(args);

        search(args);



    }

}
