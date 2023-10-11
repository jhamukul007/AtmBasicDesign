package com.atm.atmbasicdesign.handlers;

public interface ICurrencyOperations {
    void addCount(int count);

    ICurrencyHandler next();

    void setNext(ICurrencyHandler next);
}
