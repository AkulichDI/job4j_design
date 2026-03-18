package ru.job4j.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {


    private static final Logger LOG  = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("Trace Message1");
        LOG.debug("DEBUG message1");
        LOG.info("INFO message1");
        LOG.warn("WARN message1");
        LOG.error("ERROR message1");
    }
}
