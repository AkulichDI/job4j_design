package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        String path = "./data/per1.xml";

        PersonXmlSerializer.serialize(person, path);

        ReaderStringFiles.readFile(path);

        Person per1 = PersonXmlSerializer.deserialize(path);
        per1.setAge(42);

        System.out.println(per1);

        PersonXmlSerializer.serialize(per1,path);



    }
}