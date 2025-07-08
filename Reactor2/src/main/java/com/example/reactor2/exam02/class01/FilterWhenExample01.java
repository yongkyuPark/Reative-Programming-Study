package com.example.reactor2.exam02.class01;

import com.example.reactor2.common.SampleData;
import com.example.reactor2.utils.Logger;
import com.example.reactor2.utils.TimeUtils;
import reactor.core.publisher.Flux;

import static com.example.reactor2.common.CoronaVaccineService.isGreaterThan;

/**
 * 백신의 재고를 기준 이상으로 보유하고 있는 백신만 출력하도록 하는 예제
 *  - filterWhen을 사용해서 비동기적으로 필터링을 한다.
 */
public class FilterWhenExample01 {

    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.coronaVaccineNames)
                .filterWhen(vaccine -> isGreaterThan(vaccine, 3_000_000))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(1000);
    }

}
