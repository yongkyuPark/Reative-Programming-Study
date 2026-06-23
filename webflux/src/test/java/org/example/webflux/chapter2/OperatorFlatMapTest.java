package org.example.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class OperatorFlatMapTest {

    @Test
    public void monoToFlux() {
        Mono<Integer> one = Mono.just(1);
        Flux<Integer> integerFlux = one.flatMapMany(data -> {
            return Flux.just(data, data + 1, data + 2);
        });
        integerFlux.subscribe(data -> System.out.println("data = " + data));
    }

    @Test
    public void testWebClientFlatMap() {
        Flux<String> flatMap = Flux.just(callWebClient("1단계 - 문제 이해하기", 1500),
                callWebClient("2단계 - 문제 단계별로 풀어가기", 1000),
                callWebClient("3단계 - 최종 응답", 500))
                .flatMap(monoData -> {
                    return monoData;
                });

        flatMap.subscribe(data -> System.out.println("FlatMapped data = " + data));

        Flux<String> flatMapSequential = Flux.just(callWebClient("1단계 - 문제 이해하기", 1500),
                        callWebClient("2단계 - 문제 단계별로 풀어가기", 1000),
                        callWebClient("3단계 - 최종 응답", 500))
                .flatMapSequential(monoData -> {
                    return monoData;
                });

        Flux<String> merge = Flux.merge(callWebClient("1단계 - 문제 이해하기", 1500),
                        callWebClient("2단계 - 문제 단계별로 풀어가기", 1000),
                        callWebClient("3단계 - 최종 응답", 500));

        merge.subscribe(data -> System.out.println("merge = " + data));

        Flux<String> mergeSequential = Flux.mergeSequential(callWebClient("1단계 - 문제 이해하기", 1500),
                callWebClient("2단계 - 문제 단계별로 풀어가기", 1000),
                callWebClient("3단계 - 최종 응답", 500));

        flatMapSequential.subscribe(data -> System.out.println("FlatMap Sequential data = " + data));

        Mono<String> monomonoString = Mono.just(Mono.just("안녕!")).flatMap(monoData -> monoData);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
    }

    public Mono<String> callWebClient(String request, long delay) {
        return Mono.defer(() -> {
            try {
                Thread.sleep(delay);
                return Mono.just(request + " -> 딜레이: " + delay);
            } catch (Exception e) {
                return Mono.empty();
            }
        })
                .subscribeOn(Schedulers.boundedElastic());
    }
}
