package hello.trace.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic {

    private ConcreteLogic target;

    public TimeProxy(ConcreteLogic concreteLogic) {
        this.target = concreteLogic;
    }

    @Override
    public String operation() {
        log.info("time decorator 실행");
        long start = System.currentTimeMillis();

        String result = target.operation();

        long end = System.currentTimeMillis();
        log.info("time decorator 종료 resultTime={}", end - start);
        return result;
    }
}
