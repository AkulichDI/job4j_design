package ru.job4j.io;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UsageLog4j {


    private static final Logger LOG  = LogManager.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("Trace Message1");
        LOG.debug("DEBUG message1");
        LOG.info("INFO message1");
        LOG.warn("WARN message1");
        LOG.error("ERROR message1");
    }
}
