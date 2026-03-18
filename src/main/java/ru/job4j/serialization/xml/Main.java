package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;

public class Main {


    public static void main(String[] args) throws JAXBException {
        final Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");


        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);

        try(StringWriter writer = new StringWriter()){
            marshaller.marshal(person, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        }catch (Exception e ){
            e.printStackTrace();
        }


    }

}
