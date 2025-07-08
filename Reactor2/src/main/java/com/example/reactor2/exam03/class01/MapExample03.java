package com.example.reactor2.exam03.class01;

import com.example.reactor2.common.SampleData;
import com.example.reactor2.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * map 활용 예제
 *  - Morse Code를 알파벳으로 변환하는 예제
 */
public class MapExample03 {

    public static void main(String[] args) {
        Flux
                .just("...", "---", "...")
                .map(code -> transformMorseCode(code))
                .subscribe(Logger::onNext);
    }

    public static String transformMorseCode(String morseCode) {
        return SampleData.morseCodeMap.get(morseCode);
    }

}
