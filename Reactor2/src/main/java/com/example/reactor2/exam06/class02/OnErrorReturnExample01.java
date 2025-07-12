package com.example.reactor2.exam06.class02;

import com.example.reactor2.common.Book;
import com.example.reactor2.common.SampleData;
import com.example.reactor2.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * onErrorReturn 예제
 *  - 예외가 발생했을 때, error signal을 Downstream으로 전송하지 않고, default value로 대체해서 emit하고자 할 경우
 *  - try ~ catch 문의 경우, catch해서 default value를 return 하는 것과 같다.
 */

public class OnErrorReturnExample01 {

    public static void main(String[] args) {
        getBooks()
                .map(book -> book.getPenName().toUpperCase())
                .onErrorReturn("No pen name")
                .subscribe(Logger::info, Logger::onError);
    }

    public static Flux<Book> getBooks() {
        return Flux.fromIterable(SampleData.books);
    }

}
