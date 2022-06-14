package hello.advanced.trace.stratgy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 컨텍스트를 실행시마다 전략을 파라미터로 받는 방식
 */
@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy) {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        strategy.call();
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTIme={}", resultTime);
    }
}
