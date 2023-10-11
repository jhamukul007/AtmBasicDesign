package com.atm.atmbasicdesign.handlers;

import com.atm.atmbasicdesign.enums.CurrencyUnit;
import com.atm.atmbasicdesign.exceptions.CurrencyUnitNotSupportedException;

import java.util.Map;

public class DefaultMoneyHandler {
    private final ICurrencyHandler next;
    private static DefaultMoneyHandler ins;

    private DefaultMoneyHandler() {
        this.next = new CurrencyUnitHandler(CurrencyUnit.TWO_THOUSAND);
        this.next.setNext(new CurrencyUnitHandler(CurrencyUnit.FIVE_HUNDRED));
        this.next.next().setNext(new CurrencyUnitHandler(CurrencyUnit.TWO_HUNDRED));
        this.next.next().next().setNext(new CurrencyUnitHandler(CurrencyUnit.ONE_HUNDRED));
    }

    public static DefaultMoneyHandler getIns() {
        if (ins == null) {
            synchronized (DefaultMoneyHandler.class) {
                if (ins == null)
                    ins = new DefaultMoneyHandler();
            }
        }
        return ins;
    }

    public void withdrawMoney(int amount) {
        // balance check and then
        System.out.println("Money withdraw request with amount " + amount);
        next.handler(amount);
    }

    public int getAvailableBalance() {
        return this.next.amount();
    }

    public void depositMoney(Map<CurrencyUnit, Integer> unitAndCount) {
        ICurrencyHandler current = next;
        int count = 1;
        while (current != null) {
            current.addCount(unitAndCount.get(order(count++)));
            current = current.next();
        }
    }

    CurrencyUnit order(int count) {
        switch (count) {
            case 1:
                return CurrencyUnit.TWO_THOUSAND;
            case 2:
                return CurrencyUnit.FIVE_HUNDRED;
            case 3:
                return CurrencyUnit.TWO_THOUSAND;
            case 4:
                return CurrencyUnit.ONE_HUNDRED;
            default:
                throw new CurrencyUnitNotSupportedException();
        }
    }

}
