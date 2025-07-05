package org.example.reactor1.exam01;

import org.example.reactor1.utils.Logger;
import reactor.core.publisher.Mono;

/**
 * Mono 기본 개념 예제
 *  - 원본 데이터의 emit 없이 onComplete signal 만 emit 한다.
 */
public class MonoExample02 {
    public static void main(String[] args) {
        Mono.empty()
                .subscribe(
                        data -> Logger.info("# emitted data: {}", data),
                        error -> {},
                        () -> Logger.info("# emitted onComplete signal")
                );
    }
}
