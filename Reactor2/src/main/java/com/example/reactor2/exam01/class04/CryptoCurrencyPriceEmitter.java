package com.example.reactor2.exam01.class04;

import com.example.reactor2.common.SampleData;

public class CryptoCurrencyPriceEmitter {

    private CryptoCurrencyPriceListener listener;

    public void setListener(CryptoCurrencyPriceListener listener) {
        this.listener = listener;
    }

    public void flowInto() {
        listener.onPrice(SampleData.btcPrices);
    }
    public void complete() {
        listener.onComplete();
    }

}
