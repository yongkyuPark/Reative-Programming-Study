package com.example.reactor2.exam02.class04;

import com.example.reactor2.utils.Logger;
import com.example.reactor2.utils.TimeUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * take 기본 개념 예제
 *  - 파라미터로 입력한 시간 내에 Upstream에서 emit된 데이터만 Downstream으로 emit 한다.
 */
public class TakeExample02 {

    public static void main(String[] args) {
        Flux
                .interval(Duration.ofSeconds(1))
                .doOnNext(Logger::doOnNext)
                .take(Duration.ofSeconds(2))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(4000L);
    }

}
