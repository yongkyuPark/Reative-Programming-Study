package com.example.reactor2.exam02.class02;

import com.example.reactor2.common.SampleData;
import com.example.reactor2.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * skip 활용 예제
 *  - 년도별 BTC 최고가 중에서 2000만원 이상이었던 년도 중, 오래된 년도의 2년을 건너뛴 가격을 표시한다.
 */
public class SkipExample03 {

    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.btcTopPricesPerYear)
                .filter(tuple -> tuple.getT2() >= 20_000_000)
                .skip(2)
                .subscribe(tuple -> Logger.onNext(tuple.getT1(), tuple.getT2()));
    }

}
