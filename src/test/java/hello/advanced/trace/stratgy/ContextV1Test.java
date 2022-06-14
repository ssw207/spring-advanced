package hello.advanced.trace.stratgy;

import hello.advanced.trace.stratgy.code.ContextV1;
import hello.advanced.trace.stratgy.code.StrategyLogic1;
import hello.advanced.trace.stratgy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    public void strategyV0() throws Exception {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTIme={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTIme={}", resultTime);
    }
    
    @Test
    public void strategyV1() throws Exception {
        ContextV1 context1 = new ContextV1(new StrategyLogic1());
        context1.execute();
        ContextV1 context2 = new ContextV1(new StrategyLogic2());
        context2.execute();
    }

    @Test
    public void strategyV2() throws Exception {
        ContextV1 context1 = new ContextV1(() -> log.info("비즈니스1"));
        context1.execute();

        ContextV1 context2 = new ContextV1(() -> log.info("비즈니스 2"));
        context2.execute();
    }
}

