package org.example.reactor1.exam01;

import org.example.reactor1.utils.Logger;
import reactor.core.publisher.Mono;

public class MonoExample01 {
    public static void main(String[] args) {
        Mono.just("Hello Reactor!")
                .subscribe(data -> Logger.info("# emitted data: {}", data));
    }
}
