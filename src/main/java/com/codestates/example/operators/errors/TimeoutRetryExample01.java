package com.codestates.example.operators.errors;

//타임아웃 리트라이 예제

import com.codestates.example.operators.sample_data.Coffee;
import com.codestates.example.operators.sample_data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Collectors;

@Slf4j
public class TimeoutRetryExample01 {
    public static void main(String[] args) throws InterruptedException {
        getCoffees()
                .collect(Collectors.toSet())
                //emit된 데이터를 Set<Coffee>으로 변환하는 이유는
                //timeout이 되기전에 이미 emit된 데이터가 있으므로 재구독 후, 다시 emit된 데이터에 동일한 데이터가 있으므로 중복을 제거하기 위함
                .subscribe(bookSet -> bookSet
                        .stream()
                        .forEach(data ->
                                log.info("{} : {}", data.getKorName(), data.getPrice())));

        Thread.sleep(12000);
    }

    private static Flux<Coffee> getCoffees() {
        final int[] count = {0};
        return Flux
                .fromIterable(SampleData.coffeeList)
                .delayElements(Duration.ofMillis(500))
                // delayElements() Operator는 입력으로 주어진 시간만큼 각각의 데이터 emit을 지연시키는 Operator입니다. 따라서 Coffee 객체는 0.5초에 한번씩 emit
                .map(coffee -> {
                    try {
                        count[0]++;
                        if (count[0] == 3) {     // 세번째 커피 정보(coffee) 의도적으로 2초 더 지연
                            Thread.sleep(2000);
                        }
                    } catch (InterruptedException e) {
                    }

                    return coffee;
                })
                .timeout(Duration.ofSeconds(2))   // 2초안에 데이터가 emit되지 않으면 onError Signal 이벤트가 발생하도록 지정 
                // 세 번째 커피 정보는 2.5초가 지연되어 Downstream으로 emit되지 않음
                .retry(1)     // retry() Operator를 추가했기 때문에 1회 재구독을 해서 Sequence를 다시 시작
                .doOnNext(coffee -> log.info("# getCoffees > doOnNext: {}, {}",
                        coffee.getKorName(), coffee.getPrice()));
    }
}
