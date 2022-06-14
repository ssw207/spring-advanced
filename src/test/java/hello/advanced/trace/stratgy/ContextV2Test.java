package hello.advanced.trace.stratgy;

import hello.advanced.trace.stratgy.code.ContextV2;
import hello.advanced.trace.stratgy.code.StrategyLogic2;
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

