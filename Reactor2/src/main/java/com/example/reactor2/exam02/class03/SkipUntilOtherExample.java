package com.example.reactor2.exam02.class03;

import com.example.reactor2.utils.Logger;
import com.example.reactor2.utils.TimeUtils;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * 파라미터로 입력된 Publisher가 onNext 또는 onComplete signal을 발생시킬 때까지 Upstream에서 emit된 데이터를 건너뛴다.
 */
public class SkipUntilOtherExample {

    public static void main(String[] args) {
        Flux.interval(Duration.ofSeconds(1))
                .skipUntilOther(doSomeTask())
                .subscribe(Logger::onNext);

        TimeUtils.sleep(4000);
    }

    private static Publisher<?> doSomeTask() {
        return Mono.empty().delay(Duration.ofMillis(2500));
    }

}
