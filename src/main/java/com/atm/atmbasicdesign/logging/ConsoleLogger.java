package com.atm.atmbasicdesign.logging;

public class ConsoleLogger implements Logger {
    @Override
    public void log(Object o) {
        System.out.println("-----------------------------------");
        System.out.println(o);
        System.out.println();
    }
}
