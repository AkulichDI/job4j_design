package ru.job4j.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class CSVReaderArgsValidator {

    private static final String STDOUT = "stdout";
    private static final Set<String> REQUIRED_KEYS = Set.of("path", "delimiter", "out", "filter");

    public void validate(ArgsName argsName) {
        checkRequiredKeys(argsName);
        validatePath(argsName.get("path"));
        validateDelimiter(argsName.get("delimiter"));
        validateOut(argsName.get("out"));
        validateFilter(argsName.get("filter"));
    }

    private void checkRequiredKeys(ArgsName argsName) {
        for (String key : REQUIRED_KEYS) {
            try {
                argsName.get(key);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Отсутствует обязательный параметр: " + key);
            }
        }
    }

    private void validatePath(String path) {
        if (path == null || path.isBlank()) {
            throw new IllegalArgumentException("Путь к CSV-файлу не может быть пустым");
        }
        Path file = Path.of(path);
        if (!Files.exists(file)) {
            throw new IllegalArgumentException("Файл не существует: " + path);
        }
        if (Files.isDirectory(file)) {
            throw new IllegalArgumentException("Ожидался файл, а не директория: " + path);
        }
    }

    private void validateDelimiter(String delimiter) {
        if (delimiter == null || delimiter.isBlank()) {
            throw new IllegalArgumentException("Разделитель не может быть пустым");
        }
        if (delimiter.length() != 1) {
            throw new IllegalArgumentException("Разделитель должен состоять из одного символа");
        }
    }

    private void validateOut(String out) {
        if (out == null || out.isBlank()) {
            throw new IllegalArgumentException("Параметр out не может быть пустым");
        }
        if (!STDOUT.equals(out)) {
            Path target = Path.of(out);
            Path parent = target.getParent();
            if (parent != null && !Files.exists(parent)) {
                throw new IllegalArgumentException("Директория для выходного файла не существует: " + parent);
            }
        }
    }

    private void validateFilter(String filter) {
        if (filter == null || filter.isBlank()) {
            throw new IllegalArgumentException("Фильтр не может быть пустым");
        }
        String[] columns = filter.split(",");
        for (String column : columns) {
            if (column.isBlank()) {
                throw new IllegalArgumentException("Фильтр содержит пустое имя столбца");
            }
        }
    }



}
