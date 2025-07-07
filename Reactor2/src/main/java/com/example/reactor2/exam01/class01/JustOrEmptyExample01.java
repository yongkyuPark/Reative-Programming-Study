package com.example.reactor2.exam01.class01;

import com.example.reactor2.utils.Logger;
import reactor.core.publisher.Mono;

/**
 * just()에 null 값을 입력하면 NullPointException 이 발생하는 예제
 */
public class JustOrEmptyExample01 {

    public static void main(String[] args) {
        Mono
                .just(null)
                .log()
                .subscribe(Logger::onNext);
    }

}
