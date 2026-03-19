package ru.job4j.serialization.xml;

import com.google.gson.JsonArray;
import com.google.gson.internal.bind.util.ISO8601Utils;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
       /* Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        String path = "./data/per1.xml";

        PersonXmlSerializer.serialize(person, path);

        ReaderStringFiles.readFile(path);

        Person per1 = PersonXmlSerializer.deserialize(path);
        per1.setAge(42);

        System.out.println(per1);

        PersonXmlSerializer.serialize(per1,path);
*/
        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");
        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.isSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        // Результат в консоль

        System.out.println(jsonObject.toString());
        // Преобразуем объект person в json - строку
        System.out.println(new JSONObject(person).toString());




    }
}
