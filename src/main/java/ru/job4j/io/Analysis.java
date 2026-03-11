package ru.job4j.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {

            boolean serverAvailable = true;

            for (String line = in.readLine(); line != null; line = in.readLine()) {

                String[] parts = line.split("\\s+");
                String code = parts[0];
                String time = parts[1];

                boolean unavailable = "400".equals(code) || "500".equals(code);

                if (serverAvailable && unavailable) {
                    serverAvailable = false;
                    out.print(time + ";");
                } else if (!serverAvailable && !unavailable) {
                    serverAvailable = true;
                    out.println(time + ";");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}