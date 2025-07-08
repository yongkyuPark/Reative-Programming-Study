package com.example.reactor2.exam02.class05;

import com.example.reactor2.common.SampleData;
import com.example.reactor2.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * takeUntil 기본 예제
 *  - 파라미터로 입력되는 Predicate이 true가 될때까지 데이터를 emit한다.
 *  - emit 된 데이터에는 Predicate이 true로 matching 되는 데이터가 포함된다.
 */
public class TakeUntilExample {

    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.btcTopPricesPerYear)
                .takeUntil(tuple -> tuple.getT2() > 10_000_000)
                .subscribe(tuple -> Logger.onNext(tuple.getT1(), tuple.getT2()));
    }

}
