package com.codestates.example.operators.transformation;

import reactor.core.publisher.Flux;

//concat() 예제
public class ConcatExample01 {
    public static void main(String[] args) {
        Flux
                .concat(Flux.just("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
                        Flux.just("Saturday", "Sunday"))
                .subscribe(System.out::println);
    }
}

//두 개의 Sequence를 이어 붙여서 논리적으로 하나의 Sequence로 동작