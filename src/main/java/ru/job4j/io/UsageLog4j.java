package ru.job4j.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {


    private static final Logger LOG  = LoggerFactory.getLogger(UsageLog4j.class.getName());


    public void save(String name) {
        LOG.info("Start saving user: {}", name);
        try {
            // логика сохранения
            LOG.info("User saved successfully: {}", name);
        } catch (Exception e) {
            LOG.error("Error while saving user: {}", name, e);
        }
    }


    public static void main(String[] args) {
        LOG.trace("Trace Message1");
        LOG.debug("DEBUG message1");
        LOG.info("INFO message1");
        LOG.warn("WARN message1");
        LOG.error("ERROR message1");


        var user = new UsageLog4j();

        user.save("Олег");


    }


}

