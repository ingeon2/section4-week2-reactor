package com.codestates.example.operators.errors;

import lombok.extern.slf4j.Slf4j;
import com.codestates.example.operators.sample_data.Coffee;
import reactor.core.publisher.Mono;

//에러 기본 예제
@Slf4j
public class ErrorExample01 {
    public static void main(String[] args) {
        Mono.justOrEmpty(findVerifiedCoffee()) //justOrEmpty() Operator는 파라미터로 전달되는 데이터소스가 null 이어도 에러가 발생하지 않습니다.
                //just() 는 에러 발생
                .switchIfEmpty(Mono.error(new RuntimeException("커피 못찾겠다")))
                //switchIfEmpty() Operator는 Upstream에서 전달되는 데이터가 null이면 대체 동작을 수행(아래)
                .subscribe(
                        data -> log.info("{} : {}", data.getKorName(), data.getPrice()),
                        error -> log.error("# onError: {}", error.getMessage())
                );
    }

    private static Coffee findVerifiedCoffee() {
        return null;
    }
}
