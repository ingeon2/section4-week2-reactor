package com.codestates.example.operators.create;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Arrays;
import java.util.List;

//create() Operator 예제
@Slf4j
public class CreateExample {

    private static List<Integer> source= Arrays.asList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19);
    public static void main(String[] args) {
        Flux.create((FluxSink<Integer> sink) -> { //FluxSink는 데이터 emit 등의 기능을 개발자가 직접 코드를 구현해서 처리
            //onRequest()는 Subscriber에서 데이터를 요청하면 onRequest()의 파라미터인 람다 표현식이 실행
            sink.onRequest(n -> {
                for (int i = 0; i < source.size(); i++) {
                    sink.next(source.get(i)); //for문을 순회하면서 next() 메서드로 List source의 원소를 emit
                }
                sink.complete(); //List source 원소를 모두 emit했으므로, Sequence를 종료하기 위해 complete() 을 호출
            });

            //onDispose()는 Sequence가 완전히 종료되기 직전에 호출되며, sequence 종료 직전 후처리 작업
            sink.onDispose(() -> log.info("# clean up"));
        }).subscribe(data -> log.info("# onNext: {}", data));
    }
}
