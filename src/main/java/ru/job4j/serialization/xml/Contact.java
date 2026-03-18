package ru.job4j.serialization.xml;

public class Contact {

    private final String phone;

    @Override
    public String toString() {
        return "Contact{" +
                "phone='" + phone + '\'' +
                '}';
    }

    public Contact(String phone) {
        this.phone = phone;
    }
}
