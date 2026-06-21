package org.example.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reactive")
@Slf4j
public class ReactiveProgrammingExampleController {

    //1~9까지 출력하는 api
    @GetMapping("/onenine/list")
    public List<Integer> produceOneToNine() {
        List<Integer> sink = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
            sink.add(i);
        }
        return sink;
    }

    @GetMapping("/onenine/flux")
    public Flux<Integer> produceOneToNineFlux() {
        return Flux.<Integer>create(sink -> {
            for (int i = 1; i <= 9; i++) {
                try {
                    log.info("현재 처리하고 있는 스레드 이름: " + Thread.currentThread().getName());
                    Thread.sleep(500);
                } catch (Exception e) {
                }
                sink.next(i);
            }
            sink.complete();
        }).subscribeOn(Schedulers.boundedElastic());
    }
    // 리액티브 스트림 구현체 Flux, Mono를 사용하여 발생하는 데이터를 바로바로 리액티브하게 처리
    // 비동기로 동작 - 논 블로킹하게 동작 해야한다.
}
