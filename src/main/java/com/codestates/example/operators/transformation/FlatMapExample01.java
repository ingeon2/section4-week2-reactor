package com.codestates.example.operators.transformation;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

//flatMap() 예제
@Slf4j
public class FlatMapExample01 {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .range(2, 6)         // range() Operator를 이용해서 구구단 2단부터 7단까지 출력하도록 지정
                .flatMap(dan -> Flux
                        .range(1, 9)  // flatMap() 내부에서 range() Operator로 하나의 단을 출력하도록 1부터 9까지 숫자를 지정
                        .publishOn(Schedulers.parallel())   // flatMap() 내부의 Inner Sequence를 처리할 쓰레드를 할당,
                        //따라서 전체 Sequence는 여러 개의 쓰레드가 비동기적으로 동작
                        .map(num -> dan + " x " + num + " = " + dan * num)) // 구구단 형식으로 문자열을 구성
                .subscribe(log::info);

        Thread.sleep(100L);
    }
}

//flatMap() Operator에서 추가 쓰레드를 할당할 경우, 작업의 처리 순서를 보장하지 않음. (결과 보샘.)