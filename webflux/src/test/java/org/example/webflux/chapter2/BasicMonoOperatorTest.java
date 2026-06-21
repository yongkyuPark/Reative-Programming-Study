package org.example.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class BasicMonoOperatorTest {

    @Test
    public void startMonoFromData() {
        Mono.just(1).subscribe(data -> System.out.println("data = " + data));

        Mono.empty().subscribe(data -> System.out.println("empty data = " + data));
    }

    @Test
    public void startMonoFromFunction() {
        Mono<String> monoFromCallable = Mono.fromCallable(() -> {
            return callRestTemplate("안녕!");
        }).subscribeOn(Schedulers.boundedElastic());

        Mono<String> monoFromDefer = Mono.defer(() -> {
            return callWebClient("안녕!");
        });
        monoFromDefer.subscribe();

        Mono<String> monoFromJust = callWebClient("안녕!");
    }

    @Test
    public void testDeferNecessity() {
        Mono<String> stringMono = Mono.defer(() -> {
            String a = "안녕";
            String b = "하세";
            String c = "요";
            return callWebClient(a + b + c);
        }).subscribeOn(Schedulers.boundedElastic());

    }

    @Test
    public void testBasicFluxMono() {
        Flux.<Integer>just(1, 2, 3, 4, 5)
                .map(data -> data * 2)
                .filter(data -> data % 4 == 0)
                .subscribe(data -> System.out.println("Flux가 구독한 data! = " + data));

        Mono.<Integer>just(2)
                .map(data -> data * 2)
                .filter(data -> data % 4 == 0)
                .subscribe(data -> System.out.println("Mono가 구독한 data! = " + data));
    }

    @Test
    public void monoToFlux() {
        Mono<Integer> one = Mono.just(1);
        Flux<Integer> integerFlux = one.flatMapMany(data -> {
            return Flux.just(data, data + 1, data + 2);
        });
        integerFlux.subscribe(data -> System.out.println("data = " + data));
    }

    public Mono<String> callWebClient(String request) {
        return Mono.just(request + "callWebClient");
    }

    public String callRestTemplate(String request) {
        return request + "callRestTemplate 응답";
    }
}
