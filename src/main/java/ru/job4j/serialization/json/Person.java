package ru.job4j.serialization.json;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final boolean sex;
    private final int age;
    private final Contact contact;
    private final String[] statuses;
    private final Phone phone;

    public Person(boolean sex, int age, Contact contact, String[] statuses, Phone phone) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "sex=" + sex +
                ", age=" + age +
                ", contact=" + contact +
                ", statuses=" + Arrays.toString(statuses) +
                ", phone=" + phone +
                '}';
    }
}
