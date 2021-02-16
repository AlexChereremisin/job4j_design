package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Alexey Cheremisin";
        int age = 33;
        float weight = 82.3f;
        short height = 175;
        char gender= 'm';
        long socialSecurityNumber = 12345678912345678L;
        double bitcoinQuantity = 0.0000555;
        byte children = 1;
        boolean inMarriage = true;
        LOG.debug(
                "User info name : {}, age : {}, weight : {}, height : {}, gender : {}, social security number : {}, bitcoin quantity : {}, children : {}, marriage : {}",
                name,
                age,
                weight,
                height,
                gender,
                socialSecurityNumber,
                bitcoinQuantity,
                children,
                inMarriage
                );
    }
}
