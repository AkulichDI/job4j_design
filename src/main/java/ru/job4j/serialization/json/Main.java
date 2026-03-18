package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        final Person person = new Person(false,30, new Contact("11-111"),
                new String[] {"Worker", "Married"}, new Phone("Yota",false,20000, new ArrayList<>(List.of("CPU", "GPU")),new CPU("snap888", 2000)));
        // Преобразуем объект person в json строку

        final Gson gson =  new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        // Создаем новую json строку c мод данными
        final String personJson =  "{"
                                        + "\"sex\":false,"
                                        + "\"age\":35,"
                                        + "\"contact\":"
                                        + "{"
                                        + "\"phone\":\"+7(924)111-111-11-11\""
                                        + "},"
                                        + "\"statuses\":"
                                        + "[\"Student\",\"Free\"]"
                                        + "}";

        System.out.println("1. до toJson");
        System.out.println(gson.toJson(person));

        System.out.println("2. до fromJson");
        final Person personMod = gson.fromJson(personJson, Person.class);

        System.out.println("3. после fromJson");
        System.out.println(personMod);

    }


}
