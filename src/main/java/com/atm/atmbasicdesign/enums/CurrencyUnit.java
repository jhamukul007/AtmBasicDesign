package com.atm.atmbasicdesign.enums;

public enum CurrencyUnit {
    TWO_THOUSAND {
        @Override
        public int unit() {
            return 2000;
        }
    }, FIVE_HUNDRED {
        @Override
        public int unit() {
            return 500;
        }
    }, TWO_HUNDRED {
        @Override
        public int unit() {
            return 200;
        }
    }, ONE_HUNDRED {
        @Override
        public int unit() {
            return 100;
        }
    };

    public abstract int unit();
}
