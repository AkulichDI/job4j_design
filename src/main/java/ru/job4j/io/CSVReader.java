package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {

        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String[] filter = argsName.get("filter").split(",");

        try (
                Scanner scanner = new Scanner(new FileInputStream(path));
                PrintStream output = "stdout".equals(out)
                        ? System.out
                        : new PrintStream(new FileOutputStream(out))
        ) {
            if (!scanner.hasNextLine()) {
                return;
            }

            String header = scanner.nextLine();
            String[] columns = header.split(Pattern.quote(delimiter));
            List<Integer> indexes = getIndexes(columns, filter);

            output.println(buildLine(columns, indexes, delimiter));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] row = line.split(Pattern.quote(delimiter));
                output.println(buildLine(row, indexes, delimiter));
            }
        }
    }

    private static List<Integer> getIndexes(String[] columns, String[] filter) {
        List<Integer> indexes = new ArrayList<>();
        for (String filterColumn : filter) {
            for (int i = 0; i < columns.length; i++) {
                if (filterColumn.equals(columns[i])) {
                    indexes.add(i);
                    break;
                }
            }
        }
        return indexes;
    }

    private static String buildLine(String[] row, List<Integer> indexes, String delimiter) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (Integer index : indexes) {
            joiner.add(row[index]);
        }
        return joiner.toString();
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}