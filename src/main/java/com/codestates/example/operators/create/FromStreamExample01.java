package com.codestates.example.operators.create;

import reactor.core.publisher.Flux;

import java.util.stream.Stream;

//fromStream() 예제
public class FromStreamExample01 {
    public static void main(String[] args) {
        Flux
                .fromStream(Stream.of(200, 300, 400, 500 ,600))
                //fromStream()의 입력으로 Stream을 전달합니다. 전달 받은 Stream이 포함하고 있는 데이터를 차례대로 emit
                .reduce((a, b) -> a + b) 
                //Upstream으로부터 전달 받은 두 개의 숫자를 합하는 과정을 반복해서 총합계를 구함. (reduce는 두개 데이터 순차적으로 누적 처리)
                .subscribe(System.out::println);

    }
}
