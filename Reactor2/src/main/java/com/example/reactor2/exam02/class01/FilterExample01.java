package com.example.reactor2.exam02.class01;

import com.example.reactor2.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * Filter 기본 개념 예제
 */
public class FilterExample01 {

    public static void main(String[] args) {
        Flux
                .range(1, 20)
                .filter(num -> num % 2 == 0)
                .subscribe(Logger::onNext);
    }

}
