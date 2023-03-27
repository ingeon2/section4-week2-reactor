package com.codestates.example.operators.transformation;

import com.codestates.example.operators.sample_data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

//concat() 예제 2
@Slf4j
public class ConcatExample02 {
    public static void main(String[] args) {
        Flux
                .concat(Flux.fromIterable(SampleData.salesOfCafeA),
                        Flux.fromIterable(SampleData.salesOfCafeB),
                        Flux.fromIterable(SampleData.salesOfCafeC))
                .reduce((a, b) -> a + b)
                .subscribe(data -> log.info("# total sales: {}", data));
    }
}

//concat() Operator를 이용해서 3개 카페 지점의 월별 매출액을 모두 하나의 Sequence로 연결 한 다음 카페의 전체 매출액(리듀스)을 계산하는 예제