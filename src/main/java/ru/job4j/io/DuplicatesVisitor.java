package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.DataTruncation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> list = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        FileProperty x = new FileProperty( file.toFile().length(), file.toFile().getName());

        list.computeIfAbsent(x, k ->  new ArrayList<>()).add(file);

        return super.visitFile(file, attrs);
    }


    public List<Path> getArrayDuplicate(){
       return list.entrySet().stream().filter(k -> k.getValue().size() > 1 ).flatMap(x -> x.getValue().stream()).toList();
    }
}
