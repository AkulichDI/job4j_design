package ru.job4j.serialization.xml;

import com.sun.xml.txw2.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Contact")
public class Contact {

    @XmlAttribute
    private String phone;

    @Override
    public String toString() {
        return "Contact{" +
                "phone='" + phone + '\'' +
                '}';
    }

    public Contact(){};

    public Contact(String phone) {
        this.phone = phone;
    }
}
