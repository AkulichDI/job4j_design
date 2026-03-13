package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            validate(arg);
            String[] pair = arg.split("=", 2);
            String key = pair[0].substring(1);
            String value = pair[1];
            values.put(key, value);
        }
    }

    private void validate(String arg) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not start with a '-' character");
        }

        if (!arg.contains("=")) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain an equal sign");
        }

        String[] pair = arg.split("=", 2);
        String keyPart = pair[0];
        String valuePart = pair[1];

        if (keyPart.length() == 1) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a key");
        }

        if (valuePart.isEmpty()) {
            throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a value");
        }

    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}