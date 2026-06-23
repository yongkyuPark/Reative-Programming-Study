package org.example.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class FluxMonoErrorAndSignalTest {

    @Test
    public void testBasicSignal() {
        Flux.just(1,2,3,4)
                .doOnNext(publishedData -> System.out.println("publishedData = " + publishedData))
                .doOnComplete(() -> System.out.println("스트림이 끝났습니다."))
                .doOnError(ex -> {
                    System.out.println("ex 에러상황 발생! = " + ex);
                })
                .subscribe(data -> System.out.println("data = " + data));
    }

    @Test
    public void testFluxMonoError() {
        try {
            Flux.just(1,2,3,4)
                    .map(data -> {
                        try {
                            if (data == 3) {
                                throw new RuntimeException();
                            }
                            return data * 2;
                        } catch (Exception e) {
                            return data * 999;
                        }
                    })
                    .onErrorMap(ex -> new IllegalArgumentException())
                    .onErrorReturn(999)
                    .onErrorComplete()
                    //.subscribeOn(Schedulers.boundedElastic())
                    .subscribe(data -> System.out.println("data = " + data));
        } catch (Exception e) {
            System.out.println("에러가 발생했어요!");
        }
    }

    @Test
    public void testFluxMonoDotError() {
        Flux.just(1,2,3,4)
                .flatMap(data -> {
                    if (data != 3) {
                        return Mono.just(data);
                    } else {
                        return Mono.error(new RuntimeException());
                    }
                }).subscribe(data -> System.out.println("data = " + data));
    }
}
