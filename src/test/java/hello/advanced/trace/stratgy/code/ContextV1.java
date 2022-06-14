package hello.advanced.trace.stratgy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식 - 객체 생성시 조립
 * 동적으로 전략을 변경하기 어렵다.
 */
@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        strategy.call();
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTIme={}", resultTime);
    }
}
