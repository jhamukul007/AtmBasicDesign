package com.atm.atmbasicdesign.handlers;

import com.atm.atmbasicdesign.enums.CurrencyUnit;
import com.atm.atmbasicdesign.exceptions.InvalidAmountException;

public class CurrencyUnitHandler implements ICurrencyHandler {
    private ICurrencyHandler next;
    private int unit;
    private int count;

    public CurrencyUnitHandler(CurrencyUnit currencyUnit) {
        this.unit = currencyUnit.unit();
        this.count = 0;
    }

    @Override
    public int handler(Integer money) {
        int requiredUnit = money / unit;
        int total = 0;
        if (requiredUnit > count) {
            total += count;
        } else
            total += requiredUnit;
        int remainingUnit = money - total * unit;
        if (remainingUnit != 0) {
            if (next == null)
                throw new InvalidAmountException();
            else
                next.handler(remainingUnit);
        }
        this.count -= total;
        if (total != 0)
            System.out.println(String.format("Total %s count of unit %s ", total, unit));
        return -1;
    }

    @Override
    public int amount() {
        return count * unit + (next == null ? 0 : next.amount());
    }

    @Override
    public void addCount(int count) {
        this.count += count;
    }

    @Override
    public ICurrencyHandler next() {
        return this.next;
    }

    @Override
    public void setNext(ICurrencyHandler next) {
        this.next = next;
    }

}
