package hello.trace.trace.stratgy;

import hello.trace.trace.stratgy.code.ContextV2;
import hello.trace.trace.stratgy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    public void strategyV1() throws Exception {
        ContextV2 context1 = new ContextV2();
        context1.execute(() -> log.info("비즈니스1"));
        ContextV2 context2 = new ContextV2();
        context2.execute(new StrategyLogic2());
    }
}

