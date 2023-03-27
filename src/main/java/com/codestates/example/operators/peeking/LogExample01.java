package com.codestates.example.operators.peeking;

import reactor.core.publisher.Flux;

import java.util.stream.Stream;

public class LogExample01 {
    public static void main(String[] args) {
        Flux
                .fromStream(Stream.of(200, 300, 400, 500, 600))
                .log()
                .reduce((a, b) -> a + b)
                .log()
                .subscribe(System.out::println);
    }
}

//log() Operator는 Publisher에서 발생하는 Signal 이벤트를 로그로 출력해주는 역할
//실행 결과를 보면 Publisher에서 발생한 Signal 이벤트에 대한 내용이 로그로 출력