package ru.job4j.serialization;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

public class Contact implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "zipCode=" + zipCode +
                ", phone='" + phone + '\'' +
                '}';
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }


    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Contact contact = (Contact) object;
        return zipCode == contact.zipCode && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, phone);
    }

    public static void main(String[] args) throws IOException {
        final  Contact contact = new Contact(123456, "+7 (111) 111-11-11");

        // Запись объекта во временный файл, который удалился системой

        File tempFile = Files.createTempFile(null, null).toFile();

        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(contact);
        }

        // Чтение объекта из файла

        try ( FileInputStream fis = new FileInputStream(tempFile);
        ObjectInputStream ois = new ObjectInputStream(fis)){
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
            System.out.printf("Десериализованный объект равен текущему: %b ",contactFromFile.equals(contact));


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



}
