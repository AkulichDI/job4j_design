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
       /* LOG.trace("Trace Message1");
        LOG.debug("DEBUG message1");
        LOG.info("INFO message1");
        LOG.warn("WARN message1");
        LOG.error("ERROR message1");


        var user = new UsageLog4j();

        user.save("Олег");
*/


        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);

        byte a = '1';
        int x = 2;
        char c = '3';
        long y = 4L;
        float u = 5F;
        double d = 6D;
        short f = 7;
        boolean tf = true;

        LOG.info("byte {} тестирование после ", a);
        LOG.info("int {}  2", x );
        LOG.error("CHAR {}  3", c);
        LOG.debug("{} LONG ", y);
        LOG.warn("{}", u);
        LOG.info("[{},{}]", a, d);
        LOG.info("{}", f);
        LOG.info("{}", tf);


    }


}

