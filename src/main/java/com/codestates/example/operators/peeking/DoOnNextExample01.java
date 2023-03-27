package com.codestates.example.operators.peeking;

//doOnNext() 예제

import com.codestates.example.operators.sample_data.Coffee;
import com.codestates.example.operators.sample_data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class DoOnNextExample01 {
    public static void main(String[] args) {
        Flux
                .fromIterable(SampleData.coffeeList)
                .doOnNext(coffee -> validateCoffee(coffee))
                .subscribe(data -> log.info("{} : {}", data.getKorName(), data.getPrice()));
    }

    private static void validateCoffee(Coffee coffee) {
        if(coffee == null) {
            throw new RuntimeException("커피없어");
        }
    }
}

//doOnNext()는 주로 로깅(로그를 기록 또는 출력하는 작업)에 사용되지만
//데이터를 emit하면서 필요한 추가 작업이 있다면 doOnNext()에서 처리할 수 있음.