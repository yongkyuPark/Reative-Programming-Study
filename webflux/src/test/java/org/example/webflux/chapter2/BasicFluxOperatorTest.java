package org.example.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.util.context.Context;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BasicFluxOperatorTest {

    @Test
    public void testFluxFromData() {
        Flux.just(1, 2, 3, 4, 5)
                .subscribe(data -> System.out.println("data = " + data));

        List<Integer> basicList = List.of(1, 2, 3, 4, 5);
        Flux.fromIterable(basicList)
                .subscribe(data -> System.out.println("data fromIterable = " + data));
    }

    @Test
    public void testFluxFromFunction() {
        Flux.defer(() -> {
            return Flux.just(1,2,3,4,5);
        }).subscribe(data -> System.out.println("data from defer = " + data));

        Flux.create(sink -> {
            sink.next(1);
            sink.next(2);
            sink.next(3);
            sink.complete();
        }).subscribe(data -> System.out.println("data from sink = " + data));
    }

    @Test
    public void testSinkDetail() {
        Flux.<String>create(sink -> {
            recursiveFunction(sink);
        })
                .contextWrite(Context.of("counter", new AtomicInteger(0)))
                .subscribe(data -> System.out.println("data from recursive = " + data));
    }

    @Test
    public void testFluxCollectList() {
        Flux.just(1,2,3,4,5)
                .map(data -> data * 2)
                .filter(data -> data % 4 == 0)
                .collectList()
                .subscribe(data -> System.out.println("collectList가 변환한 list data = " + data));
    }

    public void recursiveFunction(FluxSink<String> sink) {
        AtomicInteger counter = sink.contextView().get("counter");
        if (counter.incrementAndGet() < 10) {
            sink.next("sink count " + counter);
            recursiveFunction(sink);
        } else {
            sink.complete();
        }
    }
}
