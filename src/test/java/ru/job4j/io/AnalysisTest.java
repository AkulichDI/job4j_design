package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnalysisTest {


    @Test
    void filterLog(@TempDir Path tempDir) throws IOException{

        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output  = new PrintWriter(source)){

            output.print("200 10:56:01\n" +
                    "500 10:57:01\n" +
                    "400 10:58:01\n" +
                    "500 10:59:01\n" +
                    "400 11:01:02\n" +
                    "300 11:02:02");

        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(),target.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        try ( BufferedReader input = new BufferedReader(new FileReader(target))){
            input.lines().forEach(sb::append);
        }
        assertThat("10:57:01;11:02:02;").hasToString(sb.toString());


    }





}