package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenFileHasCommentsAndEmptyLines() {
        String path = "./data/with_comments_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("country")).isEqualTo("Russia");
    }

    @Test
    void whenLineStartsWithEqualsThenThrowException() {
        String path = "./data/invalid_starts_with_equals.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenLineHasEmptyValueThenThrowException() {
        String path = "./data/invalid_empty_value.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenLineHasNoEqualsThenThrowException() {
        String path = "./data/invalid_without_equals.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenValueContainsEqualsThenParseCorrectly() {
        String path = "./data/value_with_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key")).isEqualTo("value=1");
    }

    @Test
    void whenValueEndsWithEqualsThenParseCorrectly() {
        String path = "./data/value_ends_with_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key")).isEqualTo("value=");
    }






}