package com.example.reactor2.exam02.class06;

import com.example.reactor2.common.SampleData;
import com.example.reactor2.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * next 기본 예제
 *  - emit 된 데이터 중에서 첫번째 데이터만 Downstream 으로 emit 한다.
 */
public class NextExample {

    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.btcTopPricesPerYear)
                .doOnNext(Logger::doOnNext)
                .filter(tuple -> tuple.getT1() == 2015)
                .next()
                .subscribe(tuple -> Logger.onNext(tuple.getT1(), tuple.getT2()));
    }

}
