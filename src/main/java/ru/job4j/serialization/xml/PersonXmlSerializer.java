package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class PersonXmlSerializer {


    public static void serialize(Person person, String path){
        try{
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.marshal(person, new File(path));
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public static Person deserialize(String path) {
        try {
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Person) unmarshaller.unmarshal(new File(path));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка десериализации из XML", e);
        }
    }


}
