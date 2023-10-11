package com.atm.atmbasicdesign;

import com.atm.atmbasicdesign.enums.CurrencyUnit;
import com.atm.atmbasicdesign.exceptions.InvalidAmountException;
import com.atm.atmbasicdesign.handlers.DefaultMoneyHandler;
import com.atm.atmbasicdesign.logging.ConsoleLogger;
import com.atm.atmbasicdesign.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AtmBasicDesignApplicationTests {

    private DefaultMoneyHandler defaultMoneyHandler;
    private Logger logger;

    @BeforeAll
    public void init() {
        this.defaultMoneyHandler = DefaultMoneyHandler.getIns();
        this.logger = new ConsoleLogger();
    }

    void depositMoney() {
        int totalAmount = CurrencyUnit.TWO_THOUSAND.unit() * 10 + CurrencyUnit.FIVE_HUNDRED.unit() * 20 + CurrencyUnit.TWO_HUNDRED.unit() * 10 + CurrencyUnit.ONE_HUNDRED.unit() * 10;
        defaultMoneyHandler.depositMoney(Map.of(CurrencyUnit.TWO_THOUSAND, 10, CurrencyUnit.FIVE_HUNDRED, 20, CurrencyUnit.TWO_HUNDRED, 10, CurrencyUnit.ONE_HUNDRED, 10));
        logger.log("Available Balance in ATM " + defaultMoneyHandler.getAvailableBalance());
        assertEquals(totalAmount, defaultMoneyHandler.getAvailableBalance());
    }

    void withdrawMoney() {
        depositMoney();
        int totalMoney = defaultMoneyHandler.getAvailableBalance();
        defaultMoneyHandler.withdrawMoney(5000);
        logger.log("Available Balance in ATM " + defaultMoneyHandler.getAvailableBalance());

        //totalMoney = defaultMoneyHandler.getAvailableBalance();
        assertEquals(totalMoney - 5000, defaultMoneyHandler.getAvailableBalance());
        logger.log("Available Balance in ATM " + defaultMoneyHandler.getAvailableBalance());

        totalMoney = defaultMoneyHandler.getAvailableBalance();
        defaultMoneyHandler.withdrawMoney(5300);
        assertEquals(totalMoney - 5300, defaultMoneyHandler.getAvailableBalance());
        logger.log("Available Balance in ATM " + defaultMoneyHandler.getAvailableBalance());
    }

    @Test
    //@Order(0)
    void withdrawMoneyWithFail() {
        withdrawMoney();
        logger.log("Available Balance in ATM " + defaultMoneyHandler.getAvailableBalance());
        assertThrows(InvalidAmountException.class, () -> defaultMoneyHandler.withdrawMoney(30000));
        logger.log("Available Balance in ATM " + defaultMoneyHandler.getAvailableBalance());
    }
}
