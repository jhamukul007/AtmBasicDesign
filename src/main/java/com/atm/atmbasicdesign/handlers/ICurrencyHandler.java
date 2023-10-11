package com.atm.atmbasicdesign.handlers;

public interface ICurrencyHandler extends ICurrencyOperations {
    int handler(Integer money);

    int amount();
}
