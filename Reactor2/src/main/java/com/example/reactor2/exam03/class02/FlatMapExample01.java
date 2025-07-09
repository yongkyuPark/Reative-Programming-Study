package com.example.reactor2.exam03.class02;

import com.example.reactor2.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * flatMap 기본 개념 예제
 *  - inner Publisher를 통해 1대다의 데이터 매핑이 가능하다.
 */
public class FlatMapExample01 {

    public static void main(String[] args) {
        Flux
                .just("Good", "Bad")
                .flatMap(feeling ->
                        Flux
                                .just("Morning", "Afternoon", "Evening")
                                .map(time -> feeling + " " + time))
                .subscribe(Logger::onNext);
    }

}
