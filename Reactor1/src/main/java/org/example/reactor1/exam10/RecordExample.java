package org.example.reactor1.exam10;

import reactor.core.publisher.Flux;

public class RecordExample {

    public static Flux<String> getCountry(Flux<String> source) {
        return source
                .map(country -> country.substring(0, 1).toUpperCase() + country.substring(1));
    }

}
